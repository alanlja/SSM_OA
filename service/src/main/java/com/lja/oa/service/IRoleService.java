package com.lja.oa.service;

import com.lja.oa.pojo.Role;

import java.util.List;
import java.util.Map;

public interface IRoleService {
    public Map<String, Object> queryRolePage(Map<String, Object> paramMap);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbggenerated Mon Mar 13 16:11:18 CST 2017
     */
    int deleteByPrimaryKey(Long orgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbggenerated Mon Mar 13 16:11:18 CST 2017
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbggenerated Mon Mar 13 16:11:18 CST 2017
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbggenerated Mon Mar 13 16:11:18 CST 2017
     */
    Role selectByPrimaryKey(Long orgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbggenerated Mon Mar 13 16:11:18 CST 2017
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_org
     *
     * @mbggenerated Mon Mar 13 16:11:18 CST 2017
     */
    int updateByPrimaryKey(Role record);

    public List<Role> getRoleList();

    public List<Role> queryRoleList();
}
