<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>栏目列表</title>
</head>

<body>
<%@ include file="/header.jsp" %>
<div class="container" style="background: #FFF">

    <div>
        <ul class="breadcrumb">
            <li><a href="${ctx }/homePages">首页</a> <span class="divider">/</span></li>
            <li><a href="${ctx }/columns">栏目列表</a> <span class="divider">/</span></li>
            <li><button class="btn btn-link" type="button"onclick="generateStaticPage()">生成静态页</button><span class="divider">/</span></li>
            <li style="float: right"><a href="${ctx }/columns/new">添加</a> <span class="divider">/</span></li>
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
                <th>栏目名称</th>
                <th>文章列表</th>
                <th>栏目级别</th>
                <th>所属栏目</th>
                <th>栏目描述</th>
                <th>排序值</th>
                <th>标识</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${columns }" var="column" varStatus="status">
                <c:set var="children" value="${column.children }"/>
                <tr class="info">
                    <td><strong>${status.index }</strong></td>
                    <td><a  target="blank" href="${ctx }/${column.marking }.html">${column.name }</a></td>
                    <td><a href="#" class="btn btn-small btn-info disabled">文章管理</a></td>
                    <td>一级栏目</td>
                    <td></td>
                    <td>${column.description }</td>
                    <td>${column.sort }</td>
                    <td>${column.marking }</td>
                    <td>
                        <a href="${ctx}/columns/${column.id}" class="btn btn-small btn-primary">编辑</a>
                           <button class="btn btn-small btn-primary" type="button"onclick="generateStaticPageById('${column.id}')">生成</button>
                        <button class="btn btn-small btn-danger" type="button"
                                onclick="removeData(this, '${column.id}')">删除
                        </button>
                    </td>
                </tr>
                <c:forEach items="${children }" var="child" varStatus="status">
                    <tr>
                        <td><em>${status.index }</em></td>
                        <td><a  target="blank" href="${ctx }/${child.marking }.html">${child.name }</a></td>
                        <td><a href="${ctx}/articles?columnId=${child.id}" class="btn btn-small btn-primary" >文章管理</a></td>
                        <td>二级栏目</td>
                        <td>${column.name }</td>
                        <td>${child.description }</td>
                        <td>${child.sort }</td>
                        <td>${child.marking }</td>
                        <td>
                            <a href="${ctx}/columns/${child.id}" class="btn btn-small btn-primary">编辑</a>
                            <button class="btn btn-small btn-primary" type="button"onclick="generateStaticPageById('${child.id}')">生成</button>
                            <button class="btn btn-small btn-danger" type="button"
                                    onclick="removeData(this, '${child.id}')">删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>

            </c:forEach>
        </table>

    </div>


</div>
<script type="text/javascript" src="${ctx }/js/columns.js"></script>
</body>

</html>