package com.fh.controller;

import com.fh.entity.po.Area;
import com.fh.service.AreaService;
import com.fh.utils.ExportExcel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("area")
@CrossOrigin
public class AreaController {
    @Autowired
    private AreaService areaService;

    @PostMapping("queryAreaList")
    public Map queryAreaList(){
        Map map = new HashMap();
        try {
           List<Area> list = areaService.queryAreaList();
           map.put("code",520);
           map.put("data",list);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",200);
        }
        return map;
    }

    @PostMapping("addArea")
    public Map addArea(Area area){
        Map map = new HashMap();
        try {
            areaService.addArea(area);
            map.put("code",100);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",101);
            map.put("reamrk",e.getMessage());
        }
        return map;
    }

    @PostMapping("deletArea")
    public Map deleteArea(String ids){
        Map map = new HashMap();
        try {
            areaService.deleteArea(ids);
            map.put("code",100);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",101);
            map.put("reamrk",e.getMessage());
        }
        return map;
    }

    //修改节点
    @PostMapping("uploadArea")
    public Map uploadArea(Area area){
        Map map = new HashMap();
        try {
            areaService.updateArea(area);
            map.put("code",100);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",101);
            map.put("reamrk",e.getMessage());
        }
        return map;
    }

    //导出Excel
    @GetMapping("importExcel")
    public void importExcel(String ids, HttpServletResponse response) throws InvocationTargetException, NoSuchMethodException, IOException, IllegalAccessException {
        List<Area> list = areaService.queryAreaList(ids);
        XSSFWorkbook xssfSheets = ExportExcel.exportExcel(list);
        //下载文件
        //设置编码
        response.setCharacterEncoding("utf-8");
        //设置响应数据类型
        response.setContentType("application/octet-stream");//设置响应类型 告诉浏览器输出内容为流
        //设置响应文件名
        response.setHeader("Content-disposition", "attachment;filename=" + UUID.randomUUID().toString() + ".xlsx");
        //获取响应流
        ServletOutputStream os = response.getOutputStream();
        //将workbook的内容 写入输出流中
        xssfSheets.write(os);
        os.close();
    }

}
