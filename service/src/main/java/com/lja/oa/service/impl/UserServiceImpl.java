package com.lja.oa.service.impl;

import com.lja.oa.dao.UserMapper;
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
}
