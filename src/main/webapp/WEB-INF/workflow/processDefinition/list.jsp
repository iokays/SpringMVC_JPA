<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.data.domain.Page"%>
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
					class="icon-equalizer"></i>流程定义列表</a></li>
			<li><a href="#page2">添加流程定义</a></li>
			<li class="place-right"><a href="#page4"><i
					class="icon-cog nrm"></i></a></li>
		</ul>

		<div class="frames">
			<div class="frame active" id="page1">
				
			<table  class="hovered" style="width:100%">
			<thead>
				<tr>
					<th style="text-align:left; width:20% ">流程定义名称</th>
					<th style="text-align:left; width:20%">流程定义标识符</th>
					<th style="text-align:left; width:10% ">版本号</th>
					<th style="text-align:left; width:10%">资源 </th>
					<th style="text-align:left; width:15%">部署时间</th>
					<th style="text-align:left; width:15%">是否挂起</th>
					<th style="text-align:left; width:10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.getContent() }" var="object" >
				<c:set var="processDefinition" value="${object[0] }" />
				<c:set var="deployment" value="${object[1] }" />
					<tr>
						<td>${processDefinition.name }</td>
						<td>${processDefinition.key }</td>
						<td>${processDefinition.version }</td>
						<td>
							<a target="_blank" href='${ctx }/workflow/processDefinition/resource?processDefinitionId=${processDefinition.id}&resourceType=xml'>文件</a>
							<a target="_blank" href='${ctx }/workflow/processDefinition/resource?processDefinitionId=${processDefinition.id}&resourceType=xml'>图片</a>
						</td>
						<td><fmt:formatDate value="${deployment.deploymentTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
						<c:if test="${processDefinition.suspended }">
							已挂起 |<a href="spring/workflow/processDefinition/active/${process.id}"  title="确定要激活吗?" >激活</a>
						</c:if>
						<c:if test="${!processDefinition.suspended }">
							已激活 |<a href="spring/workflow/processDefinition/suspend/${process.id}"  title="确定要挂起吗?" >挂起</a>
						</c:if>
						</td>
						
						<td><a href = "${ctx }/workflow/processDefinition/delete/${processDefinition.deploymentId }" title="删除基本信息 ">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
                <div id="pageDiv"></div>
                <%
                	Page<Object[]> _page = (Page<Object[]>)request.getAttribute("page");
                	int _pageTotal = _page.getTotalPages();
                	int _pageCurrent = _page.getNumber() + 1;
                %>
				<script type="text/javascript">
                    $(document).ready(function(){
                        var page = $("#pageDiv").pagelist();
                        page.total = <%=_pageTotal %>;
                        page.current = <%=_pageCurrent %>;
                        page.url = "list?page.page={page}";
                        if (1 < page.total) {
                        	 page.create();
                        }
                    });
         		</script>
         </div>
			<div class="frame" id="page2">
			<div class="grid">
				 <div class="row">
				 	<div class="span5">
				 		<!-- <legend>部署新流程</legend> -->
						<div>
							<form action="${ctx }/workflow/processDefinition/deploy" method="post" enctype="multipart/form-data">
								<input type="file" name="file" />
								<input type="submit" value="上传" />
							</form>
						</div>
						<div><b>支持文件格式：</b>zip、bar、bpmn、bpmn20.xml</div>
						
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