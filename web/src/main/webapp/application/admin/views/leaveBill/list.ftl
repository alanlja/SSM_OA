<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/navbar.js"></script>
	<link type="text/css" rel="stylesheet" href="/css/public.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假管理</title>
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
		                <td width="94%" valign="bottom"><span class="STYLE1">请假申请列表列表</span></td>
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
		        <td height="20" bgcolor="#FFFFFF" class="STYLE10" colspan="8"><div align="left">
					<a href="/leaveBill/toAddLeaveBill">添加请假申请</a>
				</div></td>
		  </tr> 
		  <tr>
		    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
		      <tr>
		        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">ID</span></div></td>
		        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假人</span></div></td>
		        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假天数</span></div></td>
		        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假事由</span></div></td>
		        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假备注</span></div></td>
		        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假时间</span></div></td>
		        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">请假状态</span></div></td>
		        <td width="25%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
		      </tr>     
		      	<#if lbList??>
		      		<#list lbList as lb>
		      		<tr>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><#if lb.id??>${lb.id}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><#if lb.user.userChName??>${lb.user.userChName}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><#if lb.days??>${lb.days}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><#if lb.content??>${lb.content}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><#if lb.remark??>${lb.remark}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19">
				        <div align="center">
				        <#if lb.leaveDate??>${lb.leaveDate?string("yyyy-MM-dd HH:mm:ss")}</#if>
				        </div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19">
				        	<div align="center">	        
				        	<#if lb.state == 0>
				        		初始录入
				        	</#if>
				        	<#if lb.state == 1>
				        		审核中
				        	</#if>
				        	<#if lb.state == 2>
				        		审核完成
				        	</#if>
			            	</div>
			            </td>
				        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
				        	<#if lb.state == 0>
			        			<a href="/leaveBill/toUpdate?id=${lb.id}" >修改</a>
								<a href="/leaveBill/delete?id=${lb.id}" >删除</a>
								<a href="/workflow/startProcess?id=${lb.id}" >申请请假</a>
			        		</#if>
			 				<#if lb.state == 1>
			 					<a href="/workflow/showHisComment?id=${lb.id}" >查看审核记录</a>
			 				</#if>
			 				<#if lb.state == 2>
			 					<a href="/leaveBill/delete?id=${lb.id}" >删除</a>
			 					<a href="/workflow/showHisComment?id=${lb.id}" >查看审核记录</a>
			 				</#if>    	
						</div></td>
				    </tr> 
				    </#list>
				    </#if>
		    </table></td>
		  </tr>
	</table>
</body>
</html>