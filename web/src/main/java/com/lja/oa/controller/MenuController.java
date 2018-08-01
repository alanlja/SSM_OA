package com.lja.oa.controller;

import com.lja.oa.pojo.Menu;
import com.lja.oa.pojo.User;
import com.lja.oa.service.IMenuService;
import com.lja.oa.service.IUserService;
import common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Resource(name = "menuServiceImpl")
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/menuMana")
    public ModelAndView toMenuMana (HttpServletRequest request , HttpServletResponse response){
        ModelAndView result = new ModelAndView("menu/menuMana");
        return result;
    }

    // 分页显示菜单信息
    @SuppressWarnings("unchecked")
    @RequestMapping("/menuPage")
    public ModelAndView menuPage(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> paramMap = this.getParam(request);
        Map<String, Object> retMap = menuService.queryMenuPage(paramMap);

        List<Menu> menuList = (List<Menu>) retMap.get("menuList");
        long total = Long.parseLong(retMap.get("total").toString());

        ModelAndView result = new ModelAndView("menu/menuList");
        result.addObject("menuList", menuList);
        result.addObject("total", total);

        return result;
    }

    // 展示页码信息
    @RequestMapping("/getPageNumber")
    public ModelAndView getPageNumber(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView result = new ModelAndView("menu/menuPageNumber");
        int total = Integer.parseInt(request.getParameter("total"));
        int startIndex = Integer.parseInt(request.getParameter("startIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        result = this.getPageNumberInfo(total, startIndex, pageSize, result);
        return result;
    }

    /**
     * 登录用户看到对应的菜单列表
     */
    @RequestMapping("/getAuthoMenuList")
    public ModelAndView getAuthoMenuList(HttpServletRequest request){
        ModelAndView result = null;

        //一个员工能看到的菜单=这个员工对应的角色能看到的菜单+这个员工所在的部门对应的角色能看到的菜单
        User user = (User) request.getSession().getAttribute("user");
        //得到userId
        Long userId = user.getUserId();
        //得到orgId
        Long orgId = user.getOrgId();

        //去角色用户关系表中，通过userId查询roleId
        String userRoleIds = userService.getRoleIdsByUserId(userId);
        //去角色组织关系表中，通过orgId查询roleId
        String orgRoleIds = userService.getRoleIdsByOrgId(orgId);
        //1,5,7
        String roleIds = null;
        if(userRoleIds!=null&&!("").equals(userRoleIds)&&orgRoleIds!=null&&!("").equals(orgRoleIds)){
            roleIds =userRoleIds+","+orgRoleIds;
        }else if(userRoleIds!=null&&!("").equals(userRoleIds)){
            roleIds=userRoleIds;
        }else if(orgRoleIds!=null&&!("").equals(orgRoleIds)){
            roleIds = orgRoleIds;
        }else{

        }

        List<Menu> menuList = new ArrayList<>();
        //拆分
        if(roleIds.length()>0){
            Map<String, Object> map = new HashMap<>();
            String[] roleIdArray = roleIds.split(",");
            map.put("roleIds", roleIdArray);
            menuList = menuService.getMenuListByRoleIds(map);
            result = new ModelAndView("common/left");
            result.addObject("menuList", menuList);
        }
        return result;
    }
}
