<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
</head>
<body>
	<table width="100%" border="1" cellpadding="0" cellspacing="0">
		<tr height="30px" style="background-color:#aaa">
			<td>
		     <MARQUEE scrollAmount=8 direction=left><span style="color:red;">欢迎来到...首页</span></MARQUEE>
			</td>
			<td style="text-align: right;">
			<#if user??>
			      员工：<#if user.userChName??>${user.userChName}</#if>
			</#if>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="/frame">后台管理</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			
		</tr>
		
		<tr height="700px">
			<td colspan="2" >
			    
			</td>	
		</tr>
	</table>
</body>
</html>
