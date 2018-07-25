package com.lja.oa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lja.oa.pojo.Menu;
import com.lja.oa.pojo.Org;
import com.lja.oa.service.IMenuService;
import com.lja.oa.service.IOrgService;
import common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tree")
public class TreeController extends BaseController {
	
	@Autowired
	private IOrgService orgService;

	@Autowired
	private IMenuService menuService;
	
	@RequestMapping("/orgSubList")
	public void getOrgSubList(HttpServletRequest request,HttpServletResponse response){
		String orgParentId = request.getParameter("id");
		if(orgParentId==null || "".equals(orgParentId)){
			orgParentId="-1";			
		}
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orgParentId", orgParentId);
		
		List<Map<String, Object>> list = new ArrayList<>();
		List<Org> orgList = orgService.queryOrgListByOrgParentId(paramMap);
		for(int i=0;i<orgList.size();i++){
			Map<String, Object> orgMap = new HashMap<>();
			Org org = orgList.get(i);
			orgMap.put("id", org.getOrgId());
			orgMap.put("name", org.getOrgName());
			orgMap.put("isParent", true);
			list.add(orgMap);
		}

		Gson gson = new Gson();
		String responseContent = gson.toJson(list);
		this.flushResponse(response, responseContent);
	}

	// 展示菜单类型为目录的
	@RequestMapping("/menuDirSubList")
	public void menuDirSubList(HttpServletRequest request, HttpServletResponse response) {
		String menuParentId = request.getParameter("id");
		if (menuParentId == null || "".equals(menuParentId)) {
			menuParentId = "-1";
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuParentId", menuParentId);

		List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
		List<Menu> menuList = menuService.queryMenuDirListByMenuParentId(paramMap);
		for (int i = 0; i < menuList.size(); i++) {
			Map<String, Object> retMap = new HashMap<String, Object>();
			Menu menu = menuList.get(i);
			retMap.put("id", menu.getMenuId());
			retMap.put("name", menu.getMenuName());
			// isParent树的一个属性，true表示为目录图标,false表示文件图标
			retMap.put("isParent", true);
			retList.add(retMap);
		}
		Gson gson = new Gson();
		String resultJson = gson.toJson(retList);
		this.flushResponse(response, resultJson);
	}
}
