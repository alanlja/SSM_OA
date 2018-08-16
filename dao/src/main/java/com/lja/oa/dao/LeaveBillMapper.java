package com.lja.oa.dao;

import java.util.List;
import java.util.Map;

import com.lja.oa.pojo.LeaveBill;

public interface LeaveBillMapper {

	List<LeaveBill> getLeaveBillListByUserChName(String userChName);

	void addLeaveBill(LeaveBill lb);

	void updateLeaveBill(LeaveBill lb);

	LeaveBill queryLeaveBillById(Long id);

	void deleteById(long id);

	void updateLeaveBillState(Map<String,Object> map);

}
