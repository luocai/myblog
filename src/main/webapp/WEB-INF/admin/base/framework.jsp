<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html>
<head>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath()); /*根路径  */
%>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  
  <title>诸葛财财博客的后台 <rapid:block name="title"></rapid:block></title>
  
  <script src="${APP_PATH }/static/layui/layui.js"></script>
  <link rel="stylesheet" href="${APP_PATH }/static/layui/css/layui.css">
  
  <!-- 引入bootstrap和jQuery -->
  <link rel="stylesheet" href="${APP_PATH }/static/css/bootstrap.min.css">
  <script src="${APP_PATH }/static/js/jquery-3.2.1.min.js"></script>
   <script src="${APP_PATH }/static/js/bootstrap.min.js"></script>
  
  <rapid:block name="header-style"></rapid:block>
    <rapid:block name="header-script"></rapid:block>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">诸葛财财博客的后台</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="main">首页</a></li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="mylog/static/images/head.jpg" class="layui-nav-img">
          诸葛财财
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="../layout">退出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">博文管理</a>
          <dl class="layui-nav-child">
            <dd><a href="articleList">全部博文</a></dd>
            <dd><a href="write">写博文</a></dd>
            <dd><a href="categoryList">分类管理</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">评论管理</a>
	          <dl class="layui-nav-child">
	            <dd><a href="commentList">查看评论</a></dd>
	          </dl>
        </li>
        <li class="layui-nav-item">
        	<a href="javascript:;">用户管理</a>
	       	<dl class="layui-nav-child">
	            <dd><a href="javascript:;">所有用户</a></dd>
	         </dl>
        </li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <rapid:block name="content">
            
            </rapid:block>
        </div>
    </div>
  
</div>

<rapid:block name="footer-script">

</rapid:block>

<script>
	
	layui.use('element', function(){
	  var element = layui.element;
	  
	});
	
</script>
</body>
</html>