package com.lja.oa.pojo;

import java.util.Date;

public class RoleUserRel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_user_rel.ROLE_USER_REL_ID
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    private Long roleUserRelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_user_rel.ROLE_ID
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    private Long roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_user_rel.USER_ID
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_user_rel.CREATED_DATE
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    private Date createdDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_user_rel.ROLE_USER_REL_ID
     *
     * @return the value of sys_role_user_rel.ROLE_USER_REL_ID
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    public Long getRoleUserRelId() {
        return roleUserRelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_user_rel.ROLE_USER_REL_ID
     *
     * @param roleUserRelId the value for sys_role_user_rel.ROLE_USER_REL_ID
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    public void setRoleUserRelId(Long roleUserRelId) {
        this.roleUserRelId = roleUserRelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_user_rel.ROLE_ID
     *
     * @return the value of sys_role_user_rel.ROLE_ID
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_user_rel.ROLE_ID
     *
     * @param roleId the value for sys_role_user_rel.ROLE_ID
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_user_rel.USER_ID
     *
     * @return the value of sys_role_user_rel.USER_ID
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_user_rel.USER_ID
     *
     * @param userId the value for sys_role_user_rel.USER_ID
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_user_rel.CREATED_DATE
     *
     * @return the value of sys_role_user_rel.CREATED_DATE
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_user_rel.CREATED_DATE
     *
     * @param createdDate the value for sys_role_user_rel.CREATED_DATE
     *
     * @mbggenerated Fri May 19 11:14:10 CST 2017
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}