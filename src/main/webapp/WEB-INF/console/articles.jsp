<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>文章列表</title>
</head>
<body>
<%@ include file="/header.jsp" %>
<div class="container" style="background: #FFF">

    <div>
        <ul class="breadcrumb">
            <li><a href="${ctx }/homePages">首页</a> <span class="divider">/</span></li>
            <li><a href="${ctx }/articles">文章列表</a> <span class="divider">/</span></li>
            <li><button class="btn btn-link" type="button"onclick="generateStaticPage()">生成静态页</button><span class="divider">/</span></li>
            <li style="float: right"><a href="${ctx }/articles/new<c:if test='${null != columnId }'>?columnId=${columnId}</c:if>">添加</a> <span class="divider">/</span></li>
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
                <th>文章标题</th>
                <th>所属栏目</th>
                <th>发布时间</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${page.content }" var="map" varStatus="status">
                <tr class="info">
                    <td><strong>${status.count }</strong></td>
                    <td><a target="blank" href="${ctx }/article/${map.id}.html">${map.title }</a></td>
                    <td>${map.columnName }</td>
                    <td><fmt:formatDate type="both" value="${map.createDate }" /></td>
                    <td>
                        <a href="${ctx}/articles/${map.id}" class="btn btn-small btn-primary" target="blank">编辑</a>
                        <button class="btn btn-small btn-primary" type="button"onclick="generateStaticPageById('${map.id}')">生成</button>
                        <button class="btn btn-small btn-danger" type="button"
                                onclick="removeData(this, '${map.id}')">删除
                        </button>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </div>


</div>

<script type="text/javascript" src="${ctx }/js/articles.js"></script>
</body>
</html>

