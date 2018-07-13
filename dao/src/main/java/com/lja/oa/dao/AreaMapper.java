package com.lja.oa.dao;

import com.lja.oa.pojo.Area;

import java.util.List;

public interface AreaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_area
     *
     * @mbggenerated Thu Nov 24 11:14:06 CST 2016
     */
    int deleteByPrimaryKey(Long areaPriId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_area
     *
     * @mbggenerated Thu Nov 24 11:14:06 CST 2016
     */
    int insert(Area record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_area
     *
     * @mbggenerated Thu Nov 24 11:14:06 CST 2016
     */
    int insertSelective(Area record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_area
     *
     * @mbggenerated Thu Nov 24 11:14:06 CST 2016
     */
    Area selectByPrimaryKey(Long areaPriId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_area
     *
     * @mbggenerated Thu Nov 24 11:14:06 CST 2016
     */
    int updateByPrimaryKeySelective(Area record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_area
     *
     * @mbggenerated Thu Nov 24 11:14:06 CST 2016
     */
    int updateByPrimaryKey(Area record);

    List<Area> selectByAreaParentId(String areaParentId);
}
