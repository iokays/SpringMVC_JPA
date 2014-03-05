<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>栏目编辑</title>
    <style>
        #editor {
            max-height: 256px;
            height: 256px;
            background-color: white;
            border-collapse: separate;
            border: 1px solid rgb(204, 204, 204);
            padding: 4px;
            box-sizing: content-box;
            -webkit-box-shadow: rgba(0, 0, 0, 0.0745098) 0px 1px 1px 0px inset;
            box-shadow: rgba(0, 0, 0, 0.0745098) 0px 1px 1px 0px inset;
            border-top-right-radius: 3px;
            border-bottom-right-radius: 3px;
            border-bottom-left-radius: 3px;
            border-top-left-radius: 3px;
            overflow: scroll;
            outline: none;
        }
    </style>
    <script>

        function buildData() {
            var name = $("#name").val();
            var grade = $("#grade").val();
            var parentId = $("#parentId").val();
            var sort = $("#sort").val();
            var template = $("#template").val();
            var description = $("#description").val();
            var image = $("#editor").html();
            image = image.replace("<img src=\"", "").replace("\">", "");
            switch (grade) {
                case "one":
                    return {"name": name, "grade": grade, "sort": sort, "template": template, "description": description, "image": image};
                case "two":
                    return {"name": name, "grade": grade, "parent.id": parentId, "sort": sort, "template": template, "description": description, "image": image};
            }
            ;
        }

        function insertData() {
            var data = buildData();
            $.ajax({
                type: "POST",
                url: "${ctx}/column",
                data: data,
                success: function (data) {
                    /* $("#id").val(data); */
                    $("#alert").attr("class", "alert alert-success");
                    $("#alert_text").html("添加成功<a style='float:right' target=blank href='${ctx}/" + data + ".html'>静态页</a>");
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
                url: "${ctx}/column/" + id,
                data: JSON.stringify(data),
                success: function () {
                    $("#alert").attr("class", "alert alert-success");
                    $("#alert_text").html("修改成功<a style='float:right' target=blank href='${ctx}/" + id + ".html'>静态页</a>");
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
            <li><a target="columns" href="${ctx }/columns">栏目列表</a> <span class="divider">/</span></li>
            <li><a href="${ctx }/column">再添加</a> <span class="divider">/</span></li>
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
            <input type="hidden" id="id" value="${column.id }">

            <div class="control-group">
                <label class="control-label" for="name">栏目名称</label>

                <div class="controls">
                    <input id="name" class="span6" type="text" size=40 placeholder="输入栏目名称" value="${column.name }"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="grade">栏目级别</label>

                <div class="controls">
                    <select id="grade">
                        <option value="one"
                                <c:if test='${"one"==column.grade }'>selected</c:if> >一级栏目
                        </option>
                        <option value="two"
                                <c:if test='${"two"==column.grade }'>selected</c:if> >二级栏目
                        </option>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="parent.id">所属栏目</label>

                <div class="controls">
                    <select id="parentId">
                        <c:forEach items="${columns }" var="object">
                            <option value="${object.id }"
                                    <c:if test='${column.parent.id==object.id }'>selected</c:if>  >${object.name }</option>
                        </c:forEach>

                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="sort">排序</label>

                <div class="controls">
                    <input id="sort" class="span6" type="text" size=40 placeholder="输入数字" value="${column.sort }"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for=template>模版</label>

                <div class="controls">
                    <input id="template" class="span6" type="text" size=40 placeholder="输入模版名称"
                           value="${column.template }"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="description">描述</label>

                <div class="controls">
                    <textarea id="description" class="span6" rows="3"
                              placeholder="栏目说明信息">${column.description }</textarea>
                </div>
            </div>

            <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
                <div class="control-group">
                    <label class="control-label" for=image>图片</label>

                    <div class="controls">
                        <div class="btn-group">
                            <a class="btn" title="" id="pictureBtn"
                               data-original-title="Insert picture (or just drag &amp; drop)"><i
                                    class="icon-picture"></i></a> <input onchange='javascript:$("#editor").html("");'
                                                                         type="file" data-role="magic-overlay"
                                                                         data-target="#pictureBtn"
                                                                         data-edit="insertImage"
                                                                         style="opacity: 0; position: absolute; top: 0px; left: 0px; width: 41px; height: 30px;">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editor"><img src="${column.image}"></div>
        </form>
    </div>


</div>

</body>
<script>
    $(function () {
        function initToolbarBootstrapBindings() {
            var fonts = [ 'Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
                'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact',
                'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
                'Times New Roman', 'Verdana' ], fontTarget = $(
                    '[title=Font]').siblings('.dropdown-menu');
            $
                    .each(
                    fonts,
                    function (idx, fontName) {
                        fontTarget
                                .append($('<li><a data-edit="fontName ' + fontName + '" style="font-family:\'' + fontName + '\'">'
                                        + fontName + '</a></li>'));
                    });
            $('a[title]').tooltip({
                container: 'body'
            });
            $('.dropdown-menu input').click(function () {
                return false;
            }).change(
                    function () {
                        $(this).parent('.dropdown-menu').siblings(
                                '.dropdown-toggle').dropdown('toggle');
                    }).keydown('esc', function () {
                        this.value = '';
                        $(this).change();
                    });

            $('[data-role=magic-overlay]').each(
                    function () {
                        var overlay = $(this), target = $(overlay
                                .data('target'));
                        overlay.css('opacity', 0).css('position', 'absolute')
                                .offset(target.offset()).width(
                                        target.outerWidth()).height(
                                        target.outerHeight());
                    });
            if ("onwebkitspeechchange" in document.createElement("input")) {
                var editorOffset = $('#editor').offset();
                $('#voiceBtn').css('position', 'absolute').offset({
                    top: editorOffset.top,
                    left: editorOffset.left + $('#editor').innerWidth() - 35
                });
            } else {
                $('#voiceBtn').hide();
            }
        }
        ;
        function showErrorAlert(reason, detail) {
            alert(detail);
            var msg = '';
            if (reason === 'unsupported-file-type') {
                msg = "Unsupported format " + detail;
            } else {
                console.log("error uploading file", reason, detail);
            }
            $(
                    '<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
                            + '<strong>File upload error</strong> '
                            + msg
                            + ' </div>').prependTo('#alerts');
        }
        ;

        function showSelect() {
            switch ($("#grade").val()) {
                case "one":
                    $("#parentId").parent().parent().hide();
                    break;
                case "two":
                    $("#parentId").parent().parent().show();
                    break;
            }
        };
        showSelect();
        $("#grade").change(function () {
            switch ($(this).val()) {
                case "one":
                    $("#parentId").parent().parent().hide();
                    break;
                case "two":
                    $("#parentId").parent().parent().show();
                    break;
            }
        });

        $(".close").click(function () {
            $(this).parent().hide();
        });

        initToolbarBootstrapBindings();
        $('#editor').wysiwyg({
            fileUploadError: showErrorAlert
        });
        window.prettyPrint && prettyPrint();

    });
</script>
</html>



