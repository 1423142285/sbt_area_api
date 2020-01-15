package com.fh.service;

import com.fh.entity.po.Area;

import java.util.List;

public interface AreaService {
    List<Area> queryAreaList();

    void addArea(Area area);

    void deleteArea(String ids);

    void updateArea(Area area);

    List<Area> queryAreaList(String ids);
}
