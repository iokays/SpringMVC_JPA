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
		<li><a href="#tabs_user_list">用户列表</a></li>
		<li style="right:0em"><a href="#tabs_user_add">添加用户</a></li>
	</ul>
	<div id="tabs_user_list">
		<table style="width:100%">
			<thead>
				<tr>
					<th style="text-align:left; width:20% ">登陆账号</th>
					<th style="text-align:left; width:20%">用户名</th>
					<th style="text-align:left; width:20%">用户描述</th>
					<th style="text-align:left; width:20%">是否可用</th>
					<th style="text-align:left; width:15%">等级</th>
					<th style="text-align:left; width:5%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.getContent() }" var="user" >
					<tr>
						<td>${user.account }</td>
						<td>${user.name }</td>
						<td>${user.description }</td>
						<td>${user.status }</td>
						<td>${user.level }</td>
						<td><a href = "${ctx }/admin/user/load/${user.id }" title="修改基本信息,角色">修改</a>|<a href = "${ctx }/admin/user/delete/${user.id }" title="删除基本信息 ">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="tabs_user_add">
		<form action = "${ctx }/admin/user/add" method = "post">
			<div>
				<label style="width:5em; display:inline-block">登陆账号</label><input type = "text" name = "account"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">用户名称</label><input type = "text" name = "name"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">用户密码</label><input type = "text" name = "password"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">用户描述</label><input type = "text" name = "description"/>
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