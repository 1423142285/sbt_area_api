package com.fh.uitls;

import com.fh.conntre.SqlAnnotation;

import java.lang.reflect.Field;

public class SqlString {
    public static String getSql(Object obj){
        String sql = "";
        String tableName="";
        String cloum = "";
        Class<?> aClass = obj.getClass();
        SqlAnnotation annotation = aClass.getAnnotation(SqlAnnotation.class);
        if(annotation!=null){
            tableName += annotation.tables();
            Field[] fields = aClass.getDeclaredFields();
            for (int i = 0; i <fields.length ; i++) {
                Field field = fields[i];
                SqlAnnotation annotation1 = field.getAnnotation(SqlAnnotation.class);
                if(annotation1!=null){
                    String colunm = annotation1.colunm();
                    cloum+=colunm+",";
                }
            }
            cloum = cloum.substring(0,cloum.length()-1);
            sql = "select "+cloum+" from "+tableName;
        }else {
            sql="";
        }
        return sql;
    }
}
