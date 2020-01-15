package com.fh.utils;



import com.fh.annotation.ExcelAnnotation;
import com.fh.entity.po.ExcelInIt;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ExportExcel {
    public static XSSFWorkbook exportExcel(List<?> list) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, FileNotFoundException {
        ExcelInIt excelInfo = new ExcelInIt();
        Object o = list.get(0);
        Class<?> clazz = o.getClass();
        //使用java的反射机制获取注解
        ExcelAnnotation excelAnnotation = (ExcelAnnotation)clazz.getAnnotation(ExcelAnnotation.class);
        if (excelAnnotation!=null){
            excelInfo.setTitle(excelAnnotation.title());
            excelInfo.setSheetName(excelAnnotation.sheetName());
            List<String> columns=new ArrayList<String>();
            List<String> columnName=new ArrayList<String>();
            Field[] declaredFields = clazz.getDeclaredFields();
            for(int i = 0; i < declaredFields.length; i++){
                Field field = declaredFields[i];
                ExcelAnnotation annotation = field.getAnnotation(ExcelAnnotation.class);
                if(annotation!=null){
                    columns.add(field.getName());
                    columnName.add(annotation.column());
                }
            }
            excelInfo.setColumns(columns);
            excelInfo.setColumnName(columnName);
        }
        return Excel.excel(list, excelInfo);
    }
}
