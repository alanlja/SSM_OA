<#if roleList??>
<table width="100%" border="1" cellpadding="0" cellspacing="0">

	<tr>
		<th>角色 名称</th>
		<th>角色状态</th>
		<th>操作</th>
	</tr>

<#if roleList?size==0>
	<tr>
		<td colspan="4" style="text-align: center;">暂无组织信息</td>
	</tr>
<#else>
<#list roleList as role>
	<tr>
		<td><#if role.roleName??>${role.roleName}</#if></td>
		<td><#if role.state??>
		<#if role.state==1>可用</#if>
		<#if role.state==2>不可用</#if>
		</#if></td>
		<td><a href="javascript:void();" onclick="javascript:roleOper.editRole('<#if role.roleId??>${role.roleId}</#if>')" style="cursor: pointer;">编辑</a>
			<a href="javascript:void();" onclick="javascript:roleOper.showRole('<#if role.roleId??>${role.roleId}</#if>')" style="cursor: pointer;">查看</a> <a
			href="javascript:void();" onclick="javascript:roleOper.deleteRole('<#if role.roleId??>${role.roleId}</#if>')" style="cursor: pointer;">删除</a></td>
	</tr>
</#list>
</#if>
</table>
</#if>

<script type="text/javascript">
	totalCount = "<#if total??>${total}</#if>"; 
</script>
