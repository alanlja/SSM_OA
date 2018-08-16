package com.lja.oa.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.lja.oa.dao.LeaveBillMapper;
import com.lja.oa.pojo.LeaveBill;
import com.lja.oa.pojo.User;
import com.lja.oa.service.ILeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("leaveBillService")
public class LeaveBillServiceImpl implements ILeaveBillService {
	
	@Autowired
	private LeaveBillMapper leaveBillMapper;

	@Override
	public List<LeaveBill> getLeaveBillListByUserChName(String userChName) {
		return leaveBillMapper.getLeaveBillListByUserChName(userChName);
	}

	@Override
	public void saveLeaveBill(LeaveBill lb, HttpSession session) {
		//得到用户对象
		User user = (User) session.getAttribute("user");
		//得到请假单的id
		Long id = lb.getId();
		if(id==null){
			lb.setUser(user);
			//新增
			leaveBillMapper.addLeaveBill(lb);
		}else{
			//修改
			leaveBillMapper.updateLeaveBill(lb);
		}
	}

	@Override
	public LeaveBill queryLeaveBillById(Long id) {
		return leaveBillMapper.queryLeaveBillById(id);
	}

	@Override
	public void deleteById(long id) {
		leaveBillMapper.deleteById(id);
	}

}
