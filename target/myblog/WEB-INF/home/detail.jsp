<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Random"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath()); /*根路径  */
%>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <title>文章</title>
    <link rel="canonical" href="index">
	<link rel="icon" type="image/png" href="http://oo12ugek5.bkt.clouddn.com/blog/images/favicon.ico" sizes="32x32">
	<link rel="stylesheet" href="static/css/index.css">
	<link rel="alternate" href="/atom.xml" title="MINFIVE">
	<script>var _hmt=_hmt||[];!function(){var e=document.createElement("script");e.src="https://hm.baidu.com/hm.js?e3267498201dfa9699a5c509424709d6";var t=document.getElementsByTagName("script")[0];t.parentNode.insertBefore(e,t)}()</script>
	<link rel="stylesheet" href="static/css/index_1.css">
	
    <link rel="shortcut icon" type="image/x-icon" href="${APP_PATH }/static/img/web-icon.png" media="screen" />
     <link rel="stylesheet" href="${APP_PATH }/static/css/bootstrap.min.css">
    <script src="${APP_PATH }/static/js/jquery-3.2.1.min.js"></script>
    <script src="${APP_PATH }/static/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${APP_PATH }/static/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH }/static/css/detail.css">
</head> <!-- background="${APP_PATH }/static/images/bg.png" -->
<body>

<div>
    <header id="page-header" class="page__header" style="background-image:url(static/images/banner.jpeg)"><nav class="page__navbar">
		<div class="page__container navbar-container">
			<a class="page__logo" href="index" title="MINFIVE" alt="MINFIVE"><img src="static/picture/logo-text-white.png" alt="MINFIVE"></a><nav class="page__nav">
			<ul class="nav__list clearfix">
				<li class="nav__item"><a href="index" alt="首页" title="首页">首页</a></li>
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
		</section>
	</header>
	
    
    <div id="container">
		<article class="article">
            <time id="time1"><fmt:formatDate value="${article.publishTime}" pattern="yyyy年M月dd日" /></time>
            <h2 style="text-align: center; ">${article.articleTitle}</h2>
            <p style="position: center" class="text-info"><span class="glyphicon glyphicon-eye-open"></span>  ${article.click}</p>
            <section>
                <p id="zhengwen">
                    ${article.articleContent}
                </p>
   
                <p style="margin: 5em 0 1em;text-align: center;color: #83b8ec;font-size: .8em">
                    <span>Have a nice day</span>
                </p>
            </section>
            <div style="position: relative;" align="center" style="margin:5px auto 6px auto ;">
	    	 <div class="row">
	    		<c:if test="${!empty lastArticle }">
	              <a href="article?id=${lastArticle.articleId}"><span class="label label-primary" >上一篇:${lastArticle.articleTitle}</span></a>
	        	</c:if>
		        <c:if test="${!empty nextArticle }">
		          <a href="article?id=${nextArticle.articleId}"><span class="label label-success" >下一篇:${nextArticle.articleTitle}</span></a>
		        </c:if>
	    	</div> 
   			 </div>
   			 <br>
   			 </div>
        </article>
    </div>
    
    
    
	   <div id="commentarea">
			 <div class="form-horizontal" role="form" style="margin:10px">
    			<div class="form-group">
             			 <textarea id="content" onfocus="of()"  class="form-control" rows="2" placeholder="想对作者说些什么" ></textarea>
                </div>
        		<input id="articleId" type="hidden" value="${article.articleId}" >
				<div class="form-group">
               		<div >
               			<input type="text" id="name" class="form-control"
               				   placeholder="给自己起个昵称哦">
               			<span><button id="commentButton" class="btn btn-default" style="display:none;background-color:#0099CC;" type="submit">发表</button> </span>
               		</div>
               		
               		<p style="text-align: right;color: red;position: absolute" id="info"></p>
               	</div>
      		</div>
		</div>    
    
 			
   <%--  <% int i =1;    %> --%>
   <div class="comments">
     <c:forEach items="${comments}" var="comment">
           <div id="comment">
           		<a href="##">
           			<%
           				Random r = new Random();
           			    int i = r.nextInt(13);
           			 %>
					<img src="${APP_PATH }/static/head/<%=i %>.jpg" id="photo" alt="" width="50" height="50">
				</a>
				<div id="text">
					<a href="##" id="nameherf">${comment.userName}</a>
					<p class="pcontent">
						${comment.commentContent }
					</p>
					<p id="date">
						<span class="date"><fmt:formatDate value="${comment.commentTime }" pattern="yyyy年MM月dd日  HH:mm"/></span>
					</p>
		  	    </div>
		  	    <c:if test="${comment.replyState==1 }">
					<a href="##">
						<img src="${APP_PATH }/static/images/head.jpg" id="photo" alt="" width="50" height="50">
					</a>
					<div id="text"> 
						<a href="##" id="nameherf">博主 <span style="font-size: 13px;">回复： </span>${comment.userName }</a>
						<p class="pcontent">
							${comment.reply }
						</p>
						<p id="date">
							<span class="date"><fmt:formatDate value="${comment.replyTime}" pattern="yyyy年MM月dd日  HH:mm"/></span>
						</p>
		  	    	</div>
				</c:if>
           </div>
    </c:forEach>
   </div>
   
    <div class="row">
    
    </div>
    
	<footer class="page__footer"><section class="footer__top">
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
	
<script src="static/js/common.js"></script>
 <script>

      $("#commentButton").click(function () {
          if($("#content").val()==''&&$("#name").val()==''){
              $("#info").text("提示:请输入评论内容和你的昵称");
          }
          else if ($("#content").val()==''){
              $("#info").text("提示:请输入评论内容");
          }
          else if($("#name").val()==''){
              $("#info").text("提示:请输入昵称");
          }
          else {
           $("#info").text("");
              $.ajax({
                  type: "POST",
                  url: "comment/addComment",
                  data: {
                      commentContent: $("#content").val() ,
                      userName: $("#name").val(),
                      articleId:$("#articleId").val(),
                  },
                  dataType: "json",
                  success: function(data) {
                      if(data.stateCode.trim() == "1") {
                          $("#info").text("评论成功,跳转中...");
                          window.location.reload();
                      } else  {
                          $("#info").text("评论失败...");
                      }
                  }
              });
          }
      })
	
	function of(){
		$("#content").attr("rows","3");
			
			$("#name").show();
			$("#commentButton").show();
	}
  </script>
</body>
</html>