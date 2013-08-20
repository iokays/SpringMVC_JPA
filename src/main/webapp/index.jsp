<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/include-jquery-ui-theme.jsp" %>
<%@ include file="/common/include-custom-styles.jsp" %>
</head> 
<body>
	<div id = "top" style="position:absolute; height:10em; left:0em; right:0em">
		<h1>基于SpringMVC_JPA 全注解框架搭建</h1>
	</div>	
	<div id="accordion" style="position:absolute; left:0em; top:10em; bottom:0em; width: 20em">
		<div>
			<h3>
				<a href="#">系统管理</a>
			</h3>
			<div>
				<a href = "${ctx }/admin/user/list" target="main">用户列表</a><br>
				<a href = "${ctx }/admin/role/list" target="main">角色列表</a><br>
				<a href = "${ctx }/admin/authority/list" target="main">权限列表</a><br>
				<a href = "${ctx }/admin/resource/list" target="main">资源列表</a><br>
			</div>
		</div>
		<div>
			<h3>
				<a href="#">JPBM6工作流</a>
			</h3>
			<div>Phasellus mattis tincidunt nibh.</div>
		</div>
	</div>
	<div id = "main" style="position:absolute; left:20em; top:10em; bottom:0em; right:0em;">
		<iframe name = "main"  seamless="seamless" height = "100%" width = "100%" src = "main.jsp"></iframe>
	</div>
</body>
</html>