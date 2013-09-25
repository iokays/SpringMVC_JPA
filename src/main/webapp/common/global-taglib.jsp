<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, org.apache.commons.lang3.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
//jquery版本
String jqueryVersion = "2.0.3";
String jquery-mousewheelVersion = "3.0.6";

session.setAttribute("jqueryVersion", jqueryVersion);
session.setAttribute("jquery-mousewheelVersion", jquery-mousewheelVersion);

pageContext.setAttribute("timeInMillis", System.currentTimeMillis());
%>
<script type="text/javascript">
	var ctx = '<%=request.getContextPath() %>';
</script>