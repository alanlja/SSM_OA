<#if menuList??>
<table width="100%" border="1" cellpadding="0" cellspacing="0">

	<tr><th>菜单名称</th><th>菜单状态</th><th>操作</th></tr>
	
	<#if menuList?size == 0>
	   <tr><td colspan="4" style="text-align:center;">暂无菜单信息</td></tr>
	<#else> 
	<#list menuList as menu>
	<tr>
		<td><#if menu.menuName??>${menu.menuName}</#if></td>
		<td>
		<#if menu.isPublish??>
		
		<#if 1==menu.isPublish>
	    	可用
		</#if>
		
		<#if 2==menu.isPublish>
	    	不可用
		</#if>
		
		</#if>
		</td>

		<td>
		<a  href="javascript:void(0);"  onClick="javascript:menuOper.editMenu('<#if menu.menuId??>${menu.menuId}</#if>');" style="cursor:pointer;">编辑</a>
		<a  href="javascript:void(0);"  onClick="javascript:menuOper.showMenu('<#if menu.menuId??>${menu.menuId}</#if>');" style="cursor:pointer;">查看</a>
		<a  href="javascript:void(0);"  onClick="javascript:menuOper.deleteMenu('<#if menu.menuId??>${menu.menuId}</#if>');" style="cursor:pointer;">删除</a>
		</td>
	</tr>
	</#list> 
	</#if>
</table>
</#if>

<script type="text/javascript">
	totalCount = ${total};
</script>
