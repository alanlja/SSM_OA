package com.lja.oa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lja.oa.pojo.Org;
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
}
