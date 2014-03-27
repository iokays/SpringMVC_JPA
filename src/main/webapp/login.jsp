<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>后台维护</title>

<style type="text/css">
*{margin:0;padding: 0;}
body{font-family:"宋体";}
.loginBox{width:420px;height:280px;padding:0 20px;border:1px solid #fff; color:#000; margin-top:40px; border-radius:8px;background: white;box-shadow:0 0 15px #222; background: -moz-linear-gradient(top, #fff, #efefef 8%);background: -webkit-gradient(linear, 0 0, 0 100%, from(#f6f6f6), to(#f4f4f4));font:11px/1.5em 'Microsoft YaHei' ;position: absolute;left:50%;top:50%;margin-left:-210px;margin-top:-165px;}
.loginBox h2{height:45px;font-size:20px;font-weight:normal;}
.loginBox .left{border-right:1px solid #ccc;height:100%;padding-right: 20px; }
.regBtn{margin-top:21px;}
</style>


</head>
<body>
	<div class="container" style="background: #FFF">
		<div class="loginBox row">
			<form action="${ctx }/j_spring_security_check" method="post" >
				<fieldset>
					<legend>后台维护</legend>
					<label>用户名</label> <input type="text" name="j_username" placeholder="用户名">
					<label>密码</label>
					<input type="password"  name="j_password"  placeholder="密码">
					
				</fieldset>
				<button type="submit" class="btn">登录</button>
			</form>
		</div>
	</div>
	<!--.content-->

</body>

</html>



