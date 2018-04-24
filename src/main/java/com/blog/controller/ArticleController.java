package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.pojo.Article;
import com.blog.pojo.Comment;
import com.blog.service.impl.ArticleServiceImpl;
import com.blog.service.impl.CommentServiceImpl;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleServiceImpl articleService;
	@Autowired
	private CommentServiceImpl commentService;
	
	@RequestMapping(value = "/article")
	public String detail(Model model,Integer id){
		Article article = articleService.selectById(id);
		List<Comment> comments = commentService.selectByArticleId(id);
System.out.println(article);
		model.addAttribute("article", article);
		model.addAttribute("comments", comments);
		
//		model.addAttribute("click", 10);
		
		
		return "detail";
	}
	
}
