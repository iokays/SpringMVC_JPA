<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.data.domain.Page"%>
<%@page import="com.iokays.user.domain.User"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/metro_styles.jsp"%>
<%@ include file="/common/meta.jsp"%>

</head>
<body class="metrouicss">
	
	<div class="page-control" data-role="page-control">
		<span class="menu-pull"></span>
		<div class="menu-pull-bar">Page 1</div>

		<ul>
			<li class="active"><a href="#page1"><i
					class="icon-equalizer"></i>用户列表</a></li>
			<li><a href="#page2">添加用户</a></li>
			<li class="place-right"><a href="#page4"><i
					class="icon-cog nrm"></i></a></li>
		</ul>

		<div class="frames">
			<div class="frame active" id="page1">
				
			<table  class="hovered" style="width:100%">
			<thead>
				<tr>
					<th style="text-align:left; width:30% ">登陆账号</th>
					<th style="text-align:left; width:20%">用户名</th>
					<th style="text-align:left; width:20%">用户描述</th>
					<th style="text-align:left; width:10%">是否可用</th>
					<th style="text-align:left; width:10%">等级</th>
					<th style="text-align:left; width:10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.getContent() }" var="user" >
					<tr>
						<td>${user.account }</td>
						<td>${user.name }</td>
						<td>${user.description }</td>
						<td>
							<c:if test = "${user.status =='enabled'}">已启用</c:if>
							<c:if test = "${user.status =='disabled'}">已禁用</c:if>
						</td>
						<td>
							<c:if test = "${user.level =='ordinary'}">普通的</c:if>
							<c:if test = "${user.level =='systemic'}">系统的</c:if>
						</td>
						<td><a href = "${ctx }/admin/user/load/${user.id }" title="修改基本信息,角色">修改</a>|<a href = "${ctx }/admin/user/delete/${user.id }" title="删除基本信息 ">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
                <div id="pageDiv"></div>
                <%
                	Page<User> _page = (Page<User>)request.getAttribute("page");
                	int _pageTotal = _page.getTotalPages();
                	int _pageCurrent = _page.getNumber() + 1;
                %>
				<script type="text/javascript">
                    $(document).ready(function(){
                        var page = $("#pageDiv").pagelist();
                        page.total = <%=_pageTotal %>;
                        page.current = <%=_pageCurrent %>;
                        page.url = "list?page.page={page}";
                        page.create();
                    });
         		</script>
         </div>
			<div class="frame" id="page2">
			<div class="grid">
				 <div class="row">
				 	<div class="span5">
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
								<label style="width:5em; display:inline-block">是否可用</label>
								<input type="radio" name = "status" value = "enabled" checked> <span class="helper">可用</span>
								<input type="radio" name = "status" value = "disabled"> <span class="helper">不可用</span>
							</div>
							<div>
								<label style="width:5em; display:inline-block">等级</label>
								<input type="radio" name = "level" value = "ordinary" checked> <span class="helper">普通的</span>
								<input type="radio" name = "level" value = "systemic"> <span class="helper">系统的</span>
							</div>
							<div style="float:rigth;">
								<button id="button" onclick = "this.form.submit()">保存</button>
							</div>
							
						</form>
				 	</div>
				 </div>
			</div>
			</div>
			<div class="frame" id="page4">
				<h2>使用手册</h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Curabitur porta condimentum sem sed commodo. Praesent vestibulum,
					libero eget lacinia pretium, metus augue dapibus odio, nec placerat
					mauris justo non ante. Maecenas adipiscing nulla sed sem molestie
					quis pulvinar lectus convallis. Nam tortor arcu, gravida nec
					tristique sit amet, pretium sagittis eros. Curabitur at nisi ut
					ligula ornare euismod. Ut vitae tortor eget elit dictum dictum. Ut
					porttitor, ante non blandit gravida, felis risus feugiat neque, eu
					tincidunt neque ante at urna. Maecenas nec felis nulla. Praesent
					volutpat ligula vel diam venenatis feugiat. Praesent quis nunc quis
					nisl condimentum dapibus in sed ipsum. Aenean nulla sapien,
					consequat id aliquam ac, sollicitudin sed nisl. Vestibulum ante
					ipsum primis in faucibus orci luctus et ultrices posuere cubilia
					Curae; Duis vitae risus erat.</p>
			</div>
		</div>
	</div>


</body>
</html>