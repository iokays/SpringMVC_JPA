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

$(function() {
	$(".close").click(function() {
		$(this).parent().hide();
	});
});