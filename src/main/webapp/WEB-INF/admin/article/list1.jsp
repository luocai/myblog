<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="title">
    - 文章列表
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-input {
            display: inline-block;
            width: 33.333% !important;
        }

        .layui-input-block {
            margin: 0px 10px;
        }


    </style>
</rapid:override>

<rapid:override name="content">
    <body>
 		<div class="layui-container" style="margin-top: 50px">  
    <table class="layui-table">  
        <thead>  
            <tr>  
                <th>id</th>  
                <th>标题</th>  
                <th>发布时间</th>  
                <th>点击量</th>  

            </tr>  
        </thead>  
        <!--分页数据盛放器-->  
        <tbody></tbody>  
        <!---------------->  
    </table>  
    <!--分页容器-->  
    <div id="page" style="text-align:right"></div>  

</div>  
	</body>
</rapid:override>
<rapid:override name="footer-script">
<script>
    
 	let curr = 1;// 当前页，初始值设为 1  
    let limit = 10;// 每页条数，初始值设为 10  
    let total;// 总记录数  
  
    $(document).ready(function () {  
        getInfo();// 获取数据  
        toPage();// 进行分页  
    });  
  
    // 获取数据  
    function getInfo() {  
    	alert("进入了");
        $.ajax({  
            type: "post",  
            url: "articlePages",  
            async: false,// 设置同步请求，加载数据前锁定浏览器  
            dataType: 'json',  
            data: {  
                "start": curr, // 查询页码  
                "limit": limit, // 每页条数  
            },  
            success: function(data){
            	curr = data.start;
            	limit = data.limit;
            	total = data.pageInfo.total;
            	
            	if (total == 0) {  
		            $("tbody").html("<tr><td colspan='7' class='text-center'>暂无数据</td></tr>");  
		            return;  
		        }  
		        
				let text = "";   
		        $.each(${articles}, (i, item) => {  
		        
		         	
		              var a = item.articleTitle;
		             alert(a);
		             alert("获取了"); 
		        });  
		        $("tbody").html(text); 
		     }
        });  
    }  
  
    // 进行分页  
    function toPage() {  
        layui.use('laypage', function () {  
            let laypage = layui.laypage;  
            // 调用分页  
            laypage.render({  
                // 分页容器的id  
                elem: 'page',// *必选参数  
                // 数据总数，从服务端得到  
                count: total,// *必选参数  
                // 每页显示的条数。laypage将会借助 count 和 limit 计算出分页数。  
                //limit:limit,  
                // 起始页  
                //curr:Pager,  
                // 连续出现的页码个数  
                //groups:5,  
                // 自定义每页条数的选择项  
                limits: [10, 25, 50, 100],  
                // 自定义首页、尾页、上一页、下一页文本  
                first: '首页',  
                last: '尾页',  
                prev: '<em><<</em>',  
                next: '<em>>></em>',  
                // 自定义主题  
                theme: "#FF5722",  
                // 自定义排版  
                layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],  
                // 渲染数据  
                jump: function (data, first) {  
                    // data包含了当前分页的所有参数  
                    curr = data.curr;  
                    limit = data.limit;  
  
                    // 首次不执行  
                    if (!first) {  
                        getInfo();  
                    }  
                }  
            });  
        })  
    }  
    </script>
</rapid:override>
<%@ include file="../base/framework.jsp" %>
