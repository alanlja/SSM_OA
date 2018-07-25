package com.lja.oa.service.impl;

import com.lja.oa.dao.RoleMapper;
import com.lja.oa.pojo.Role;
import com.lja.oa.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "roleServiceImpl")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String, Object> queryRolePage(Map<String, Object> paramMap) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        int startIndex = Integer.parseInt(paramMap.get("startIndex").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        paramMap.put("startIndex", startIndex);
        paramMap.put("pageSize", pageSize);
        List<Role> roleList = roleMapper.queryRolePage(paramMap);
        int total = roleMapper.countTotal(paramMap);
        retMap.put("roleList", roleList);
        retMap.put("total", total);
        return retMap;
    }


    @Override
    public int deleteByPrimaryKey(Long roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    @Override
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }
}
