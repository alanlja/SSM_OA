package com.lja.oa.controller;

import javax.servlet.http.HttpSession;

import com.lja.oa.pojo.LeaveBill;
import com.lja.oa.pojo.User;
import com.lja.oa.service.ILeaveBillService;
import common.WorkflowBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/leaveBill")
public class LeaveBillController {
	
	@Autowired
	private ILeaveBillService leaveBillService;

	@RequestMapping("/leaveBill_main")
	public ModelAndView getLeaveBill(HttpSession session){
		ModelAndView result = new ModelAndView("views/leaveBill/list");
		User user = (User) session.getAttribute("user");
		String userChName = user.getUserChName();
		List<LeaveBill> lbList = leaveBillService.getLeaveBillListByUserChName(userChName);
		result.addObject("lbList", lbList);
		return result;
	}
	
	/**
	 * 跳转到添加请假申请的页面
	 */
	@RequestMapping("/toAddLeaveBill")
	public String toAddLeaveBill(){
		return "views/leaveBill/input";
	}
	
	/**
	 * 新增请假申请，修改请假申请
	 */
	@RequestMapping("/saveLeaveBill")
	public String saveLeaveBill(LeaveBill lb,HttpSession session){
		leaveBillService.saveLeaveBill(lb,session);
		return "redirect:/leaveBill/leaveBill_main";
	}
	
	/**
	 * 点击修改信息，数据回显操作
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(WorkflowBean bean){
		ModelAndView result = new ModelAndView("views/leaveBill/input");
		Long id = bean.getId();
		LeaveBill lb = leaveBillService.queryLeaveBillById(id);
		result.addObject("lb", lb);
		return result;
	}
	
	/**
	 * 删除 请假单
	 */
	@RequestMapping("/delete")
	public String delete(long id){
		leaveBillService.deleteById(id);
		return "redirect:/leaveBill/leaveBill_main";
	}
}
