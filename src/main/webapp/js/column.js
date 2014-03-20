function buildData() {
	var name = $("#name").val();
	var marking = $("#marking").val();
	var grade = $("#grade").val();
	var parentId = $("#parentId").val();
	var sort = Number($("#sort").val());
	var template = $("#template").val();
	var description = $("#description").val();
	switch (grade) {
	case "one":
		return {
			"name" : name,
			"marking" : marking,
			"grade" : grade,
			"sort" : sort,
			"template" : template,
			"description" : description,
			"timeInMillis" : timeInMillis
		};
	case "two":
		return {
			"name" : name,
			"marking" : marking,
			"grade" : grade,
			"parent.id" : parentId,
			"sort" : sort,
			"template" : template,
			"description" : description,
			"timeInMillis" : timeInMillis
		};
	}
	;
}

function insertData() {
	var data = buildData();
	$.ajax({
		type : "POST",
		url : ctx + "/columns",
		data : data,
		success : function(data) {
			/* $("#id").val(data); */
			$("#alert").attr("class", "alert alert-success");
			$("#alert_text").html(
					"添加成功<a style='float:right' target=blank href='" + ctx + "/"
							+ data.marking + ".html'>静态页</a>");
			$("#alert").css("display", "block");
		},
		error : function() {
			$("#alert").attr("class", "alert alert-error");
			$("#alert_text").html("添加失败");
			$("#alert").css("display", "block");
		}
	});
}

function updateData(id) {
	var data = buildData();
	$.ajax({
		type : "PUT",
		url : ctx + "/columns/" + id,
		data : JSON.stringify(data),
		success : function() {
			$("#alert").attr("class", "alert alert-success");
			$("#alert_text").html(
					"修改成功<a style='float:right' target=blank href='" + ctx + "/"
							+ data.marking + ".html'>静态页</a>");
			$("#alert").css("display", "block");
		},
		error : function() {
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

$(function() {
	var $ = jQuery, $list = $('#fileList'), ratio = window.devicePixelRatio || 1, thumbnailWidth = 1000 * ratio, thumbnailHeight = 200 * ratio, uploader;
	
	// 初始化Web Uploader
	var uploader = WebUploader.create({
		// 选完文件后，是否自动上传。
		auto : true,

		// swf文件路径
		swf : "${ctx}/dist/webuploader/1.0/Uploader.swf",

		// 文件接收服务端。
		server : ctx + '/columns/fileupload?timeInMillis=' + timeInMillis,

		fileNumLimit : 1,

		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : {
			id : filePicker,
			multiple : false
		},

		// 只允许选择图片文件。
		accept : {
			title : 'Images',
			extensions : 'gif,jpg,jpeg,bmp,png',
			mimeTypes : 'image/*'
		}
	});// 当有文件添加进来的时候

	uploader.on('fileQueued', function(file) {
		var $li = $('<div id="' + file.id + '" class="file-item thumbnail">'
				+ '<img>' + '<div class="info">' + file.name + '</div>'
				+ '</div>'), $img = $li.find('img');

		// $list为容器jQuery实例
		$list.append($li);

		// 创建缩略图
		// 如果为非图片文件，可以不用调用此方法。
		// thumbnailWidth x thumbnailHeight 为 100 x 100
		uploader.makeThumb(file, function(error, src) {
			if (error) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}

			$img.attr('src', src);
		}, thumbnailWidth, thumbnailHeight);
	});
	
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress span');

	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<p class="progress"><span></span></p>')
	                .appendTo( $li )
	                .find('span');
	    }

	    $percent.css( 'width', percentage * 100 + '%' );
	});

	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on( 'uploadSuccess', function( file ) {
	    $( '#'+file.id ).addClass('upload-state-done');
	});

	// 文件上传失败，显示上传出错。
	uploader.on( 'uploadError', function( file ) {
	    var $li = $( '#'+file.id ),
	        $error = $li.find('div.error');

	    // 避免重复创建
	    if ( !$error.length ) {
	        $error = $('<div class="error"></div>').appendTo( $li );
	    }

	    $error.text('上传失败');
	});

	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on( 'uploadComplete', function( file ) {
	    $( '#'+file.id ).find('.progress').remove();
	});
	
	$(".close").click(function() {
		$(this).parent().hide();
	});

});