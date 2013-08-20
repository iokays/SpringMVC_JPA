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
		<li><a href="#tabs-role-edit">角色编辑</a></li>
		<li><a href="#tabs-role-to-role">所属用户</a></li>
		<li><a href="#tabs-role-to-role">包含权限</a></li>
	</ul>
	<div id="tabs-role-edit" >
		<form action = "${ctx }/admin/role/edit" method = "post">
			<div>
				<input type = "hidden" title = "角色ID" name = "id" value = "${role.id}" >
				<label style="width:5em; display:inline-block">角色名称</label><input type = "text" name = "name" value = "${role.name }" />
			</div>
			<div>
				<label style="width:5em; display:inline-block">角色描述</label><input type = "text" name = "description" value = "${role.description }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">是否可用</label><input type = "text" name = "status" value = "${role.status }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">等级</label><input type = "text" name = "level" value = "${role.level }"/>
			</div>
			<div style="float:rigth;">
				<button id="button" onclick = "this.form.submit()">提交</button>
			</div>
		</form>
	</div>
	
	<div id = "tabs-role-to-role">
		<iframe id = "role_to_role" style = "width:100%"  seamless="seamless" src="${ctx }/admin/roletorole/toRole.jsp"></iframe>
	</div>
</div>

</body>
</html>