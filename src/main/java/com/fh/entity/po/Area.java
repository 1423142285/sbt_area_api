package com.fh.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fh.annotation.ExcelAnnotation;

@TableName("stb_area")
@ExcelAnnotation(title = "地区信息",sheetName = "地区展示")
public class Area {
    @TableId(value = "id",type = IdType.AUTO)
    @ExcelAnnotation(column = "id")
    private Integer id;
    @ExcelAnnotation(column = "地区名称")
    private String name;
    @ExcelAnnotation(column = "上级id")
    private Integer pid;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
