function removeData(e, id) {
	$.ajax({
		type : "DELETE",
		url : "${ctx}/columns/" + id,
		success : function(data) {
			$(e).parents("tr").remove();
			$("#alert").attr("class", "alert alert-success");
			if (0 == data) {
				$("#alert_text").html("数据不存在,或者已删除");
			} else {
				$("#alert_text").html("数据删除成功");
			}
			$("#alert").css("display", "block");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#alert").attr("class", "alert alert-error");
			$("#alert_text").html("删除失败!");
			$("#alert").css("display", "block");
		}
	});
}

$(function() {
	$(".close").click(function() {
		$(this).parent().hide();
	});
});