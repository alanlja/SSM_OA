
<html>
<head>
<meta charset="UTF-8">

</head>
<body style="background-color: #8FBC8F;">
	<#if menuList??> <#if menuList?size==0> 你没有访问菜单的权限 <#else> <#list
	menuList as menu>
	<a href="<#if menu.menuPath??>${menu.menuPath}</#if>"
		target="mainFrame"><#if menu.menuName??>${menu.menuName}</#if></a>
	<br> </#list> </#if> <#else> 你没有访问菜单的权限 </#if>

</body>
</html>