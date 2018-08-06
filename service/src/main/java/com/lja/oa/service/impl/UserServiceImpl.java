package com.lja.oa.service.impl;

import com.lja.oa.dao.RoleUserRelMapper;
import com.lja.oa.dao.UserMapper;
import com.lja.oa.pojo.RoleMenuRel;
import com.lja.oa.pojo.RoleUserRel;
import com.lja.oa.pojo.User;
import com.lja.oa.pojo.Users;
import com.lja.oa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("areaService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleUserRelMapper relMapper;

    @Override
    public Map<String, Object> getPage(Map<String, Object> paramMap) {
        int startIndex = Integer.parseInt(paramMap.get("startIndex").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        paramMap.put("startIndex",startIndex);
        paramMap.put("pageSize",pageSize);

        List<User> userList = userMapper.getUserList(paramMap);
        int count = userMapper.getCount(paramMap);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("userList",userList);
        result.put("count",count);
        return result;
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public Users queryUserById(int userId) {
        return userMapper.queryUserById(userId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delUserById(long userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public List<Map<String, Object>> getUserSexStaticties() {
        return userMapper.getUserSexStaticties();
    }

    @Override
    public List<Map<String, Object>> getProvincePersonStaticties() {
        return userMapper.getProvincePersonStaticties();
    }

    @Override
    public List<User> getUserByOrgId(int orgId) {
        return userMapper.getUserByOrgId(orgId);
    }

    @Override
    public void addRoleUserRel(RoleUserRel rel) {
        relMapper.insert(rel);
    }

    @Override
    public User checkUserIsExits(User user) {
        return userMapper.checkUserIsExits(user);
    }

    @Override
    public String getRoleIdsByUserId(Long userId) {
        return userMapper.getRoleIdsByUserId(userId);
    }

    @Override
    public String getRoleIdsByOrgId(Long orgId) {
        return userMapper.getRoleIdsByOrgId(orgId);
    }

    @Override
    public void insertSheetData(List<Map<String, Object>> list) {
        userMapper.insertSheetData(list);
    }

    @Override
    public List<Map<String, Object>> getColumnList() {
        return userMapper.getColumnList();
    }

    @Override
    public Map<String, Object> queryUserPage(Map<String, Object> paramMap) {
        int startIndex = Integer.parseInt(paramMap.get("startIndex").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        paramMap.put("startIndex", startIndex);
        paramMap.put("pageSize", pageSize);

        List<User> userList = userMapper.getAuthUserList(paramMap);
        int count = userMapper.getTotalCount(paramMap);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userList", userList);
        resultMap.put("count", count);
        return resultMap;
    }

    @Override
    public void delUserRoleRel(RoleUserRel rel) {
        relMapper.delUserRoleRel(rel);
    }
}
