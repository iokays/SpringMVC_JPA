<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/metro_styles.jsp"%>
<%@ include file="/common/meta.jsp"%>
<title>基于SpringMVC_JPA 全注解框架搭建</title>
</head>
<body class="metrouicss" onload="prettyPrint()" style="zoom: 1;">

		<div class="nav-bar">
			<div class="nav-bar-inner padding10">
				<span class="pull-menu"></span> <a href=""><span
					class="element brand"> <img class="place-left"
						src="${ctx }/public/images/logo32.png" style="height: 20px" /> 基于SpringMVC_JPA
						全注解框架搭建 <small></small>
				</span></a>

				<div class="divider"></div>

				<ul class="menu">
					<li><a href="">首页</a></li>
				</ul>

			</div>
		</div>
		
		<div class="page snapped">
         <div class="page-sidebar">
            <ul>
                 <li class="sticker sticker-color-pink dropdown active" data-role="dropdown">
                    <a><i class="icon-list"></i>工作流管理</a>
                    <ul class="sub-menu light sidebar-dropdown-menu open">
                        <li><a href="${ctx }/workflow/processDefinition/list" target="content">流程定义列表</a></li>
						<li><a href="${ctx }/workflow/processInstance/list" target="content">流程实例列表</a></li>
						<li><a href="${ctx }/workflow/historicTaskInstance/list" target="content">流程任务列表</a></li>
						<li><a href="responsive.html">流程用户组列表</a></li>
                    </ul>
                </li>
                 <li class="sticker sticker-color-green dropdown active" data-role="dropdown">
                    <a><i class="icon-list"></i> 系统管理</a>
                    <ul class="sub-menu light sidebar-dropdown-menu keep-opened">
                        <li><a href = "${ctx }/admin/user/list" target="content">用户列表</a></li>
                        <li><a href = "${ctx }/admin/role/list" target="content">角色列表</a></li>
                        <li><a href = "${ctx }/admin/authority/list" target="content">权限列表</a></li>
                        <li><a href = "${ctx }/admin/resource/list" target="content">资源列表</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    	</div>
    <div class="page fill">
        <div class="page-region">
            <div class="page-region-content">
             	<iframe name = "content"  frameborder="no" seamless="seamless" height = "100%" width = "100%" scrolling="no" src="http://www.baidu.com"></iframe>
            </div>
        </div>
    </div>

	
	   

        

</body>


</html>