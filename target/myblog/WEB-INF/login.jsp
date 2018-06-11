<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath()); /*根路径  */
%>
<head>
	<title>博客登录系统</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta charset="utf-8">
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
	<link rel="canonical" href="http://blog.minfive.com/index.html">
	<link rel="icon" type="image/png" href="http://oo12ugek5.bkt.clouddn.com/blog/images/favicon.ico" sizes="32x32">
	<!-- 引入jquery -->
	<script src="${APP_PATH }/static/js/jquery-3.2.1.min.js"></script>
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="${APP_PATH }/static/css/bootstrap.min.css">
	<!-- 引入 bootstrap.js-->
	<script src="${APP_PATH }/static/js/bootstrap.min.js"></script>
	<style type="text/css">
	body{
	   background: url(static/images/banner.jpeg)repeat;
	}
	#login-box {
		/*border:1px solid #F00;*/
		padding: 35px;
		border-radius:15px;
		background: RGB(177,179,191);
		color: #fff;
	}

	</style>
</head>
<body>
	<div class="container" id="top">
		<div class="row" style="margin-top: 280px; ">
			<div class="col-md-4"></div>
			<div class="col-md-4" id="login-box">
				<form class="form-horizontal" role="form" action="##" id="from1" method="post">
				  <div class="form-group">
				    <label for="firstname" class="col-sm-3 control-label">用户昵称</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="username" placeholder="请输入名字" name="username">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-3 control-label">密码</label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password">
				    </div>
				  </div>
				  <div class="form-group pull-right" style="margin-right: 15px;">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button  id="loginButton" type="button" class="btn btn-default btn-info">登录</button>
				    </div>
				  </div>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>		
	</div>
</body>
<script type="text/javascript">
 
        $("#loginButton").click(function () {
            if($("#username").val()==''&&$("#password").val()==''){
                alert("提示:账号和密码不能为空");
                return false;
            }
            else if ($("#username").val()==''){
                alert("提示:账号不能为空");
                return false;
            }
            else if($("#password").val()==''){
                alert("提示:密码不能为空");
                return false;
            }
            else {
                $.ajax({
                    type: "POST",
                    url: "loginCheck",
                    data: {
                        username:$("#username").val() ,
                        password: $("#password").val()
                    },
                    dataType: "json",
                    success: function(data) {
                        if(data.stateCode.trim() == "0") {
                            alert("该用户不存在");
                            window.location.reload();
                        } else if(data.stateCode.trim() == "1") {
                           alert("密码错误");
                           window.location.reload();
                        } else if(data.stateCode.trim() == "2"){
                            alert("登陆成功");
                            location.href="admin/articleList";
                        }
                    }
                });
            }
        })

    </script>
</html>