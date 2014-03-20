<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/styles.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>文章编辑</title>

    <style>
        #editor {
            max-height: 1024px;
            height: 1024px;
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

        #voiceBtn {
            width: 20px;
            color: transparent;
            background-color: transparent;
            transform: scale(2.0, 2.0);
            -webkit-transform: scale(2.0, 2.0);
            -moz-transform: scale(2.0, 2.0);
            border: transparent;
            cursor: pointer;
            box-shadow: none;
            -webkit-box-shadow: none;
        }

        div[data-role="editor-toolbar"] {
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        .dropdown-menu a {
            cursor: pointer;
        }
    </style>

</head>
<body>
<%@ include file="/header.jsp" %>
<div class="container" style="background:#FFF">

    <div>
        <ul class="breadcrumb">
            <li><a target="homePages" href="${ctx }/homePages">首页</a> <span class="divider">/</span></li>
            <li><a target="articles" href="${ctx }/articles">文章列表</a> <span class="divider">/</span></li>
            <li><a href="${ctx }/articles/new">再添加</a> <span class="divider">/</span></li>
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
            <input id="id" type="hidden" value="${article.id }"/>

            <div class="control-group">
                <label class="control-label" for="title">文章标题</label>

                <div class="controls">
                    <input id="title" class="span8" type="text" size=40 placeholder="输入文章标题" value="${article.title }"/>
                </div>
                
            </div>
            
            <div class="control-group">
                 <label class="control-label" for=image>标识图片</label>

                 <div class="controls" style="width:150px">
                        <!--dom结构部分-->
					<div id="uploader-demo">
						    <!--用来存放item-->
						<div id="fileList" class="uploader-list"></div>
						<div id="filePicker">选择图片</div>
					</div>
                </div>
             </div>
                
                
            <div class="control-group">
                <label class="control-label" for="columnId">所属栏目</label>

                <div class="controls">
                    <select id="columnId">
                        <c:forEach items="${columns }" var="column">
                            <c:if test="${null == article }">
                                <option value="${column.id }"
                                        <c:if test='${columnId==column.id }'>selected</c:if>  >${column.name }</option>
                            </c:if>
                            <c:if test="${null != article }">
                                <option value="${column.id }"
                                        <c:if test='${article.column.id==column.id }'>selected</c:if>  >${column.name }</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </form>
    </div>

    <div class="btn-toolbar" data-role="editor-toolbar"
         data-target="#editor">
        <div class="btn-group">
            <a class="btn dropdown-toggle" data-toggle="dropdown" title=""
               data-original-title="Font"><i class="icon-font"></i><b
                    class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a data-edit="fontName Serif"
                       style="font-family: &amp; amp;">Serif</a></li>
                <li><a data-edit="fontName Sans"
                       style="font-family: &amp; amp;">Sans</a></li>
                <li><a data-edit="fontName Arial"
                       style="font-family: &amp; amp;">Arial</a></li>
                <li><a data-edit="fontName Arial Black"
                       style="font-family: &amp; amp;">Arial Black</a></li>
                <li><a data-edit="fontName Courier"
                       style="font-family: &amp; amp;">Courier</a></li>
                <li><a data-edit="fontName Courier New"
                       style="font-family: &amp; amp;">Courier New</a></li>
                <li><a data-edit="fontName Comic Sans MS"
                       style="font-family: &amp; amp;">Comic Sans MS</a></li>
                <li><a data-edit="fontName Helvetica"
                       style="font-family: &amp; amp;">Helvetica</a></li>
                <li><a data-edit="fontName Impact"
                       style="font-family: &amp; amp;">Impact</a></li>
                <li><a data-edit="fontName Lucida Grande"
                       style="font-family: &amp; amp;">Lucida Grande</a></li>
                <li><a data-edit="fontName Lucida Sans"
                       style="font-family: &amp; amp;">Lucida Sans</a></li>
                <li><a data-edit="fontName Tahoma"
                       style="font-family: &amp; amp;">Tahoma</a></li>
                <li><a data-edit="fontName Times"
                       style="font-family: &amp; amp;">Times</a></li>
                <li><a data-edit="fontName Times New Roman"
                       style="font-family: &amp; amp;">Times New Roman</a></li>
                <li><a data-edit="fontName Verdana"
                       style="font-family: &amp; amp;">Verdana</a></li>
            </ul>
        </div>

        <div class="btn-group">
            <a class="btn dropdown-toggle" data-toggle="dropdown" title=""
               data-original-title="Font Size"><i class="icon-text-height"></i>&nbsp;<b
                    class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
                <li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
                <li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
            </ul>
        </div>

        <div class="btn-group">
            <a class="btn" data-edit="bold" title=""
               data-original-title="Bold (Ctrl/Cmd+B)"><i class="icon-bold"></i></a>
            <a class="btn" data-edit="italic" title=""
               data-original-title="Italic (Ctrl/Cmd+I)"><i
                    class="icon-italic"></i></a> <a class="btn" data-edit="strikethrough"
                                                    title="" data-original-title="Strikethrough"><i
                class="icon-strikethrough"></i></a> <a class="btn"
                                                       data-edit="underline" title=""
                                                       data-original-title="Underline (Ctrl/Cmd+U)"><i
                class="icon-underline"></i></a>
        </div>

        <div class="btn-group">
            <a class="btn" data-edit="insertunorderedlist" title=""
               data-original-title="Bullet list"><i class="icon-list-ul"></i></a>
            <a class="btn" data-edit="insertorderedlist" title=""
               data-original-title="Number list"><i class="icon-list-ol"></i></a>
            <a class="btn" data-edit="outdent" title=""
               data-original-title="Reduce indent (Shift+Tab)"><i
                    class="icon-indent-left"></i></a> <a class="btn" data-edit="indent"
                                                         title="" data-original-title="Indent (Tab)"><i
                class="icon-indent-right"></i></a>
        </div>

        <div class="btn-group">
            <a class="btn" data-edit="justifyleft" title=""
               data-original-title="Align Left (Ctrl/Cmd+L)"><i
                    class="icon-align-left"></i></a> <a class="btn"
                                                        data-edit="justifycenter" title=""
                                                        data-original-title="Center (Ctrl/Cmd+E)"><i
                class="icon-align-center"></i></a> <a class="btn"
                                                      data-edit="justifyright" title=""
                                                      data-original-title="Align Right (Ctrl/Cmd+R)"><i
                class="icon-align-right"></i></a> <a class="btn"
                                                     data-edit="justifyfull" title=""
                                                     data-original-title="Justify (Ctrl/Cmd+J)"><i
                class="icon-align-justify"></i></a>
        </div>

        <div class="btn-group">
            <a class="btn dropdown-toggle" data-toggle="dropdown" title=""
               data-original-title="Hyperlink"><i class="icon-link"></i></a>

            <div class="dropdown-menu input-append">
                <input class="span2" placeholder="URL" type="text"
                       data-edit="createLink">
                <button class="btn" type="button">Add</button>
            </div>
            <a class="btn" data-edit="unlink" title=""
               data-original-title="Remove Hyperlink"><i class="icon-cut"></i></a>
        </div>

        <div class="btn-group">
            <a class="btn" title="" id="pictureBtn"
               data-original-title="Insert picture (or just drag &amp; drop)"><i
                    class="icon-picture"></i></a> <input type="file"
                                                         data-role="magic-overlay" data-target="#pictureBtn"
                                                         data-edit="insertImage"
                                                         style="opacity: 0; position: absolute; top: 0px; left: 0px; width: 41px; height: 30px;">
        </div>

        <div class="btn-group">
            <a class="btn" data-edit="undo" title=""
               data-original-title="Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a>
            <a class="btn" data-edit="redo" title=""
               data-original-title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
        </div>

        <input type="text" data-edit="inserttext" id="voiceBtn"
               x-webkit-speech=""
               style="position: absolute; top: 58px; left: 1231px;">

    </div>

    <div id="editor" contenteditable="true">${content }</div>

</div>

<script type="text/javascript" src="${ctx }/js/article.js"></script>
</body>


</html>

