package com.blog.controller;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.pojo.Comment;
import com.blog.service.impl.CommentServiceImpl;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

	@Autowired
	private CommentServiceImpl commentService;
	
	@RequestMapping(value = "/addComment")
	public @ResponseBody Object addComment(HttpServletRequest request){
		Comment comment = new Comment();
		comment.setArticleId(Integer.parseInt(request.getParameter("articleId")));
		comment.setCommentContent(request.getParameter("commentContent"));
		comment.setCommentTime(new Date());
System.out.println(request.getParameter("userName"));
		comment.setUserName(request.getParameter("userName"));
		comment.setReplyState(0);

		
		boolean flag = commentService.insertComment(comment);
		HashMap<String, String> res = new HashMap<String, String>();
		if (flag == true){
			res.put("stateCode", "1");
		}else{
			res.put("stateCode", "2");
		}
		
		return res;
		
	}
}
