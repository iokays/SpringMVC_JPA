<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>预约列表</title>
</head>
<script type="text/javascript">
    function removeData(e, id) {
        $.ajax({
            type: "DELETE",
            url: "${ctx}/reservation/" + id,
            success: function (data) {
                $(e).parents("tr").remove();
                $("#alert").attr("class", "alert alert-success");
                if (0 == data) {
                    $("#alert_text").html("数据不存在,或者已删除");
                } else {
                    $("#alert_text").html("数据删除成功");
                }
                $("#alert").css("display", "block");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#alert").attr("class", "alert alert-error");
                $("#alert_text").html("删除失败!");
                $("#alert").css("display", "block");
            }});
    }

    function updateStatus(e, id) {
        var data = {"status": "contact"};

        $.ajax({
            type: "PUT",
            url: "${ctx}/reservation/" + id,
            data: JSON.stringify(data),
            success: function () {
                $("#alert").attr("class", "alert alert-success");
                $("#alert_text").html("修改成功");
                $("#alert").css("display", "block");
                $(e).attr("class", "btn btn-small btn-primary disabled");
                $(e).html("已联系");
            },
            error: function () {
                $("#alert").attr("class", "alert alert-error");
                $("#alert_text").html("修改失败");
                $("#alert").css("display", "block");
            }
        });
    }

</script>
<body>
<%@ include file="/header.jsp" %>
<div class="container" style="background: #FFF">

    <div>
        <ul class="breadcrumb">
            <li><a target="homePages" href="${ctx }/homePages">首页</a> <span class="divider">/</span></li>
            <li><a target="reservations" href="${ctx }/reservations">预约列表</a> <span class="divider">/</span></li>
            <li style="float: right"><a href="${ctx }/reservation">添加</a> <span class="divider">/</span></li>
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
                <th>宝宝姓名</th>
                <th>宝宝生日</th>
                <th>家长手机</th>
                <th>预约课程</th>
                <th>所在城市</th>
                <th>备注</th>
                <th>状态</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${reservations }" var="reservation" varStatus="status">
                <tr class="info">
                    <td><strong>${status.index }</strong></td>
                    <td>${reservation.name }</td>
                    <td>${reservation.birthday }</td>
                    <td>${reservation.phone }</td>
                    <td>${reservation.course }</td>
                    <td>${reservation.city }</td>
                    <td>${reservation.remark }
                        <c:if test="${'uncontact'==reservation.status}">
                    <td>
                        <button class="btn btn-small btn-primary" type="button"
                                onclick="updateStatus(this, '${reservation.id}')">未联系
                        </button>
                    </td>
                    </c:if>
                    <c:if test="${'contact'==reservation.status}">
                        <td>
                            <button class="btn btn-small btn-primary disabled" type="button">已联系</button>
                        </td>
                    </c:if>

                    <td>
                        <a href="${ctx}/reservation/${reservation.id}" class="btn btn-small btn-primary"
                           target="${reservation.id }">编辑</a>
                        <button class="btn btn-small btn-danger" type="button"
                                onclick="removeData(this, '${reservation.id}')">删除
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