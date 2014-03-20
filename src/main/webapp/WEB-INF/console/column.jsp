<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>栏目编辑</title>
   
</head>
<body>
<%@ include file="/header.jsp" %>
<div class="container" style="background: #FFF">

    <div>
        <ul class="breadcrumb">
            <li><a target="homePages" href="${ctx }/homePages">首页</a> <span class="divider">/</span></li>
            <li><a target="columns" href="${ctx }/columns">栏目列表</a> <span class="divider">/</span></li>
            <li><a href="${ctx }/columns/new">再添加</a> <span class="divider">/</span></li>
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
                <label class="control-label" for="name">标识</label>

                <div class="controls">
                    <input id="marking" class="span6" type="text" size=40 placeholder="输入前端标识" value="${column.marking }"/>
                </div>
            </div>
            
            <div class="control-group">
                <label class="control-label" for="name">模版</label>

                <div class="controls">
                    <input id="template" class="span6" type="text" size=40 placeholder="输入模版名称" value="${column.template }"/>
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
                            <option value="${object.id }" <c:if test='${column.parent.id==object.id }'>selected</c:if>  >${object.name }</option>
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
                <label class="control-label" for="description">描述</label>

                <div class="controls">
                    <textarea id="description" class="span6" rows="3"
                              placeholder="栏目说明信息">${column.description }</textarea>
                </div>
            </div>

            
                <div class="control-group">
                    <label class="control-label" for=image>图片</label>

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

<script type="text/javascript" src="${ctx }/js/column.js"></script>
</body>

</html>



