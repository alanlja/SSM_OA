<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/navbar.js"></script>
	<link type="text/css" rel="stylesheet" href="/css/public.css"/>
<title>部署管理</title>
</head>
<body>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="bottom"><div align="center"><img src="/images/tb.gif" width="14" height="14" /></div></td>
		                <td width="94%" valign="bottom"><span class="STYLE1">部署信息管理列表</span></td>
		              </tr>
		            </table></td>
		            <td><div align="right"><span class="STYLE1">
		              </span></div></td>
		          </tr>
		        </table></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
		      <tr>
		        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">ID</span></div></td>
		        <td width="60%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">流程名称</span></div></td>
		        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">发布时间</span></div></td>
		        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
		      </tr>
		    
		      	<#if depList??>
		      		<#list depList as dep>
		      		<tr>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><#if dep.id??>${dep.id}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><#if dep.name??>${dep.name}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">
				        <#if dep.deploymentTime??>${dep.deploymentTime?string("yyyy-MM-dd HH:mm:ss")}</#if> 
				        </div></td>
				        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
				        	<a href="/workflow/delDeployment?deploymentId=<#if dep.id??>${dep.id}</#if>">删除</a>
				        </div></td>
				    </tr> 
				    </#list>
		      	</#if>
		    </table></td>
		  </tr>
		</table>
		<hr>
		<br/>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="bottom"><div align="center"><img src="/images/tb.gif" width="14" height="14" /></div></td>
		                <td width="94%" valign="bottom"><span class="STYLE1">流程定义信息列表</span></td>
		              </tr>
		            </table></td>
		            <td><div align="right"><span class="STYLE1">
		              </span></div></td>
		          </tr>
		        </table></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
		      <tr>
		        <td width="12%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">ID</span></div></td>
		        <td width="18%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">名称</span></div></td>
		        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">流程定义的KEY</span></div></td>
		        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">流程定义的版本</span></div></td>
		        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">流程定义的规则文件名称</span></div></td>
		        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">流程定义的规则图片名称</span></div></td>
		        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">部署ID</span></div></td>
		        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
		      </tr>
		      
		      <#if pdList??>
		      	<#list pdList as pd>
		      		<tr>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><#if pd.id??>${pd.id}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><#if pd.name??>${pd.name}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><#if pd.key??>${pd.key}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><#if pd.version??>${pd.version}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><#if pd.resourceName??>${pd.resourceName}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><#if pd.diagramResourceName??>${pd.diagramResourceName}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><#if pd.deploymentId??>${pd.deploymentId}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
				        	<a target="_blank" href="/workflow/proccessImage?deploymentId=<#if pd.deploymentId??>${pd.deploymentId}</#if>&imageName=<#if pd.diagramResourceName??>${pd.diagramResourceName}</#if>">查看流程图</a>
					 	</div></td>
				    </tr> 
		      </#list>
		      </#if>
		    </table></td>
		  </tr>
	</table>
	<hr>
	<br/>
	<!-- 发布流程 -->
	<form action="/workflow/newdeploy" enctype="multipart/form-data" method="POST">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="bottom"><div align="center"><img src="/images/tb.gif" width="14" height="14" /></div></td>
		                <td width="94%" valign="bottom"><span class="STYLE1">部署流程定义</span></td>
		              </tr>
		            </table></td>
		            <td><div align="right"><span class="STYLE1">
		              </span></div></td>
		          </tr>
		        </table></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		    <td>
		    	<div align="left" class="STYLE21">
					流程名称：<input type="text" name="filename" style="width: 200px;"/><br/>
					流程文件:<input type="file" name="file" style="width: 200px;"/><br/>
					<input type="submit" value="上传流程" class="button_ok"/>
				</div>
		    </td>
		  </tr>
	</table>
		
	</form>
</body>
</html>