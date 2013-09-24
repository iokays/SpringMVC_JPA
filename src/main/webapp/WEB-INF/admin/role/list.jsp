<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/include-jquery-ui-theme.jsp" %>
<%@ include file="/common/include-custom-styles.jsp" %>

<script type="text/javascript">
	function test() {
		alert("hello world");
	}
</script>
</head>
<body>
<div id="tabs">
	<ul>
		<li><a href="#tabs_role_list">角色列表</a></li>
		<li style="right:0em"><a href="#tabs_role_add">添加角色</a></li>
	</ul>
	<div id="tabs_role_list">
		<table style="width:100%">
			<thead>
				<tr>
					<th style="text-align:left; width:30% ">角色名称</th>
					<th style="text-align:left; width:30%">角色描述</th>
					<th style="text-align:left; width:20%">是否可用</th>
					<th style="text-align:left; width:15%">等级</th>
					<th style="text-align:left; width:5%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.getContent() }" var="role" >
					<tr>
						<td>${role.name }</td>
						<td>${role.description }</td>
						<td>${role.status }</td>
						<td>${role.level }</td>
						<td><a href = "${ctx }/admin/role/load/${role.id }" title="修改基本信息,角色">修改</a>|<a href = "${ctx }/admin/role/delete/${role.id }" title="删除基本信息 ">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="tabs_role_add">
		<form action = "${ctx }/admin/role/add" method = "post">
			<div>
				<label style="width:5em; display:inline-block">角色名称</label><input type = "text" name = "name"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">角色描述</label><input type = "text" name = "description"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">是否可用</label><input type = "text" name = "status"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">等级</label><input type = "text" name = "level"/>
			</div>
			
			<div style="float:rigth;">
				<button id="button" onclick = "this.form.submit()">保存并添加角色</button>
			</div>
			
		</form>
	</div>
</div>

</body>
</html>