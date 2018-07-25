package com.lja.oa.dao;

import com.lja.oa.pojo.Menu;
import com.lja.oa.pojo.Menus;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    //查询所有菜单信息
    public List<Menu> queryMenuPage(Map<String, Object> paramMap);
    public int countTotal(Map<String, Object> paramMap);
    public List<Menu> queryMenuDirListByMenuParentId(Map<String, Object> paramMap);
    public Menus selectMenusByPrimaryKey(long menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Sun Apr 09 13:13:38 CST 2017
     */
    int deleteByPrimaryKey(Long menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Sun Apr 09 13:13:38 CST 2017
     */
    int insert(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Sun Apr 09 13:13:38 CST 2017
     */
    int insertSelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Sun Apr 09 13:13:38 CST 2017
     */
    Menu selectByPrimaryKey(Long menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Sun Apr 09 13:13:38 CST 2017
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Sun Apr 09 13:13:38 CST 2017
     */
    int updateByPrimaryKey(Menu record);
}