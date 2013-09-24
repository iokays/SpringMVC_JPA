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
		<li><a href="#tabs-resource-edit">资源编辑</a></li>
		<li><a href="#tabs-resource-to-role">所属权限</a></li>
	</ul>
	<div id="tabs-resource-edit" >
		<form action = "${ctx }/admin/resource/edit" method = "post">
			<div>
				<input type = "hidden" title = "用户ID" name = "id" value = "${resource.id}" >
				<label style="width:5em; display:inline-block">资源名称</label><input type = "text" name = "name" value = "${resource.name }" />
			</div>
			<div>
				<label style="width:5em; display:inline-block">资源类型</label><input type = "text" name = "type" value = "${resource.type }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">资源链接</label><input type = "text" name = "value" value = "${resource.value }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">资源优先权</label><input type = "text" name = "priority" value = "${resource.priority }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">资源描述</label><input type = "text" name = "description" value = "${resource.description }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">是否可用</label><input type = "text" name = "status" value = "${resource.status }"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">等级</label><input type = "text" name = "level" value = "${resource.level }"/>
			</div>
			<div style="float:rigth;">
				<button id="button" onclick = "this.form.submit()">提交</button>
			</div>
		</form>
	</div>
	
	<div id = "tabs-resource-to-role">
		<iframe id = "resource_to_role" style = "width:100%"  seamless="seamless" src="${ctx }/admin/resourcetorole/toRole.jsp"></iframe>
	</div>
</div>

</body>
</html>