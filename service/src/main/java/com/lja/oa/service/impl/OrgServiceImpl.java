package com.lja.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lja.oa.dao.OrgMapper;
import com.lja.oa.dao.RoleOrgRelMapper;
import com.lja.oa.pojo.Org;
import com.lja.oa.pojo.Orgs;
import com.lja.oa.pojo.RoleOrgRel;
import com.lja.oa.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orgService")
public class OrgServiceImpl implements IOrgService {
	@Autowired
	private OrgMapper orgMapper;

	@Autowired
	private RoleOrgRelMapper relMapper;
	
	@Override
	public List<Org> queryOrgListByOrgParentId(Map<String, Object> map) {
		return orgMapper.queryOrgListByOrgParentId(map);
	}

	@Override
	public Map<String, Object> getOrgPage(Map<String, Object> paramMap) {
		int startIndex = Integer.parseInt(paramMap.get("startIndex").toString());
		int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
		
		paramMap.put("startIndex", startIndex);
		paramMap.put("pageSize", pageSize);
		
		List<Org> orgList = orgMapper.getPage(paramMap);
		int count = orgMapper.getCount(paramMap);
		
		Map<String, Object> resutMap = new HashMap<>();
		resutMap.put("orgList", orgList);
		resutMap.put("count", count);
		return resutMap;
	}

	@Override
	public void addOrg(Org org) {
		orgMapper.insert(org);
	}

	@Override
	public Orgs queryOrgById(int orgId) {
		return orgMapper.queryOrgById(orgId);
	}

	@Override
	public void updateOrg(Org org) {
		orgMapper.updateByPrimaryKey(org);
	}

	@Override
	public void delOrg(long orgId) {
		orgMapper.deleteByPrimaryKey(orgId);
	}

	@Override
	public Map<String, Object> queryOrgPage(Map<String, Object> paramMap) {
		int startIndex = Integer.parseInt(paramMap.get("startIndex").toString());
		int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
		paramMap.put("startIndex", startIndex);
		paramMap.put("pageSize", pageSize);

		List<Org> orgList = orgMapper.getOrgList(paramMap);
		int count = orgMapper.getTotalCount(paramMap);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("orgList", orgList);
		resultMap.put("count", count);
		return resultMap;
	}

	@Override
	public void delOrgRoleRel(RoleOrgRel rel) {
		relMapper.delOrgRoleRel(rel);
	}

	@Override
	public void addRoleOrgRel(RoleOrgRel rel) {
		relMapper.insert(rel);
	}
}