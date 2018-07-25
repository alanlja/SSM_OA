package com.lja.oa.controller;

import com.lja.oa.pojo.Menu;
import com.lja.oa.service.IMenuService;
import common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
    @Resource(name = "menuServiceImpl")
    private IMenuService menuService;

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
}
