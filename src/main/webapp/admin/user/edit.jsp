<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/include-jquery-ui-theme.jsp" %>
<%@ include file="/common/include-custom-styles.jsp" %>
</head>
<body>
<div id="tabs">
	<ul>
		<li><a href="#tabs-user-edit">用户编辑</a></li>
	</ul>
	<div id="tabs-user-edit" >
		<form action = "/edit" method = "post">
			<div>
				<label style="width:5em; display:inline-block">登陆账号</label><input type = "text" name = "user.account" value = "${user.account }" />
			</div>
			<div>
				<label style="width:5em; display:inline-block">用户名称</label><input type = "text" name = "user.name" value = "${user.name }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">用户描述</label><input type = "text" name = "user.description" value = "${user.description }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">是否可用</label><input type = "text" name = "user.status" value = "${user.status }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">等级</label><input type = "text" name = "user.level" value = "${user.level }"/>
			</div>
		</form>
	</div>
	
	<div>
		<iframe id = "user_to_role" style = "width:100%" src=""></iframe>
	</div>
</div>

</body>
</html>