package com.lja.oa.service.impl;

import com.lja.oa.dao.MenuMapper;
import com.lja.oa.dao.RoleMenuRelMapper;
import com.lja.oa.pojo.Menu;
import com.lja.oa.pojo.Menus;
import com.lja.oa.pojo.RoleMenuRel;
import com.lja.oa.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "menuServiceImpl")
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuRelMapper relMapper;

    @Override
    public Map<String, Object> queryMenuPage(Map<String, Object> paramMap) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        int startIndex = Integer.parseInt(paramMap.get("startIndex").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        paramMap.put("startIndex", startIndex);
        paramMap.put("pageSize", pageSize);
        List<Menu> menuList = menuMapper.queryMenuPage(paramMap);
        int total = menuMapper.countTotal(paramMap);
        retMap.put("menuList", menuList);
        retMap.put("total", total);
        return retMap;
    }


    @Override
    public List<Menu> queryMenuDirListByMenuParentId(Map<String, Object> paramMap) {
        return menuMapper.queryMenuDirListByMenuParentId(paramMap);
    }

    @Override
    public int deleteByPrimaryKey(Long menuId) {
        return menuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public int insert(Menu record) {
        return menuMapper.insert(record);
    }

    @Override
    public int insertSelective(Menu record) {
        return menuMapper.insertSelective(record);
    }

    @Override
    public Menu selectByPrimaryKey(Long menuId) {
        return menuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public int updateByPrimaryKeySelective(Menu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return menuMapper.updateByPrimaryKey(record);
    }

    @Override
    public Menus selectMenusByPrimaryKey(long menuId) {
        return menuMapper.selectMenusByPrimaryKey(menuId);
    }

    @Override
    public List<Menu> queryMenuListByMenuParentId(Map<String, Object> paramMap) {
        return menuMapper.queryMenuListByMenuParentId(paramMap);
    }

    @Override
    public void addRoleMenuRel(RoleMenuRel rel) {
        relMapper.insert(rel);
    }

    @Override
    public List<Menu> getMenuListByRoleIds(Map<String, Object> map) {
        return menuMapper.getMenuListByRoleIds(map);
    }
}
