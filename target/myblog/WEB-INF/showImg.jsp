<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%
		pageContext.setAttribute("APP_PATH", request.getContextPath()); /*根路径  */
	%>
    
    <title>My JSP 'showImg.jsp' starting page</title>
    


  </head>
	  
  <body>
   
  
   <img alt="图片" style="display:none;" id="image" src="${APP_PATH }/static/img/1.jpg"/>

   <br>
   <button onclick="start()">按钮</button>
   
  </body>
 
 <script type="text/javascript">
 
 	var i = 1;
 	var int ;
 	
 	function start(){
 		int=self.setInterval("showImg()",1000);
 		document.getElementById("image").style.display='block';
 	}
 	function showImg(){
 		i++;
 		if (i == 14){
 			i = 1;
 		}
 		alert(i);
 /* 		alert(path); */
		var img = document.getElementById("image");
		img.setAttribute("src", "/myblog/static/img/"+ i+".jpg");
 		
 		/* for(var i = 1; i<= 13;i++){
 			var img = document.getElementById("image");
 			img.setAttribute("src", path+ i+".jpg");
 		} */
 		
 /* 		window.location.reload(); */
/*  		alert("出来"); */
 	}
 </script>
</html>
