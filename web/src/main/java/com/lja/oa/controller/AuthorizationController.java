package com.lja.oa.controller;

import com.google.gson.Gson;
import com.lja.oa.pojo.*;
import com.lja.oa.service.IMenuService;
import com.lja.oa.service.IOrgService;
import com.lja.oa.service.IRoleService;
import com.lja.oa.service.IUserService;
import common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthorizationController extends BaseController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IOrgService orgService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/authorizationMana")
    public String toMana() {
        return "authorization/authorizationMana";
    }

    /**
     * 查询角色的集中信息
     */
    @RequestMapping("/queryRoleList")
    public void queryRoleList (HttpServletResponse response){
        List<Role> roleList = roleService.queryRoleList();
        Gson gson = new Gson();
        String responseContent = gson.toJson(roleList);
        this.flushResponse(response,responseContent);
    }

    /**
     * 查询授权组织分页信息
     */
    @RequestMapping("/queryOrgPage")
    public ModelAndView queryOrgPage(HttpServletRequest request){
        ModelAndView result = new ModelAndView("authorization/orgList");
        Map<String,Object> paramMap =  this.getParam(request);
        Map<String,Object> resultMap =  orgService.queryOrgPage(paramMap);

        List<Org> orgList = (List<Org>) resultMap.get("orgList");
        int count = Integer.parseInt(resultMap.get("count").toString());
        result.addObject("orgList", orgList);
        result.addObject("count", count);
        return result;
    }

    /**
     * 删除授权组织信息
     */
    @RequestMapping("/delOrgRoleRel")
    public void delOrgRoleRel(RoleOrgRel rel, HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            orgService.delOrgRoleRel(rel);
            resultMap.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isSuccess", false);
        }
        Gson gson = new Gson();
        String responseContent = gson.toJson(resultMap);
        this.flushResponse(response, responseContent);
    }

    /**
     * 授权菜单
     */
    @RequestMapping("/addRoleMenuRel")
    public void addRoleMenuRel(RoleMenuRel rel, HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            menuService.addRoleMenuRel(rel);
            resultMap.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isSuccess", false);
        }
        Gson gson = new Gson();
        String responseContent = gson.toJson(resultMap);
        this.flushResponse(response, responseContent);
    }

    /**
     * 授权组织
     */
    @RequestMapping("/addRoleOrgRel")
    public void addRoleOrgRel(RoleOrgRel rel,HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            orgService.addRoleOrgRel(rel);
            resultMap.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isSuccess", false);
        }
        Gson gson = new Gson();
        String responseContent = gson.toJson(resultMap);
        this.flushResponse(response, responseContent);
    }

    /**
     * 授权人员
     *
     */
    @RequestMapping("/addRoleUserRel")
    public void addRoleUserRel(RoleUserRel rel, HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            userService.addRoleUserRel(rel);
            resultMap.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isSuccess", false);
        }
        Gson gson = new Gson();
        String responseContent = gson.toJson(resultMap);
        this.flushResponse(response, responseContent);
    }
}