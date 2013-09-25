<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/global-taglib.jsp" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%@ include file="common/bootstrap-styles.jsp"%>
</head>
<body>
<div id="tabs">
	<ul>
		<li><a href="#tabs-authority-edit">权限编辑</a></li>
		<li><a href="#tabs-authority-to-role">所属角色</a></li>
		<li><a href="#tabs-authority-to-role">包含资源</a></li>
	</ul>
	<div id="tabs-authority-edit" >
		<form action = "${ctx }/admin/authority/edit" method = "post">
			<div>
				<input type = "hidden" title = "权限ID" name = "id" value = "${authority.id}" >
				<label style="width:5em; display:inline-block">权限名称</label><input type = "text" name = "name" value = "${authority.name }" />
			</div>
			<div>
				<label style="width:5em; display:inline-block">权限描述</label><input type = "text" name = "description" value = "${authority.description }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">是否可用</label><input type = "text" name = "status" value = "${authority.status }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">等级</label><input type = "text" name = "level" value = "${authority.level }"/>
			</div>
			<div style="float:rigth;">
				<button id="button" onclick = "this.form.submit()">提交</button>
			</div>
		</form>
	</div>
	
	<div id = "tabs-authority-to-role">
		<iframe id = "authority_to_role" style = "width:100%"  seamless="seamless" src="${ctx }/admin/authoritytorole/toRole.jsp"></iframe>
	</div>
</div>

</body>
</html>