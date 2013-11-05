<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.data.domain.Page"%>
<%@page import="org.activiti.engine.repository.HistoricProcessInstance"%>
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
					class="icon-equalizer"></i>流程实例列表</a></li>
			<li class="place-right"><a href="#page2"><i
					class="icon-cog nrm"></i></a></li>
		</ul>

		<div class="frames">
			<div class="frame active" id="page1">
				
			<table  class="hovered" style="width:100%">
			<thead>
				<tr>
					<th style="text-align:left; width:15%">流程实例编号</th>
					<th style="text-align:left; width:15%">流程定义编号</th>
					<th style="text-align:left; width:15%">开始时间</th>
					<th style="text-align:left; width:15% ">结束时间</th>
					<th style="text-align:left; width:15% ">开始用户</th>
					<th style="text-align:left; width:15%">资源</th>
					<th style="text-align:left; width:10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.getContent() }" var="historicProcessInstance" >
					<tr>
						<td>${historicProcessInstance.id }</td>
						<td>${historicProcessInstance.processDefinitionId }</td>
						<td><fmt:formatDate value="${historicProcessInstance.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${historicProcessInstance.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${historicProcessInstance.startUserId }</td>
						<td><a target="_blank" href='${ctx }/workflow/processDefinition/resource?processDefinitionId=${processDefinition.id}&resourceType=xml'>${processDefinition.resourceName }</a></td>
						
						<td>
						<c:if test="${processDefinition.suspended }">
							是 |<a href="spring/workflow/processDefinition/active/${process.id}"  title="确定要激活吗?" >激活</a>
						</c:if>
						<c:if test="${!processDefinition.suspended }">
							否 |<a href="spring/workflow/processDefinition/suspend/${process.id}"  title="确定要挂起吗?" >挂起</a>
						</c:if>
						</td>
						
						<td><a href = "${ctx }/admin/authority/load/${authority.id }" title="修改基本信息,角色">修改</a>|<a href = "${ctx }/admin/authority/delete/${authority.id }" title="删除基本信息 ">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
                <div id="pageDiv"></div>
                <%
                	Page<HistoricProcessInstance> _page = (Page<HistoricProcessInstance>)request.getAttribute("page");
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