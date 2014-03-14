<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>首页编辑</title>
    <script>

        function buildData() {
            var name = $("#name").val();
            var url = $("#url").val();
            var sort = $("#sort").val();
            var image = $("#editor").html();
            image = image.replace("<img src=\"", "").replace("\">", "");
            return {"name": name, "url": url, "sort": sort, "image": image};
        }

        function insertData() {
            var data = buildData();
            $.ajax({
                type: "POST",
                url: "${ctx}/homePage",
                data: data,
                success: function (data) {
                    /* $("#id").val(data); */
                    $("#alert").attr("class", "alert alert-success");
                    $("#alert_text").html("添加成功<a style='float:right' target=blank href='${ctx}/index.html'>静态页</a>");
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
                url: "${ctx}/homePage/" + id,
                data: JSON.stringify(data),
                success: function () {
                    $("#alert").attr("class", "alert alert-success");
                    $("#alert_text").html("修改成功<a style='float:right' target=blank href='${ctx}/index.html'>静态页</a>");
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
	            <li><a target="homePages" href="${ctx }/homePages">首页管理</a> <span class="divider">/</span></li>
	            <li><a href="${ctx }/homePage">再添加</a> <span class="divider">/</span></li>
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
	            <input type="hidden" id="id" value="${homePage.id }">
	
	            <div class="control-group">
	                <label class="control-label" for="name">图片名称</label>
	
	                <div class="controls">
	                    <input id="name" class="span6" type="text" size=40 placeholder="输入图片名称" value="${homePage.name }"/>
	                </div>
	            </div>
	
	            <div class="control-group">
	                <label class="control-label" for="url">图片链接</label>
	
	                <div class="controls">
	                    <input id="url" class="span6" type="text" size=40 placeholder="输入图片链接" value="${homePage.url }"/>
	                </div>
	            </div>
	
	            <div class="control-group">
	                <label class="control-label" for="sort">排序</label>
	
	                <div class="controls">
	                    <input id="sort" class="span6" type="text" size=40 placeholder="输入数字" value="${homePage.sort }"/>
	                </div>
	            </div>
	            
	            
	            <div class="control-group">
	                <label class="control-label" for="sort">图片</label>
					
					<div class="controls">
		                <!--dom结构部分-->
						<div id="uploader-demo">
						    <!--用来存放item-->
						    <div id="fileList" class="uploader-list"></div>
						    <div id="filePicker">选择图片</div>
						</div>
					</div>
	            </div>
	            
	        </form>
	    </div>
	</div>
</body>
<script type="text/javascript">
//初始化Web Uploader
var uploader = WebUploader.create({
	// 选完文件后，是否自动上传。
    auto: true,
    
    // swf文件路径
    swf:"${ctx}/dist/webuploader/1.0/Uploader.swf",

    // 文件接收服务端。
    server: '${ctx}/homePages/fileupload',

    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#filePicker',

    // 只允许选择图片文件。
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    }
});// 当有文件添加进来的时候


uploader.on( 'fileQueued', function( file ) {
    var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
                '<div class="info">' + file.name + '</div>' +
            '</div>'
            ),
        $img = $li.find('img');


    // $list为容器jQuery实例
    $list.append( $li );

    // 创建缩略图
    // 如果为非图片文件，可以不用调用此方法。
    // thumbnailWidth x thumbnailHeight 为 100 x 100
    uploader.makeThumb( file, function( error, src ) {
        if ( error ) {
            $img.replaceWith('<span>不能预览</span>');
            return;
        }

        $img.attr( 'src', src );
    }, thumbnailWidth, thumbnailHeight );
});

</script>

</html>



