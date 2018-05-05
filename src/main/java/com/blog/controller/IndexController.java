package com.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.pojo.Article;
import com.blog.pojo.ArticleCustom;
import com.blog.pojo.Category;
import com.blog.service.impl.ArticleServiceImpl;
import com.blog.service.impl.CategoryServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class IndexController {
	
	@Autowired
	private ArticleServiceImpl articleService;
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@RequestMapping(value = "/index")
	public String index(Model model,@RequestParam(required=true, defaultValue="1")Integer page,@RequestParam(required=false,defaultValue="3")Integer pageSize){
		
		PageHelper.startPage(page, pageSize);
		List<Article> articleList = articleService.selectAll();
		List<ArticleCustom> articles = new ArrayList<>();
		PageInfo<Article> pageInfo = new PageInfo<Article>(articleList, 5);
		
	System.out.println(pageInfo);
	System.out.println(articleList.size());
	
		for(int i = 0; i < articleList.size(); i++){
System.out.println(articleList.get(i).getCategory());
			Category category = categoryService.selectById(articleList.get(i).getCategory());
System.out.println(category);
			ArticleCustom articleCustom = new ArticleCustom();
			BeanUtils.copyProperties(articleList.get(i), articleCustom);
			articleCustom.setCategoryName(category.getCategoryName());
			
			articles.add(articleCustom);
		}
		model.addAttribute("articles", articles);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("keywords", "java学习");
		
		return "home/index";
	}
}
