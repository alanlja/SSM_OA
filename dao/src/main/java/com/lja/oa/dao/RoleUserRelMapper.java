package com.lja.oa.dao;

import com.lja.oa.pojo.RoleUserRel;

public interface RoleUserRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    int deleteByPrimaryKey(Long roleUserRelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    int insert(RoleUserRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    int insertSelective(RoleUserRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    RoleUserRel selectByPrimaryKey(Long roleUserRelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    int updateByPrimaryKeySelective(RoleUserRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_user_rel
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    int updateByPrimaryKey(RoleUserRel record);

    void delUserRoleRel(RoleUserRel rel);
}