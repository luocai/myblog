package com.blog.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.blog.pojo.CategoryCustom;
import com.blog.service.impl.ArticleServiceImpl;
import com.blog.service.impl.CategoryServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import util.DateUtil;

@Controller
public class IndexController {
	
	@Autowired
	private ArticleServiceImpl articleService;
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@RequestMapping(value = {"/index","/"})
	public String index(Model model,@RequestParam(required=true, defaultValue="1")Integer page,@RequestParam(required=false,defaultValue="12")Integer pageSize){
		
		PageHelper.startPage(page, pageSize);
		List<Article> articleList = articleService.selectAll();
		List<ArticleCustom> articles = new ArrayList<>();
		PageInfo<Article> pageInfo = new PageInfo<Article>(articleList, 5);
		
	System.out.println(pageInfo);
	System.out.println(articleList.size());

		for(int i = 0; i < articleList.size(); i++){
System.out.println(articleList.get(i).getSummary());
			Category category = categoryService.selectById(articleList.get(i).getCategory());
System.out.println(category);
			ArticleCustom articleCustom = new ArticleCustom();
			BeanUtils.copyProperties(articleList.get(i), articleCustom);
			
			Date date = articleList.get(i).getPublishTime();
			
			articleCustom.setCategoryName(category.getCategoryName());
			articleCustom.setPublishMonth(DateUtil.getMonth(date));
			articleCustom.setPublishDay(DateUtil.getDay(date));
			
System.out.println("summary:"  + articleList.get(i).getSummary());
			articles.add(articleCustom);
		}
		
		/*List<Category> init = categoryService.selectAll();
		for(int i = 0; i < init.size(); i++){
			int size = articleService.seletcByCategory(init.get(i).getCategoryId()).size();
			init.get(i).setArticlecount(size);
			categoryService.updateCategory(init.get(i));
			
		}*/
		
		List<Article> hotArticle = articleService.selectHotArticle();
		
System.out.println("最热的文章是："+hotArticle);
		List<Category> categoryList = categoryService.selectAll();
		List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
		
		for(Category category: categoryList){
			int size = articleService.selectByCategory(category.getCategoryId()).size();
			
			CategoryCustom categoryCustom = new CategoryCustom();
			BeanUtils.copyProperties(category, categoryCustom);
			categoryCustom.setArticleCountPublished(size);
			categoryCustomList.add(categoryCustom);
		}
		
		model.addAttribute("categoryList", categoryCustomList);
		model.addAttribute("articles", articles);
		model.addAttribute("hotArticle", hotArticle);
		model.addAttribute("pageInfo", pageInfo);
		
		//return "home/index";
		return "/home/index";
	}
	
	@RequestMapping(value="/archives")
	public String archives(Model model, Integer year){
		
System.out.println("year的值是" + year);		
		List<ArticleCustom> archiveArticle = new ArrayList<ArticleCustom>(); //结果集
		List<Integer> years = new ArrayList<Integer>(); //年份集合
		List<Article> articleList = articleService.selectAll();
		List<ArticleCustom> articles = new ArrayList<>();
		
	
		for(int i = 0; i < articleList.size(); i++){
			Category category = categoryService.selectById(articleList.get(i).getCategory());
			ArticleCustom articleCustom = new ArticleCustom();
			BeanUtils.copyProperties(articleList.get(i), articleCustom);
			
			Date date = articleList.get(i).getPublishTime();
			
			articleCustom.setCategoryName(category.getCategoryName());
			articleCustom.setPublishMonth(DateUtil.getMonth(date));
			articleCustom.setPublishDay(DateUtil.getDay(date));
			
			articles.add(articleCustom);
		}
		
		//把年份加入
		for(ArticleCustom article: articles){
			Integer y = DateUtil.getYear(article.getPublishTime());
			if(!years.contains(y)){
				years.add(y);
			}
			if(y.equals(year) ){
				archiveArticle.add(article);
			}
		}
		
		if(year == null){
			year = years.get(0);
			System.out.println("当前年份是："+ year);
			for(ArticleCustom article: articles){
				Integer y = DateUtil.getYear(article.getPublishTime());
				System.out.println("这个y是："+ y);
				if(y.equals(year)){
					archiveArticle.add(article);
					System.out.println("啦啦");
				}
			}
		}
		
		for(ArticleCustom a: archiveArticle){
			System.out.println("归档哦"+a);
		}
		
		
		List<Article> hotArticle = articleService.selectHotArticle();

		List<Category> categoryList = categoryService.selectAll();

		List<CategoryCustom> categoryCustomList = new ArrayList<CategoryCustom>();
		
		for(Category category: categoryList){
			int size = articleService.selectByCategory(category.getCategoryId()).size();
			
			CategoryCustom categoryCustom = new CategoryCustom();
			BeanUtils.copyProperties(category, categoryCustom);
			categoryCustom.setArticleCountPublished(size);
			categoryCustomList.add(categoryCustom);
		}
		
		
		model.addAttribute("archiveArticle", archiveArticle);
		model.addAttribute("years", years);
		model.addAttribute("categoryList", categoryCustomList);
		model.addAttribute("hotArticle", hotArticle);
		model.addAttribute("articleList", articleList);
	/*	if(year == null){
			year = years.get(0);//如果没有初始值，则导航年为下标为0的年份
		}*/
		model.addAttribute("currentYear",year);
		
		return "/home/archives";
	}
	
	@RequestMapping(value="/about")
	public String about(){
		
		return "home/about";
	}
	
	@RequestMapping(value="/friends")
	public String friends(){
		
		return "home/friendLink";
	}
}
