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
<script type="text/javascript">
    function removeData(e, id) {
        $.ajax({
            type: "DELETE",
            url: "${ctx}/articles/" + id,
            success: function (data) {
                $(e).parents("tr").remove();
                $("#alert").attr("class", "alert alert-success");
                $("#alert_text").html("数据删除成功");
                $("#alert").css("display", "block");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#alert").attr("class", "alert alert-error");
                $("#alert_text").html("删除失败!");
                $("#alert").css("display", "block");
            }});
    }

</script>
<body>
<%@ include file="/header.jsp" %>
<div class="container" style="background: #FFF">

    <div>
        <ul class="breadcrumb">
            <li><a target="homePages" href="${ctx }/homePages">首页</a> <span class="divider">/</span></li>
            <li><a target="articles" href="${ctx }/articles">文章列表</a> <span class="divider">/</span></li>
            <li style="float: right"><a href="${ctx }/article?columnId=${columnId}">添加</a> <span
                    class="divider">/</span></li>
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

            <c:forEach items="${page.content }" var="object" varStatus="status">
                <tr class="info">
                    <td><strong>${status.count }</strong></td>
                    <td><a target="blank" href="${ctx }/${object[0]}.html">${object[1] }</a></td>
                    <td>${object[2] }</td>
                    <td>${object[3] }</td>
                    <td>
                        <a href="${ctx}/articles/${object[0]}" class="btn btn-small btn-primary" target="blank">编辑</a>
                        <button class="btn btn-small btn-danger" type="button"
                                onclick="removeData(this, '${object[0]}')">删除
                        </button>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </div>


</div>

</body>
<script type="text/javascript">
    $(function () {
        $(".close").click(function () {
            $(this).parent().hide();
        });
    });
</script>
</html>

