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

    User checkUserIsExits(User user);

    String getRoleIdsByUserId(Long userId);

    String getRoleIdsByOrgId(Long orgId);

    void insertSheetData(List<Map<String, Object>> list);

    List<Map<String, Object>> getColumnList();

    public Map<String,Object> queryUserPage(Map<String,Object> paramMap);

    public void delUserRoleRel(RoleUserRel rel);
}
