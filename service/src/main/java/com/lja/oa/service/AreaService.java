package com.lja.oa.service;

import com.lja.oa.pojo.Area;

import java.util.List;

public interface AreaService {
    List<Area> selectByAreaParentId(String areaParentId);
}
