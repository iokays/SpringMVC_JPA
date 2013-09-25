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
		<li><a href="#tabs_resource_list">资源列表</a></li>
		<li style="right:0em"><a href="#tabs_resource_add">添加资源</a></li>
	</ul>
	<div id="tabs_resource_list">
		<table style="width:100%">
			<thead>
				<tr>
					<th style="text-align:left; width:20%">资源名称</th>
					<th style="text-align:left; width:10%">资源类型</th>
					<th style="text-align:left; width:15%">资源链接</th>
					<th style="text-align:left; width:10%">资源优先权</th>
					<th style="text-align:left; width:15%">资源描述</th>
					<th style="text-align:left; width:10%">是否可用</th>
					<th style="text-align:left; width:10%">等级</th>
					<th style="text-align:left; width:10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.getContent() }" var="resource" >
					<tr>
						<td>${resource.name }</td>
						<td>${resource.type }</td>
						<td>${resource.value }</td>
						<td>${resource.priority }</td>
						<td>${resource.description }</td>
						<td>${resource.status }</td>
						<td>${resource.level }</td>
						<td><a href = "${ctx }/admin/resource/load/${resource.id }" title="修改基本信息,角色">修改</a>|<a href = "${ctx }/admin/resource/delete/${resource.id }" title="删除基本信息 ">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="tabs_resource_add">
		<form action = "${ctx }/admin/resource/add" method = "post">
			<div>
				<label style="width:5em; display:inline-block">资源名称</label><input type = "text" name = "name"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">资源类型</label><input type = "text" name = "type"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">资源链接</label><input type = "text" name = "value"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">资源优先权</label><input type = "text" name = "priority"/>
			</div>
			<div>
				<label style="width:5em; display:inline-block">资源描述</label><input type = "text" name = "description"/>
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