<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
	<%@ include file="/common/global.jsp"%>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include-jquery-ui-theme.jsp" %>
	<%@ include file="/common/include-custom-styles.jsp" %>
	
	<script type="text/javascript">
		    $(function () {

		        // Accordion
		        $("#accordion").accordion({ header: "h3" });

		        // Tabs
		        $('#tabs').tabs();

		        // Button
		        $("#button").button();
		        $("#button-disabled").button().addClass("ui-state-disabled").attr("disabled", true);
		        $("#radioset").buttonset();
		        
		        // Dialog			
		        $('#dialog').dialog({
		            autoOpen: false,
		            width: 600,modal:true,
		            buttons: {
		                "Ok": function () {
		                    $(this).dialog("close");
		                },
		                "Cancel": function () {
		                    $(this).dialog("close");
		                }
		            }
		        });

		        // Dialog Link
		        $('#dialog_link').click(function () {
		            $('#dialog').dialog('open');
		            return false;
		        });

		        // Autocomplete
		        $("#autocomplete").autocomplete({
		            source: ["c++", "java", "php", "coldfusion", "javascript", "asp", "ruby", "python", "c", "scala", "groovy", "haskell", "perl"]
		        });

		        // Datepicker
		        $('#datepicker').datepicker({
		            inline: true
		        });

		        // Slider
		        $('#slider').slider({
		            range: true,
		            values: [17, 67]
		        });
		        var values = [50, 80, 20, 40, 70];
		        $("#verticalSliders > div").each(function (i, item) {
		            $(item).slider({ orientation: "vertical", range: "min", min: 0, max: 100, value: values[i] });
		        });

		        // Progressbar
		        $("#progressbar").progressbar({
		            value: 20
		        });

		        //hover states on the static widgets
		        $('#dialog_link, ul#icons li').hover(
					function () { $(this).addClass('ui-state-hover'); },
					function () { $(this).removeClass('ui-state-hover'); }
				);

		    });
		</script>
	
	<style type="text/css">
			body{ font-family:"Segoe UI", Helvetica, Verdana;font-size: 11px; margin: 0px;}
			#wrapper{width:700px;}
			h1{font-weight:normal;}
			.header { margin-top: 2em;font-weight:normal; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
			#verticalSliders{height:140px;padding-top:20px;}
            #verticalSliders > div{float:left;margin:20px;}
	</style>
</head>
<body>
	<h1>基于SpringMVC_JPA 全注解框架搭建</h1>
	<h2>实现功能</h2>
	<p style="font-size: 1.3em; line-height: 1.5; margin: 1em 0;">1.
		SpringMVC，Hibernate 基于JPA的搭建； 2. 添加了Spring Security安全框架 [功能已实现，尚未添加权限的业务处理]; </p>

	<h2 class="header">Accordion</h2>
	<div id="accordion">
		<div>
			<h3>
				<a href="#">系统管理</a>
			</h3>
			<div>
				用户列表<br> 角色列表<br> 权限列表<br> 资源列表
			</div>
		</div>
		<div>
			<h3>
				<a href="#">JPBM6工作流</a>
			</h3>
			<div>Phasellus mattis tincidunt nibh.</div>
		</div>
		<div>
			<h3>
				<a href="#">Third</a>
			</h3>
			<div>Nam dui erat, auctor a, dignissim quis.</div>
		</div>
	</div>
</body>
</html>