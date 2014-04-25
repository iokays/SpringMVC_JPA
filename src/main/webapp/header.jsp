<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			
			<!-- <a class="brand" href="#">后台系统</a> -->
			<div class="nav-collapse collapse">
				<p class="navbar-text pull-right">
					<a target="blank" href="https://github.com/iokays/love" class="navbar-link">源码</a>  <a href="<%=request.getContextPath() %>/j_spring_security_logout" class="navbar-link">退出</a>
            	</p>
				<ul class="nav">
					<li id="homePagesHeader"><a
						href="<%=request.getContextPath() %>/homePages">首页管理</a></li>
					<li id="columnsHeader"><a
						href="<%=request.getContextPath() %>/columns">栏目管理</a></li>
					<li id="articlesHeader"><a
						href="<%=request.getContextPath() %>/articles">文章管理</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>

<header class="jumbotron subhead" id="overview">
	<div class="container" style="margin-top: 40px">
		<h1 style="margin-top: 30px; margin-bottom: 30px;">后台内容管理系统</h1>
	</div>
</header>