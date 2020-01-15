package com.fh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fh.annotation.ExcelAnnotation;
import com.fh.conntre.SqlAnnotation;

@TableName("stb_area")
@SqlAnnotation(tables = "t_area")
public class Area {
    @TableId(value = "id",type = IdType.AUTO)
    @SqlAnnotation(colunm = "t_id")
    private Integer id;
    @SqlAnnotation(colunm = "t_name")
    private String name;
    @SqlAnnotation(colunm = "t_pid")
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
