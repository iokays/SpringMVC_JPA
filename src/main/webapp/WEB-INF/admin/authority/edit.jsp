<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.data.domain.Page"%>
<%@page import="com.iokays.authority.domain.Authority"%>
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
					class="icon-equalizer"></i>权限编辑</a></li>
			<li><a href="#page2">所属角色</a></li>
			<li><a href="#page3">包含资源</a></li>
			<li class="place-right"><a href="#page4"><i
					class="icon-cog nrm"></i></a></li>
		</ul>

		<div class="frames">
			<div class="frame active" id="page1">
				
			<div class="grid">
				 <div class="row">
				 	<div class="span5">
				 		<form action = "${ctx }/admin/authority/add" method = "post">
				 			<input type = "hidden" title = "用户ID" name = "id" value = "${authority.id}" >
							<div>
								<label style="width:5em; display:inline-block">角色名称</label><input type = "text" name = "name" value = "${authority.name }" />
							</div>
							<div>
								<label style="width:5em; display:inline-block">角色描述</label><input type = "text" name = "description" value = "${authority.description }"/>
							</div>
							<div>
								<label style="width:5em; display:inline-block">是否可用</label>
								<input type="radio" name = "status" value = "enabled" <c:if test = "${authority.status =='enabled'}">checked</c:if> ><span class="helper">可用</span>
								<input type="radio" name = "status" value = "disabled" <c:if test = "${authority.status =='disabled'}">checked</c:if> > <span class="helper">不可用</span>
							</div>
							<div>
								<label style="width:5em; display:inline-block">等级</label>
								<input type="radio" name = "level" value = "ordinary"  <c:if test = "${authority.level =='ordinary'}">checked</c:if> > <span class="helper">普通的</span>
								<input type="radio" name = "level" value = "systemic"  <c:if test = "${authority.level =='systemic'}">checked</c:if> > <span class="helper">系统的</span>
							</div>
							<div style="float:rigth;">
								<button id="button" onclick = "this.form.submit()">保存</button>
							</div>
							
						</form>
				 	</div>
				 </div>
			</div>
              
         </div>
			<div class="frame" id="page2">
				<iframe id = "authority_to_role" style = "width:100%"  seamless="seamless" src="${ctx }/admin/authoritytorole/toRole.jsp"></iframe>
			</div>
			
			<div class="frame" id="page3">
				<iframe id = "authority_to_role" style = "width:100%"  seamless="seamless" src="${ctx }/admin/authoritytorole/toRole.jsp"></iframe>
			</div>
			
		</div>
	</div>


</body>
</html>