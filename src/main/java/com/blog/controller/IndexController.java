package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.pojo.Article;
import com.blog.service.impl.ArticleServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class IndexController {
	
	@Autowired
	private ArticleServiceImpl articleService;
	
	@RequestMapping(value = "/index")
	public String index(Model model,@RequestParam(required=true, defaultValue="1")Integer page,@RequestParam(required=false,defaultValue="3")Integer pageSize){
		
		PageHelper.startPage(page, pageSize);
		List<Article> articles = articleService.selectAll();
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles, 5);
		
	System.out.println(pageInfo);
	System.out.println(articles.size());
	
		model.addAttribute("articles", articles);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("keywords", "java学习");
		
		return "index";
	}
}
