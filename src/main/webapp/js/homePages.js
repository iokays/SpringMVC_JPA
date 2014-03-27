function removeData(e, id) {
	$.ajax({
		type : "DELETE",
		url : ctx + "/homePages/" + id,
		success : function() {
			$(e).parents("tr").remove();
			$("#alert").attr("class", "alert alert-success");
			$("#alert_text").html("数据删除成功");
			$("#alert").css("display", "block");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#alert").attr("class", "alert alert-error");
			$("#alert_text").html("删除失败!");
			$("#alert").css("display", "block");
		}
	});
}

function generateStaticPage() {
	$.ajax({
		type : "GET",
		url : ctx + "/homePages/generateStaticPage",
		success : function() {
			$("#alert").attr("class", "alert alert-success");
			$("#alert_text").html("恭喜!...批量静态页生成成功！");
			$("#alert").css("display", "block");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#alert").attr("class", "alert alert-error");
			$("#alert_text").html("抱歉!...批量静态页生成失败!");
			$("#alert").css("display", "block");
		}
	});
}

$(function() {
	
	$(".close").click(function() {
		$(this).parent().hide();
	});
});

$("#homePagesHeader").addClass("active");