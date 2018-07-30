<#if menuList??>
<table width="100%" border="1" cellpadding="0" cellspacing="0">

	<tr><th>菜单名称</th><th>菜单状态</th><th>操作</th></tr>
	
	<#if menuList?size == 0>
	   <tr><td colspan="3" style="text-align:center;">该角色下暂无已经授权的菜单信息</td></tr>
	<#else> 
	<#list menuList as menu>
	<tr>
		<td><#if menu.menuName??>${menu.menuName}</#if></td>
		<td>
		<#if menu.isPublish??>
		
		<#if 1==menu.isPublish>
	    	已发布
		</#if>
		
		<#if 2==menu.isPublish>
	    	未发布
		</#if>
		
		</#if>
		</td>
		
		 
		<td>
		<a  href="javascript:void();"  onclick="DeleteAuthorizationMenu('<#if menu.menuId??>${menu.menuId}</#if>')" style="cursor:pointer;")">删除</a>
		</td>
	</tr>
	</#list> 
	</#if>
</table>
</#if>

<script type="text/javascript">
	totalCount = ${total};
</script>
