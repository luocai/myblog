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
		
		Article lastArticle = articleService.selectLastArticle(id);
		Article nextArticle = articleService.selectNextArticle(id);
System.out.println(lastArticle);	
System.out.println(nextArticle);
		model.addAttribute("article", article);
		model.addAttribute("comments", comments);
		model.addAttribute("lastArticle", lastArticle);
		model.addAttribute("nextArticle", nextArticle);
		
		
		
		return "home/detail";
	}
	
}
