<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>预约编辑</title>
    <script>

        function buildData() {
            var name = $("#name").val();
            var birthday = $("#birthday").val();
            var phone = $("#phone").val();
            var course = $("#course").val();
            var city = $("#city").val();
            var remark = $("#remark").val();
            var status = $("#status").val();
            return {"name": name, "birthday": birthday, "phone": phone, "course": course, "city": city, "remark": remark, "status": status};
        }

        function insertData() {
            var data = buildData();
            $.ajax({
                type: "POST",
                url: "${ctx}/reservation",
                data: data,
                success: function (data) {
                    /* $("#id").val(data); */
                    $("#alert").attr("class", "alert alert-success");
                    $("#alert_text").html("添加成功");
                    $("#alert").css("display", "block");
                },
                error: function () {
                    $("#alert").attr("class", "alert alert-error");
                    $("#alert_text").html("添加失败");
                    $("#alert").css("display", "block");
                }
            });
        }

        function updateData(id) {
            var data = buildData();
            $.ajax({
                type: "PUT",
                url: "${ctx}/reservation/" + id,
                data: JSON.stringify(data),
                success: function () {
                    $("#alert").attr("class", "alert alert-success");
                    $("#alert_text").html("修改成功");
                    $("#alert").css("display", "block");
                },
                error: function () {
                    $("#alert").attr("class", "alert alert-error");
                    $("#alert_text").html("修改失败");
                    $("#alert").css("display", "block");
                }
            });
        }

        function uploadData() {
            var id = $("#id").val();
            if (undefined == id || "" == id) {
                insertData();
            } else {
                updateData(id);
            }
        }
    </script>

</head>
<body>
<%@ include file="/header.jsp" %>
<div class="container" style="background: #FFF">

    <div>
        <ul class="breadcrumb">
            <li><a target="homePages" href="${ctx }/homePages">首页</a> <span class="divider">/</span></li>
            <li><a target="reservations" href="${ctx }/reservations">预约列表</a> <span class="divider">/</span></li>
            <li><a href="${ctx }/reservation">再添加</a> <span class="divider">/</span></li>
            <li style="float:right">
                <button class="btn btn-small btn-primary" type="button" onclick="uploadData()">保存</button>
            </li>
        </ul>
    </div>
    <div id="alert" class="alert alert-error" style="display: none">
        <button type="button" class="close">×</button>
        <strong id="alert_text"></strong>
    </div>
    <div>
        <form class="form-horizontal">
            <input type="hidden" id="id" value="${reservation.id }">

            <div class="control-group">
                <label class="control-label" for="name">宝宝姓名</label>

                <div class="controls">
                    <input id="name" class="span6" type="text" size=40 placeholder="输入宝宝姓名"
                           value="${reservation.name }"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="birthday">宝宝生日</label>

                <div class="controls">
                    <input id="birthday" class="span6" type="text" size=40 placeholder="输入宝宝生日"
                           value="${reservation.birthday }"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="phone">家长手机</label>

                <div class="controls">
                    <input id="phone" class="span6" type="text" size=40 placeholder="输入家长手机"
                           value="${reservation.phone }"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="course">预约课程</label>

                <div class="controls">
                    <input id="course" class="span6" type="text" size=40 placeholder="输入预约课程"
                           value="${reservation.course }"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="city">所在城市</label>

                <div class="controls">
                    <input id="city" class="span6" type="text" size=40 placeholder="输入所在城市"
                           value="${reservation.city }"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="remark">备注</label>

                <div class="controls">
                    <input id="remark" class="span6" type="text" size=40 placeholder="输入备注"
                           value="${reservation.remark }"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="status">状态</label>

                <div class="controls">
                    <select id="status">
                        <option value="uncontact">未联系</option>
                        <option value="contact">已联系</option>
                    </select>
                </div>
            </div>

        </form>
    </div>


</div>

</body>
<script>
    $(function () {
        $(".close").click(function () {
            $(this).parent().hide();
        });

    });
</script>
</html>



