<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery框架 -->
<script type="text/javascript"  src="/static/plugin/jquery/jquery-1.9.1.js"></script>
<!-- 弹出框插件 -->
<script type="text/javascript" src="/static/plugin/layer/layer.min.js"></script>
<!--树  -->
<link rel="stylesheet" href="/static/plugin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/static/plugin/zTree/js/jquery.ztree.core.js"></script>

<!-- 区域级联 -->
<script type="text/javascript" src="/static/utils/getArea.js"></script>

<script type="text/javascript" src="/static/application/admin/menu/menuOper.js"></script>
<script type="text/javascript" src="/static/application/admin/menu/menuMana.js"></script>
<script type="text/javascript" src="/static/application/admin/menu/menuTree.js"></script>
<script type="text/javascript" src="/static/application/admin/menu/menuAddTree.js"></script>

</head>

<body style="background-color: #DCDCDC;">

	<table border="1" width="100%" >
		<tr height="100px">
			<td>
				<table border="1" width="100%" height="90px" >
					<tr height="30px">
				
				    <td width="30%">
				            所属目录：
						  <input type="text"  readonly="readonly" id="qry_menuParentName" >
						  <input type="hidden"   id="qry_menuParentId" >
						  <input type="button"   id="qry_menuTreeId"  onclick="QueryMenuTreeLayer();" value="选择">
				    </td>
				
					<td width="30%">菜单名称：<input type="text" id="qry_menuName"></td>
						<td width="30%">是否可用：
						<select id="qry_isPublish"  width="100"  style="width:140px;">
						<option value=''>请选择</option>
						<option value='1'>可用</option>
						<option value='2'>不可用</option>
						</select></td>
						
						
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
					    <button  onclick="javascript:menuOper.showMenuAddLayer();">新增</button>
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