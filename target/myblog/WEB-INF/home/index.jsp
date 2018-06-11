<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>CAI'S BLOG</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="author" content="John Doe">
<meta name="designer" content="minfive">
<meta name="keywords" content="minfive, minfive blog, 前端博客, 前端, 程序员, 前端开发, 全栈开发, node.js, javascript">
<meta name="description" content="个人博客，用于分享一些在日常学习工作甚至于生活中遇到的一些比较有趣的东西。七荤八素，胡言乱语，望各位看官见谅。">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=yes">
<meta name="mobile-web-app-capable" content="yes">
<meta name="robots" content="all">
<link rel="canonical" href="index">
<link rel="icon" type="image/png" href="http://oo12ugek5.bkt.clouddn.com/blog/images/favicon.ico" sizes="32x32">
<link rel="stylesheet" href="static/css/index.css">
<link rel="alternate" href="/atom.xml" title="MINFIVE">
<script>var _hmt=_hmt||[];!function(){var e=document.createElement("script");e.src="https://hm.baidu.com/hm.js?e3267498201dfa9699a5c509424709d6";var t=document.getElementsByTagName("script")[0];t.parentNode.insertBefore(e,t)}()</script>
<link rel="stylesheet" href="static/css/index_1.css">
</head>
<body ontouchstart>
<div id="page-loading" class="page page-loading" style="background-image:url(static/images/loader.gif)">
</div>
<div id="page" class="page js-hidden">
	<header id="page-header" class="page__header" style="background-image:url(static/images/banner.jpeg)"><nav class="page__navbar">
	<div class="page__container navbar-container">
		<a class="page__logo" href="index" title="MINFIVE" alt="MINFIVE"><img src="static/picture/logo-text-white.png" alt="MINFIVE"></a><nav class="page__nav">
		<ul class="nav__list clearfix">
			<li class="nav__item"><a href="##" alt="首页" title="首页">首页</a></li>
			<li class="nav__item"><a href="archives" alt="归档" title="归档">归档</a></li>
			<li class="nav__item"><a href="friends" alt="友链" title="友链">友链</a></li>
			<li class="nav__item"><a href="##" alt="音乐" title="音乐">音乐</a></li>
			<li class="nav__item"><a href="about" alt="关于" title="关于">关于</a></li>
		</ul>
		</nav><button class="page__menu-btn" type="button"><i class="iconfont icon-menu"></i></button>
	</div>
	</nav><section class="page__info">
	<h1 class="info__title">诸葛财财</h1>
	<hr class="info__hr">
	<p class="info__desc">
		天青色等烟雨
	</p>
	</section></header><main class="page__container page__main">
	<div class="page__content">
		<div class="page__posts clearfix">
			
			<c:forEach items="${articles }" var="article">
				<div class="page__post">
					<article itemscope itemtype="http://schema.org/Article" class="page__mini-article">
					<div class="mini-article__cover">
						<img itemprop="image" src="${article.cover }" alt="${article.articleTitle}">
						<div itemprop="datePublished" content="" class="mini-article__date">
							<span class="date__day">${article.publishDay }</span><span class="date__month">${article.publishMonth }月</span>
						</div>
						<a itemprop="url" class="iconfont icon-enter" href="article?id=${article.articleId}"></a>
					</div>
					<div class="mini-article__info">
						<h3 itemprop="name" class="mini-article__title"><a itemprop="url" href="article?id=${article.articleId}" title="${article.articleTitle}">${article.articleTitle}</a></h3>
						<p class="mini-article__author">
							by <span itemprop="author" itemscope itemtype="http://schema.org/Person"><a itemprop="url" href="https://github.com/Mrminfive" target="_blank"><span itemprop="name">财财</span></a></span>
						</p>
						<p itemprop="articleSection" class="min-article__desc">
							${article.summary}
						
						</p>
						<div class="min-article__tags">
							<img alt="标签" id="tagImg" src="static/images/tag.png">
							<ul class="tags__list clearfix">
								<li class="tags__item"><a href="##">${article.categoryName}</a></li>
								<!-- <li class="tags__item"><a href="/tags/vue/">vue</a></li>
								<li class="tags__item"><a href="/tags/权限管理/">权限管理</a></li> -->
							</ul>
						</div>
					</div>
					</article>
				</div>
			</c:forEach>
			
		</div>
		<nav class="page__paginator">
			<ul class="paginator__list clearfix">
			 	<c:forEach begin="${pageInfo.navigateFirstPage }" end="${pageInfo.navigateLastPage}" step="1" var="pageNo">
			 		<c:choose>
			 			<c:when test="${pageInfo.pageNum==pageNo}">
			 				<li class="paginator__item"><span>${pageNo }</span></li>
			 			</c:when>
			 			<c:otherwise>
			 				<li class="paginator__item"><a href="index?page=${pageNo}">${pageNo}</a></li>
			 			</c:otherwise>
			 		</c:choose>
	          	</c:forEach>
	          	<c:if test="${pageInfo.isHasNextPage() !=false }">
	          		<li class="paginator__item"><a href="index?page=${pageInfo.pageNum+1}" title="下一页"><img alt="更多" id="moreImg" src="static/images/next.png"></a></li>
	          	</c:if>
				
			</ul>
		</nav>
	</div>
	<aside class="page__sidebar">
	<form id="page-search-from" class="page__search-from" action="search">
		<label class="search-form__item"><input class="input" type="text" name="search" placeholder="Search..."><i class="iconfont icon-search"></i></label>
	</form>
	<div class="sidebar__block">
		<h3 class="block__title">简介</h3>
		<p class="block__text">
			个人博客，用于分享自己的知识、想法、心情等，写东西比较随意。欢迎大家在文章评论区和我交流。
		</p>
	</div>
	<div class="sidebar__block">
		<h3 class="block__title">联系方式</h3>
		<p class="block__text">
			<a href="https://github.com/luocai" target="_blank"><img id="github" style="width:30px; height:30px;"alt="github" src="static/images/github.png"></a>
			<a href="https://www.zhihu.com/people/cai-cai-39-91-38/activities" target="_blank"><img id="zhihu" style="width:30px; height:30px; margin-left:10px;" alt="zhihu" src="static/images/zhihu2.png"></a>			
			<a href="http://qm.qq.com/cgi-bin/qm/qr?k=UAh8kjMkT7slRjgDCibhNs-bueGY4iD5" target="_blank"><img id="qq" style="width:30px; height:30px; margin-left:10px;" alt="qq" src="static/images/QQ.png"></a>
			<a href="mailto:13540680230@163.com" target="_blank"><img id="mail" style="width:30px; height:30px; margin-left:10px;" alt="mail" src="static/images/mail.png"></a>
			<a href="https://weibo.com/thejaycn?refer_flag=1001030102_&is_hot=1" target="_blank"><img id="weibo" style="width:30px; height:30px; margin-left:10px;" alt="weibo" src="static/images/weibo.png"></a>
		</p>
	</div>
	<div class="sidebar__block">
		<h3 class="block__title">文章分类</h3>
		<ul class="block-list">
			<c:forEach items="${categoryList }" var="category"> 
				<li class="block-list-item"><a class="block-list-link" href="queryByCategory?cid=${category.categoryId }">${category.categoryName }</a><span class="block-list-count">${category.articleCountPublished }</span></li>
			</c:forEach>
		</ul>
	</div>
	<div class="sidebar__block">
		<h3 class="block__title">最新文章</h3>
		<ul class="block-list latest-post-list">
			<c:forEach items="${articles }" var="article" begin="0" end="2">
				<li class="latest-post-item">
					<a href="article?id=${article.articleId }" title="${article.articleTitle }">
						<div class="item__cover">
							<img src="${article.cover }" alt="${article.articleTitle }">
						</div>
						<div class="item__info">
							<h3 class="item__title">${article.articleTitle }</h3>
							<span class="item__text"><fmt:formatDate value="${article.publishTime}" pattern="yyyy年M月dd日" /></span>
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="sidebar__block">
		<h3 class="block__title">最热文章</h3>
		<ul class="block-list latest-post-list">
			<c:forEach items="${hotArticle}" var="article">
				<li class="latest-post-item">
					<a href="article?id=${article.articleId }" title="${article.articleTitle }">
						<div class="item__cover">
							<img src="${article.cover }" alt="${article.articleTitle }">
						</div>
						<div class="item__info">
							<h3 class="item__title">${article.articleTitle }</h3>
							<span class="item__text">阅读量：${article.click}</span>
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	</aside></main><footer class="page__footer"><section class="footer__top">
	<div class="page__container footer__container">
		<div class="footer-top__item footer-top__item--2">
		
		</div>
		<div class="footer-top__item footer__image">
			<!-- <img src="static/picture/qrcode.png" alt="logo" title="MINFIVE"> -->
			<p>Copyright © 2018</p>
		</div> 
		<div class="footer-top__item">
		
		</div>
		<div class="footer-top__item">
			
		</div>
	</div>
	</section><section class="footer__bottom">
	<div class="page__container footer__container">
		
	</div>
	</section></footer>
	<div id="back-top" class="back-top back-top--hidden js-hidden">
		<img alt="顶部" id="upImg" src="static/images/up.png">
	</div>
</div>
<script src="static/js/common.js"></script>
</body>
</html>
