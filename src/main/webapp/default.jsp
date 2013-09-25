<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/global-taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/bootstrap-styles.jsp"%>
<title>Bootstrap, from Twitter</title>
<style type="text/css">
body {
	min-height: 2000px;
}

.navbar-static-top {
	margin-bottom: 19px;
}
</style>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->
</head>
<body style="">

	<!-- Static navbar -->
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="http://v3.bootcss.com/examples/navbar-static-top/#">Project
					name</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class = "dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">系统管理<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="http://v3.bootcss.com/examples/navbar-static-top/#">Action</a></li>
							<li><a href="http://v3.bootcss.com/examples/navbar-static-top/#">Action</a></li>
							<li><a href="http://v3.bootcss.com/examples/navbar-static-top/#">Action</a></li>
							<li><a href="http://v3.bootcss.com/examples/navbar-static-top/#">Action</a></li>
							<li><a href="http://v3.bootcss.com/examples/navbar-static-top/#">Action</a></li>
							<li><a href="http://v3.bootcss.com/examples/navbar-static-top/#">Action</a></li>
						
						</ul>
					</li>
					
					<li class="active"><a
						href="http://v3.bootcss.com/examples/navbar-static-top/#">Home</a></li>
					<li><a
						href="http://v3.bootcss.com/examples/navbar-static-top/#about">About</a></li>
					<li><a
						href="http://v3.bootcss.com/examples/navbar-static-top/#contact">Contact</a></li>
					<li class="dropdown"><a
						href="http://v3.bootcss.com/examples/navbar-static-top/#"
						class="dropdown-toggle" data-toggle="dropdown">Dropdown <b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="http://v3.bootcss.com/examples/navbar-static-top/#">Action</a></li>
							<li><a
								href="http://v3.bootcss.com/examples/navbar-static-top/#">Another
									action</a></li>
							<li><a
								href="http://v3.bootcss.com/examples/navbar-static-top/#">Something
									else here</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Nav header</li>
							<li><a
								href="http://v3.bootcss.com/examples/navbar-static-top/#">Separated
									link</a></li>
							<li><a
								href="http://v3.bootcss.com/examples/navbar-static-top/#">One
									more separated link</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="http://v3.bootcss.com/examples/navbar/">Default</a></li>
					<li class="active"><a
						href="./Static Top Navbar Example for Bootstrap_files/Static Top Navbar Example for Bootstrap.htm">Static
							top</a></li>
					<li><a href="http://v3.bootcss.com/examples/navbar-fixed-top/">Fixed
							top</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>


	<div class="container">

		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>Navbar example</h1>
			<p>This example is a quick exercise to illustrate how the
				default, static and fixed to top navbar work. It includes the
				responsive CSS and HTML, so it also adapts to your viewport and
				device.</p>
			<p>To see the difference between static and fixed top navbars,
				just scroll.</p>
			<p>
				<a class="btn btn-lg btn-primary"
					href="http://v3.bootcss.com/components/#navbar" role="button">View
					navbar docs »</a>
			</p>
		</div>

	</div>
	<!-- /container -->
</body>
</html>
