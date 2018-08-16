package com.lja.oa.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.lja.oa.pojo.LeaveBill;

public interface ILeaveBillService {

	List<LeaveBill> getLeaveBillListByUserChName(String userChName);

	void saveLeaveBill(LeaveBill lb, HttpSession session);

	LeaveBill queryLeaveBillById(Long id);

	void deleteById(long id);

}
