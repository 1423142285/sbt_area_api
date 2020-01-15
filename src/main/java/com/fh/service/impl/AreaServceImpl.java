package com.fh.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fh.dao.AreaDao;
import com.fh.entity.po.Area;
import com.fh.service.AreaService;
import com.fh.utils.RedisPools;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AreaServceImpl implements AreaService {
    @Resource
    private AreaDao areaDao;

    @Override
    public List<Area> queryAreaList() {
        Jedis jedis = RedisPools.getJedis();
        String areaList = jedis.get("areaList");
        if(areaList!=null){
            List<Area> list = JSONArray.parseArray(areaList, Area.class);
            return list;
        }else {
            List<Area> list1 = areaDao.selectList(null);
            String s = JSONObject.toJSONString(list1);
            jedis.set("areaList",s);
            RedisPools.returnjedis(jedis);
            return list1;
        }
    }

    @Override
    public void addArea(Area area) {
        areaDao.insert(area);
        List<Area> list = areaDao.selectList(null);
        Jedis jedis = RedisPools.getJedis();
        jedis.set("areaList",JSONObject.toJSONString(list));
        RedisPools.returnjedis(jedis);
    }

    @Override
    public void deleteArea(String ids) {
        Map map = new HashMap();
        ids = ids.substring(0,ids.length()-1);
        map.put("ids",ids);
        areaDao.deleteArea(map);
        List<Area> list = areaDao.selectList(null);
        Jedis jedis = RedisPools.getJedis();
        jedis.set("areaList",JSONObject.toJSONString(list));
        RedisPools.returnjedis(jedis);
    }

    @Override
    public void updateArea(Area area) {
        areaDao.updateById(area);
        List<Area> list = areaDao.selectList(null);
        Jedis jedis = RedisPools.getJedis();
        jedis.set("areaList",JSONObject.toJSONString(list));
        RedisPools.returnjedis(jedis);
    }

    @Override
    public List<Area> queryAreaList(String ids) {
        Map map = new HashMap();
        if(ids != ""){
            ids = ids.substring(0,ids.length()-1);
            map.put("ids",ids);
        }else {
            ids = "";
            map.put("ids",ids);
        }

        List<Area> list = areaDao.queryAreaList(map);
        return list;
    }
}
