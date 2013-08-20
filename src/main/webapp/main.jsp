<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/global.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="common/meta.jsp" %>
<%@ include file="common/include-jquery-ui-theme.jsp" %>
<%@ include file="common/include-custom-styles.jsp" %>
</head>
<body>
<%-- <h2>权限标签测试</h2>
用户名:<sec:authentication property="name" />, 欢迎来到主页!<br>
拥有权限:<sec:authentication property="principal.authorities" /><br>  

<sec:authorize ifAnyGranted="1">您是超级管理员,可看到该信息:(这里可以用逗号分隔，加入多个角色你拥有管理员权限)</sec:authorize>

<sec:authorize url = "/index.jsp">
	<br>可见
</sec:authorize>

<sec:authorize url = "/test.jsp">
	<br>不可见
</sec:authorize> --%>

</body>
</html>