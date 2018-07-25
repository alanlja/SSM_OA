<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"  src="/static/plugin/jquery/jquery-1.9.1.js"></script>
<!-- 弹出框插件 -->
<script type="text/javascript" src="/static/plugin/layer/layer.min.js"></script>
<!-- 引入树的插件  -->
<link rel="stylesheet" href="/static/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/static/plugin/zTree/js/jquery.ztree.core.js"></script>

<script type="text/javascript"  src="/static/application/admin/role/roleOper.js"></script>
<script type="text/javascript"  src="/static/application/admin/role/roleMana.js"></script>

</script>

</head>


<body style="background-color: #DCDCDC;">

	<table border="1" width="100%" >
		<tr height="100px">
			<td>
				<table border="1" width="100%" height="90px" >
					<tr height="30px">
						 <td width="30%">角色名称：<input type="text" id="qry_roleName"></td>
						<td width="30%">状态：
						<select id="qry_state"  width="100"  style="width:140px;">
						<option value=''>请选择</option>
						<option value='1'>可用</option>
						<option value='2'>不可用</option>
						</select></td>
						<td width="30%"></td>
					</tr>
					<tr  height="30px">
					   <td width="30%"></td>
					   <td width="30%"></td>
						<td width="30%" style="text-align:center;">
						<button id="queryButton">查询</button>
						   &nbsp;&nbsp;&nbsp;
						<button id="resetButton">重置</button></td>
					</tr>
						<tr  height="30px">
					    <td width="100%" colspan="3" style="text-align:left;">
					    <button  onclick="javascript:roleOper.alertaddRole();">新增</button>
					    </td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td ><div id="pageListContainer" ></div></td>
		</tr>
		<tr height="50px">
			<td><div id="pageNumberToolBar" height="40px" style="text-align:center;"></div></td>
		</tr>
	</table>

</body>
</html>