package com.lja.oa.dao;

import com.lja.oa.pojo.RoleMenuRel;

public interface RoleMenuRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Thu May 18 15:34:21 CST 2017
     */
    int deleteByPrimaryKey(Long roleMenuRel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Thu May 18 15:34:21 CST 2017
     */
    int insert(RoleMenuRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Thu May 18 15:34:21 CST 2017
     */
    int insertSelective(RoleMenuRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Thu May 18 15:34:21 CST 2017
     */
    RoleMenuRel selectByPrimaryKey(Long roleMenuRel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Thu May 18 15:34:21 CST 2017
     */
    int updateByPrimaryKeySelective(RoleMenuRel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu_rel
     *
     * @mbggenerated Thu May 18 15:34:21 CST 2017
     */
    int updateByPrimaryKey(RoleMenuRel record);
}