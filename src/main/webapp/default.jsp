<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/metro_styles.jsp"%>
<%@ include file="/common/meta.jsp"%>
    <title>基于SpringMVC_JPA 全注解框架搭建</title>
</head>
<body class="modern-ui">

<div class="page">
<div class="nav-bar">
    <div class="nav-bar-inner padding10">
        <span class="pull-menu"></span>
        <a href="index.html"><span class="element brand">
            <img class="place-left" src="images/logo32.png" style="height: 20px"/>
           	基于SpringMVC_JPA 全注解框架搭建 <small></small>
        </span></a>

        <div class="divider"></div>

        <ul class="menu">
            <li><a href="">首页</a></li>
            <li data-role="dropdown">
                <a href="#">脚手架</a>
                <ul class="dropdown-menu">
                    <li><a href="global.html">全局样式</a></li>
                    <li><a href="layout.html">布局与模板</a></li>
                    <li><a href="grid.html">网格系统</a></li>
                    <li class="divider"></li>
                    <li><a href="responsive.html">响应式设计</a>
                    </li>
                </ul>
            </li>
             <li data-role="dropdown"><a href="#">基本样式</a>
                <ul class="dropdown-menu">
                    <li><a href="typography.html">排版</a></li>
                    <li><a href="tables.html">表格</a></li>
                    <li><a href="forms.html">表单</a></li>
                    <li><a href="buttons.html">按钮</a></li>
                    <li><a href="images.html">图像</a></li>
                    <li class="divider"></li>
                    <li><a href="icons.html">图标</a></li>
                </ul>
            </li>
             <li data-role="dropdown"><a href="#">组件</a>
                <ul class="dropdown-menu">
                    <li><a href="tiles.html">Tiles</a></li>
                    <li><a href="menus.html">菜单与导航</a></li>
                    <li><a href="sidebar.html">侧边栏</a></li>
                    <li><a href="pagecontrol.html">多页控件</a></li>
                    <li><a href="accordion.html">手风琴</a></li>
                    <li><a href="buttons-set.html">按钮组</a></li>
                    <li><a href="rating.html">评分</a></li>
                    <li><a href="progress.html">进度条</a></li>
                    <li><a href="carousel.html">幻灯片</a></li>
                    <li><a href="listview.html">列表视图</a></li>
                    <li><a href="slider.html">滑块</a></li>
                    <li><a href="dialog.html">对话框</a></li>
                    <li class="divider"></li>
                    <li><a href="notices.html">通知与回复</a></li>
                    <li class="divider"></li>
                    <li><a href="cards.html">额外惊喜 - 一副扑克</a></li>
                </ul>
            </li>
            
            <li data-role="dropdown">
                <a href="#">工作流管理</a>
                <ul class="dropdown-menu">
                    <li><a href="global.html">工作流定义列表</a></li>
                    <li><a href="layout.html">工作流实例列表</a></li>
                    <li><a href="layout.html">工作流任务列表</a></li>
                    <li class="divider"></li>
                    <li><a href="responsive.html">工作流用户组列表</a>
                    </li>
                </ul>
            </li>
            
            <li data-role="dropdown">
                <a href="#">系统管理</a>
                <ul class="dropdown-menu">
                    <li><a href="global.html">用户列表</a></li>
                    <li><a href="layout.html">角色列表</a></li>
                    <li><a href="grid.html">权限列表</a></li>
                    <li><a href="grid.html">资源列表</a></li>
                </ul>
            </li>
            
			<li><a href="https://github.com/olton/Metro-UI-CSS" target="_blank">源码</a></li>
        </ul>

    </div>
</div>
</div>

<div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>全局<small>样式</small></h1>
                <a class="back-button big page-back" href="/"></a>
            </div>
        </div>

        <div class="page-region">
            <div class="page-region-content">
                <div class="grid">
                    <div class="row">
                        <div class="span10">
                            <h2>必须使用HTML5 doctype</h2>
                            <p>Metro UI CSS使用了某些HTML元素和CSS属性,它们需要使用HTML5 doctype。 将HTML5 doctype应用在你所有的项目中。</p>

<pre class="prettyprint linenums"><ol class="linenums"><li class="L0"><span class="pln">    </span><span class="dec">&lt;!DOCTYPE html&gt;</span></li><li class="L1"><span class="pln">    </span><span class="tag">&lt;html</span><span class="pln"> </span><span class="atn">lang</span><span class="pun">=</span><span class="atv">"en"</span><span class="tag">&gt;</span></li><li class="L2"><span class="pln">      ...</span></li><li class="L3"><span class="pln">    </span><span class="tag">&lt;/html&gt;</span></li></ol></pre>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span10">
                            <h2>重置样式</h2>
                            <p>
                               在Metro UI CSS使用的是<a href="http://nicolasgallagher.com/">Nicolas Gallaher</a>写的<a href="http://github.com/necolas/normalize.css">normalize.css</a>。
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span3">
                            <h2>颜色</h2>
                            <ul class="unstyled">
                                <li style="height: 40px;"><span class="square bg-color-green"></span>*-color-green</li>
                                <li style="height: 40px;"><span class="square bg-color-greenDark"></span>*-color-greenDark</li>
                                <li style="height: 40px;"><span class="square bg-color-greenLight"></span>*-color-greenLight</li>
                                <li style="height: 40px;"><span class="square bg-color-pink"></span>*-color-pink</li>
                                <li style="height: 40px;"><span class="square bg-color-pinkDark"></span>*-color-pinkDark</li>
                            </ul>
                        </div>
                        <div class="span3">
                            <h2>&nbsp;</h2>
                            <ul class="unstyled">
                                <li style="height: 40px;"><span class="square bg-color-yellow"></span>*-color-yellow</li>
                                <li style="height: 40px;"><span class="square bg-color-darken"></span>*-color-darken</li>
                                <li style="height: 40px;"><span class="square bg-color-purple"></span>*-color-purple</li>
                                <li style="height: 40px;"><span class="square bg-color-blue"></span>*-color-blue</li>
                                <li style="height: 40px;"><span class="square bg-color-blueDark"></span>*-color-blueDark</li>
                            </ul>
                        </div>
                        <div class="span3">
                            <h2>&nbsp;</h2>
                            <ul class="unstyled">
                                <li style="height: 40px;"><span class="square bg-color-blueLight"></span>*-color-blueLight</li>
                                <li style="height: 40px;"><span class="square bg-color-orange"></span>*-color-orange</li>
                                <li style="height: 40px;"><span class="square bg-color-orangeDark"></span>*-color-orangeDark</li>
                                <li style="height: 40px;"><span class="square bg-color-red"></span>*-color-red</li>
                                <li style="height: 40px;"><span class="square bg-color-white"></span>*-color-white</li>
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span10">
                            <p>设置背景元素时添加前缀<strong>bg</strong>,设置文本颜色时添加前缀 <strong>fg</strong></p>
<pre class="prettyprint linenums"><ol class="linenums"><li class="L0"><span class="pln">    </span><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"bg-color-red"</span><span class="tag">&gt;</span><span class="pln">...</span><span class="tag">&lt;/div&gt;</span></li><li class="L1"><span class="pln">    </span><span class="tag">&lt;span</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"fg-color-blue"</span><span class="tag">&gt;</span><span class="pln">...</span><span class="tag">&lt;/span&gt;</span></li></ol></pre>
                        </div>
                    </div>

                    <div class="row">
                        <div class="span10">
                            <p>设置边框颜色时添加前缀 <strong>border</strong></p>
                            <div class="clearfix">
                                <div class="span2 border-color-blue">
                                    &nbsp;
                                </div>
                                <div class="span2 border-color-green">
                                    &nbsp;
                                </div>
                                <div class="span2 border-color-red">
                                    &nbsp;
                                </div>
                                <div class="span2 border-color-orange">
                                    &nbsp;
                                </div>
                                <div class="span2 border-color-darken">
                                    &nbsp;
                                </div>
                            </div>
<pre class="prettyprint linenums"><ol class="linenums"><li class="L0"><span class="pln">    </span><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"border-color-red"</span><span class="tag">&gt;</span><span class="pln">...</span><span class="tag">&lt;/div&gt;</span></li></ol></pre>
                        </div>
                    </div>

                    <div class="row">
                        <div class="span10">
                            <p>为组件(比如tile, 列表项,等等.)添加轮廓outline时使用前缀 <strong>outline</strong></p>
                            <div class="clearfix">
                                <div class="tile outline-color-blue">
                                    &nbsp;
                                </div>
                                <div class="tile outline-color-green">
                                    &nbsp;
                                </div>
                                <div class="tile outline-color-red">
                                    &nbsp;
                                </div>
                                <div class="tile outline-color-orange">
                                    &nbsp;
                                </div>
                            </div>
<pre class="prettyprint linenums"><ol class="linenums"><li class="L0"><span class="pln">    </span><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"tile outline-color-red"</span><span class="tag">&gt;</span><span class="pln">...</span><span class="tag">&lt;/div&gt;</span></li></ol></pre>
                        </div>
                    </div>

                    <div class="row">
                        <div class="span10">
                            <h2>字体</h2>
                            <p class="span10">
                               在Metro UI CSS 中，我使用Segoe UI、<a href="http://www.google.com/webfonts#UsePlace:use/Collection:Open+Sans">Open Sans</a>字体(取决于系统是否支持).
                            </p>
                        </div>
                    </div>
                </div>

                <div class="grid">
                    <div class="row">
                                            </div>
                </div>

            </div>
        </div>
    </div>
    <div class="page">
        <div class="nav-bar">
            <div class="nav-bar-inner padding10">
                <span class="element">
                   test
                </span>
            </div>
        </div>
    </div>
    </body>
    
    <script type="text/javascript" src="${ctx }/ajax/libs/jquery/${jqueryVersion}/jquery.js"></script>
    <script type="text/javascript" src="${ctx }/ajax/libs/jquery_mousewheel/${jquery_mousewheelVersion}/jquery.mousewheel.js"></script>
    <script type="text/javascript" src="${ctx }/js/modern/dropdown.js"></script>
    <script type="text/javascript" src="${ctx }/js/modern/accordion.js"></script>
    <script type="text/javascript" src="${ctx }/js/modern/buttonset.js"></script>
    <script type="text/javascript" src="${ctx }/js/modern/carousel.js"></script>
    <script type="text/javascript" src="${ctx }/js/modern/input-control.js"></script>
    <script type="text/javascript" src="${ctx }/js/modern/pagecontrol.js"></script>
    <script type="text/javascript" src="${ctx }/js/modern/rating.js"></script>
    <script type="text/javascript" src="${ctx }/js/modern/slider.js"></script>
    <script type="text/javascript" src="${ctx }/js/modern/tile-slider.js"></script>
    <script type="text/javascript" src="${ctx }/js/modern/tile-drag.js"></script>
    <script type="text/javascript" src="${ctx }/ajax/libs/prettify/r224/prettify.js"></script>
    
</html>