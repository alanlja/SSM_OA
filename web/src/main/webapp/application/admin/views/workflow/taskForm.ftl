<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/navbar.js"></script>
	<link type="text/css" rel="stylesheet" href="/css/public.css"/>
<title>请假任务办理</title>
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
		                <td width="94%" valign="bottom"><span class="STYLE1">请假申请的任务办理</span></td>
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
		  		<form action="/workflow/submitTask" method="POST">
			  		<div align="left" class="STYLE21">
			  			<!-- 任务ID -->
			  			<input type="hidden" name="taskId" value="<#if taskId??>${taskId}</#if>">
			  			<!-- 请假单ID -->
			  			<input type="hidden" name="id" value="<#if leaveBill.id??>${leaveBill.id}</#if>">
				 		请假天数:<input type="text" name="days" disabled="true" value="<#if leaveBill.days??>${leaveBill.days}</#if>" style="width: 200px;"/><br/>
				 		请假原因:<input type="text"  name="content" disabled="true" value="<#if leaveBill.content??>${leaveBill.content}</#if>" style="width: 800px;"/><br/>
				 		请假备注:<textarea name="remark" disabled="true" cols="30" rows="2"><#if leaveBill.remark??>${leaveBill.remark}</#if></textarea><br/>
				 		批&emsp;&emsp;注:<textarea name="comment" cols="50" rows="5"></textarea><br/>
				 		<!-- 使用连线的名称作为按钮 -->
				 		<#if outComeList??>
				 			<#list outComeList as outcome>
				 				<input type="submit" name="outcome" value="<#if outcome??>${outcome}</#if>" class="button_ok"/>				 			
				 			</#list>
				 		</#if>
			 		</div>
			 	</form>
		  	</td>
		  </tr>
	</table>
	<hr>
	<br>
	<#if commentList??>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr>
			    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="6%" height="19" valign="bottom"><div align="center"><img src="/images/tb.gif" width="14" height="14" /></div></td>
			                <td width="94%" valign="bottom"><span class="STYLE1">显示请假申请的批注信息</span></td>
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
			        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">时间</span></div></td>
			        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">批注人</span></div></td>
			        <td width="75%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">批注信息</span></div></td>
			      </tr>
			    <#list commentList as c>
			      	<tr>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE6">
				        <div align="center"><#if c.time??>${c.time?string("yyyy-MM-dd HH:mm:ss")}</#if></div>
				        </td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><#if c.userId??>${c.userId}</#if></div></td>
				        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><#if c.fullMessage??>${c.fullMessage}</#if></div></td>
				    </tr> 
			      </#list>
			   
			    </table></td>
			  </tr>
		</table>
	<#else>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr>
			    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td height="24" bgcolor="#F7F7F7"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="6%" height="19" valign="bottom"><div align="center"><img src="/images/tb.gif" width="14" height="14" /></div></td>
			                <td width="94%" valign="bottom"><span><b>暂时没有批注信息</b></span></td>
			              </tr>
			            </table></td>
			          </tr>
			        </table></td>
			      </tr>
			    </table></td>
			  </tr>
		</table>
		</#if>
</body>
</html>