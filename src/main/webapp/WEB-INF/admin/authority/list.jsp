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
		<li><a href="#tabs_authority_list">权限列表</a></li>
		<li style="right:0em"><a href="#tabs_authority_add">添加权限</a></li>
	</ul>
	<div id="tabs_authority_list">
		<table style="width:100%">
			<thead>
				<tr>
					<th style="text-align:left; width:20%">权限名称</th>
					<th style="text-align:left; width:20%">权限描述</th>
					<th style="text-align:left; width:20%">是否可用</th>
					<th style="text-align:left; width:15%">等级</th>
					<th style="text-align:left; width:5%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.getContent() }" var="authority" >
					<tr>
						<td>${authority.name }</td>
						<td>${authority.description }</td>
						<td>${authority.status }</td>
						<td>${authority.level }</td>
						<td><a href = "${ctx }/admin/authority/load/${authority.id }" title="修改基本信息,角色">修改</a>|<a href = "${ctx }/admin/authority/delete/${authority.id }" title="删除基本信息 ">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="tabs_authority_add">
		<form action = "${ctx }/admin/authority/add" method = "post">
			<div>
				<label style="width:5em; display:inline-block">权限名称</label><input type = "text" name = "name"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">权限描述</label><input type = "text" name = "description"/>
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