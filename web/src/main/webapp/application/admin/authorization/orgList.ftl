<#if orgList??>
<table width="100%" border="1" cellpadding="0" cellspacing="0">

	<tr><th>组织名称</th><th>组织状态</th><th>显示顺序</th><th>操作</th></tr>
	
	<#if orgList?size == 0>
	   <tr><td colspan="4" style="text-align:center;">该角色下暂无已经授权的组织信息</td></tr>
	<#else> 
	<#list orgList as org>
	<tr>
		<td><#if org.orgName??>${org.orgName}</#if></td>
		<td>
		<#if org.state??>
		
		<#if 1==org.state>
	    	可用
		</#if>
		
		<#if 2==org.state>
	    	不可用
		</#if>
		
		</#if>
		</td>
		<td><#if org.dispIndex??>${org.dispIndex}</#if></td>
		
		<td>
		<a  href="javascript:void();"  onclick="DeleteAuthorizationOrg('<#if org.orgId??>${org.orgId}</#if>')" style="cursor:pointer;")">删除</a>
		</td>
	</tr>
	</#list> 
	</#if>
</table>
</#if>

<script type="text/javascript">
	totalCount = ${count};
</script>
