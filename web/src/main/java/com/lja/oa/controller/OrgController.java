package com.lja.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lja.oa.pojo.Org;
import com.lja.oa.pojo.Orgs;
import com.lja.oa.service.IOrgService;
import common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/org")
public class OrgController extends BaseController {
	
	@Autowired
	private IOrgService orgService;
	
	/**
	 * 跳转到组织管理的主页面
	 * @return
	 */
	@RequestMapping("/orgMana")
	public String toOrgMana(){
		return "org/orgMana";
	}

	@RequestMapping("/queryOrgListByOrgParentId")
	public void queryOrgListByOrgParentId(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = this.getParam(request);	
		List<Org> list = orgService.queryOrgListByOrgParentId(map);
		
		Gson gson = new Gson();
		String responseContent = gson.toJson(list);
		
		this.flushResponse(response, responseContent);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/orgPage")
	public ModelAndView getOrgPage(HttpServletRequest request){
		Map<String, Object> paramMap = this.getParam(request);
		//分页的list  count
		Map<String, Object> resultMap = orgService.getOrgPage(paramMap);
		
		List<Org> orgList = (List<Org>) resultMap.get("orgList");
		int count = Integer.parseInt(resultMap.get("count").toString());
		
		ModelAndView result = new ModelAndView("org/orgList");
		result.addObject("orgList", orgList);
		result.addObject("count", count);
		return result;
	}
	/**
	 * 展示页码
	 * @return
	 */
	@RequestMapping("/getPageNumber")
	public ModelAndView getOrgPageNumber(HttpServletRequest request){
		ModelAndView result = new ModelAndView("org/orgPageNumber");
		int total = Integer.parseInt(request.getParameter("total"));
		int startIndex = Integer.parseInt(request.getParameter("startIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		return this.getPageNumberInfo(total, startIndex, pageSize, result);
	}
	
	/**
	 * 新增组织信息
	 */
	@RequestMapping("/addOrg")
	public void addOrg(Org org,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			orgService.addOrg(org);
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
	 * 通过orgId查询组织信息(Orgs)
	 * @param orgId
	 * @param response
	 */
	@RequestMapping("/queryOrgById")
	public void queryOrgById(int orgId,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Orgs org = orgService.queryOrgById(orgId);
		resultMap.put("org", org);
		Gson gson = new Gson();
		String responseContent = gson.toJson(resultMap);
		this.flushResponse(response, responseContent);
	}

	/**
	 * 修改组织信息
	 */
	@RequestMapping("/updateOrg")
	public void updateOrg(Org org,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			orgService.updateOrg(org);
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
	 * 删除 组织信息
	 */
	@RequestMapping("/delOrg")
	public void delOrg(long orgId,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			orgService.delOrg(orgId);
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
