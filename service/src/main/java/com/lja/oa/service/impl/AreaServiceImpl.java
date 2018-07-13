package com.lja.oa.service.impl;

import com.lja.oa.dao.AreaMapper;
import com.lja.oa.pojo.Area;
import com.lja.oa.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> selectByAreaParentId(String areaParentId) {
        return areaMapper.selectByAreaParentId(areaParentId);
    }
}
