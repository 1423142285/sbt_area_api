package com.fh.controller;

import com.fh.model.Area;
import com.fh.uitls.SqlString;
import org.junit.Test;

public class TestController {
    @Test
    public void test1(){
        Area area = new Area();
        String sql = SqlString.getSql(area);
        System.out.println("sql为"+sql);
    }
}
