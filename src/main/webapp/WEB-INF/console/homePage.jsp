<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>首页编辑</title>
    
</head>
<body>
<%@ include file="/header.jsp" %>
	<div class="container" style="background: #FFF">
		
	    <div>
	        <ul class="breadcrumb">
	            <li><a target="homePages" href="${ctx }/homePages">首页管理</a> <span class="divider">/</span></li>
	            <li><a href="${ctx }/homePages">再添加</a> <span class="divider">/</span></li>
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
	                <label class="control-label" for="target">图片链接</label>
	
	                <div class="controls">
	                    <input id="target" class="span6" type="text" size=40 placeholder="输入图片链接" value="${homePage.target }"/>
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
	            
	            <div class="control-group">
	            	<label class="control-label" for="sort">已上传(未保存)</label>
	            	<div class="controls">
	            		<img alt="" src="D:/news/home/1111111111111111111.jpg">
	            	</div>
	            	
	            </div>
	            
	            <div class="control-group">
	            	<label class="control-label" for="sort">已存储图片</label>
	            </div>
	            
	        </form>
	    </div>
	</div>
	
<script type="text/javascript" src="${ctx }/js/homePage.js"></script>
</body>

</html>



