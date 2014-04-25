<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>首页列表</title>
</head>
<body>
<%@ include file="/header.jsp" %>
<div class="container" style="background: #FFF">

    <div>
        <ul class="breadcrumb">
            <li><a href="${ctx }/homePages">首页</a> <span class="divider">/</span></li>
            <li><button class="btn btn-link" type="button"onclick="generateStaticPage()">生成静态页</button><span class="divider">/</span></li>
            <li style="float: right"><a href="${ctx }/homePages/new">添加</a> <span class="divider">/</span></li>
        </ul>
    </div>
    <div id="alert" class="alert alert-error" style="display: none">
        <button type="button" class="close">×</button>
        <strong id="alert_text"></strong>
    </div>
    <div>
        <table class="table table-condensed">
            <tr>
                <th>#</th>
                <th>图片名称</th>
                <th>图片链接</th>
                <th>排序值</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${homePages }" var="homePage" varStatus="status">
                <tr class="info">
                    <td><strong>${status.index }</strong></td>
                    <td><a href="${ctx}/index.html" target="blank" >${homePage.name }</a></td>
                    <td><a href="${homePage.target }" target="blank" >${homePage.target }</a></td>
                    <td>${homePage.sort }</td>
                    <td>
                        <a href="${ctx}/homePages/${homePage.id}" class="btn btn-small btn-primary" >编辑</a>
                        <button class="btn btn-small btn-danger" type="button"
                                onclick="removeData(this, '${homePage.id}')">删除
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>

</div>

<script type="text/javascript" src="${ctx }/js/homePages.js"></script>
</body>
</html>