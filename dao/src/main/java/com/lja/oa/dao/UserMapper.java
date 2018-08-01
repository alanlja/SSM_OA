package com.lja.oa.dao;

import com.lja.oa.pojo.User;
import com.lja.oa.pojo.Users;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Wed Nov 08 11:16:16 CST 2017
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Wed Nov 08 11:16:16 CST 2017
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Wed Nov 08 11:16:16 CST 2017
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Wed Nov 08 11:16:16 CST 2017
     */
    User selectByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Wed Nov 08 11:16:16 CST 2017
     * 修改用户信息
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated Wed Nov 08 11:16:16 CST 2017
     */
    int updateByPrimaryKey(User record);

    List<User> getUserList(Map<String, Object> paramMap);

    int getCount(Map<String, Object> paramMap);

    Users queryUserById(int userId);

    List<Map<String, Object>> getUserSexStaticties();

    List<Map<String, Object>> getProvincePersonStaticties();

    List<User> getUserByOrgId(int orgId);

    User checkUserIsExits(User user);

    String getRoleIdsByUserId(Long userId);

    String getRoleIdsByOrgId(Long orgId);

    void insertSheetData(List<Map<String, Object>> list);

    List<Map<String, Object>> getColumnList();
}
