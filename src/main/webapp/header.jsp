<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a target="homePages" href="<%=request.getContextPath() %>/homePages">首页管理</a>
                    </li>
                    <li class=""><a target="columns" href="<%=request.getContextPath() %>/columns">栏目管理</a></li>
                    <li class=""><a target="articles" href="<%=request.getContextPath() %>/articles">文章管理</a></li>
                    <li class=""><a target="reservations" href="<%=request.getContextPath() %>/reservations">预约管理</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<header class="jumbotron subhead" id="overview">
    <div class="container" style="margin-top: 40px">
        <h1 style="margin-top: 30px;margin-bottom: 30px;">后台内容管理系统</h1>
    </div>
</header>