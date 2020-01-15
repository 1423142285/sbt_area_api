package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.entity.po.Area;

import java.util.List;
import java.util.Map;

public interface AreaDao extends BaseMapper<Area> {
    void deleteArea(Map map);

    List<Area> queryAreaList(Map map);
}
