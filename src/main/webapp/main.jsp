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
<h2>权限标签测试</h2>
<sec:authorize url = "/test.jsp">
	可见
</sec:authorize>
<sec:authorize url = "/test.html">
	不可见
</sec:authorize>
</body>
</html>