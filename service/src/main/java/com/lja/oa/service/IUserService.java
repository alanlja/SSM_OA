package com.lja.oa.service;

import com.lja.oa.pojo.User;
import com.lja.oa.pojo.Users;

import java.util.Map;

public interface IUserService {
    Map<String, Object> getPage(Map<String, Object> paramMap);

    void addUser(User user);

    Users queryUserById(int userId);

    void updateUser(User user);

    void delUserById(long userId);
}
