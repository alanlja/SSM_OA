package com.lja.oa.service;

import com.lja.oa.pojo.Org;
import com.lja.oa.pojo.Orgs;

import java.util.List;
import java.util.Map;

public interface IOrgService {
	//orgParentId=1
	//['orgParent':'1']
	public List<Org> queryOrgListByOrgParentId(Map<String, Object> map);

	public Map<String, Object> getOrgPage(Map<String, Object> paramMap);

	public void addOrg(Org org);

	public Orgs queryOrgById(int orgId);

	public void updateOrg(Org org);

	public void delOrg(long orgId);
}
