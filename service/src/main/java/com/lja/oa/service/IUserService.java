package com.lja.oa.service;

import com.lja.oa.pojo.RoleUserRel;
import com.lja.oa.pojo.User;
import com.lja.oa.pojo.Users;

import java.util.List;
import java.util.Map;

public interface IUserService {
    Map<String, Object> getPage(Map<String, Object> paramMap);

    void addUser(User user);

    Users queryUserById(int userId);

    void updateUser(User user);

    void delUserById(long userId);

    List<Map<String, Object>> getUserSexStaticties();

    List<Map<String, Object>> getProvincePersonStaticties();

    List<User> getUserByOrgId(int orgId);

    void addRoleUserRel(RoleUserRel rel);
}
