<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global-taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/bootstrap-styles.jsp"%>
</head>
<body>
<div id="tabs">
	<ul>
		<li><a href="#tabs-user-edit">用户编辑</a></li>
		<li><a href="#tabs-user-to-role">包含角色</a></li>
	</ul>
	<div id="tabs-user-edit" >
		<form action = "${ctx }/admin/user/edit" method = "post">
			<div>
				<input type = "hidden" title = "用户ID" name = "id" value = "${user.id}" >
				<label style="width:5em; display:inline-block">登陆账号</label><input type = "text" name = "account" value = "${user.account }" />
			</div>
			<div>
				<label style="width:5em; display:inline-block">用户名称</label><input type = "text" name = "name" value = "${user.name }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">用户密码</label><input type = "text" name = "password" value = "${user.password }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">用户描述</label><input type = "text" name = "description" value = "${user.description }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">是否可用</label><input type = "text" name = "status" value = "${user.status }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">等级</label><input type = "text" name = "level" value = "${user.level }"/>
			</div>
			<div style="float:rigth;">
				<button id="button" onclick = "this.form.submit()">提交</button>
			</div>
		</form>
	</div>
	
	<div id = "tabs-user-to-role">
		<iframe id = "user_to_role" style = "width:100%"  seamless="seamless" src="${ctx }/admin/usertorole/toRole.jsp"></iframe>
	</div>
</div>

</body>
</html>