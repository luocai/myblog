package com.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.pojo.Article;
import com.blog.pojo.ArticleCustom;
import com.blog.pojo.Category;
import com.blog.pojo.CategoryCustom;
import com.blog.pojo.Comment;
import com.blog.service.impl.ArticleServiceImpl;
import com.blog.service.impl.CategoryServiceImpl;
import com.blog.service.impl.CommentServiceImpl;

import util.DateUtil;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleServiceImpl articleService;
	@Autowired
	private CommentServiceImpl commentService;
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@RequestMapping(value = "/article")
	public String detail(Model model,Integer id){
		Article article = articleService.selectById(id);
		article.setClick(article.getClick()+1);
		
		//更新访问量
		articleService.updateArticle(article);
		
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
	
	@RequestMapping(value="/queryByCategory")
	public String queryByCategory(Model model, Integer cid){
		
		/*List<Article> categoryArticle = articleService.seletcByCategory(cid);*/
		List<Article> articles = articleService.selectAll();
		List<Article> articleList = articleService.selectByCategory(cid);
		List<ArticleCustom> categoryArticle = new ArrayList<>();
		
	
		for(int i = 0; i < articleList.size(); i++){
			Category category = categoryService.selectById(articleList.get(i).getCategory());
			ArticleCustom articleCustom = new ArticleCustom();
			BeanUtils.copyProperties(articleList.get(i), articleCustom);
			
			Date date = articleList.get(i).getPublishTime();
			
			articleCustom.setCategoryName(category.getCategoryName());
			articleCustom.setPublishMonth(DateUtil.getMonth(date));
			articleCustom.setPublishDay(DateUtil.getDay(date));
			
			categoryArticle.add(articleCustom);
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

		model.addAttribute("categoryArticle", categoryArticle); //结果集
		model.addAttribute("categoryList", categoryCustomList); //所有分类
		model.addAttribute("hotArticle", hotArticle); //最热文章
		model.addAttribute("articleList", articles); //用于最新文章
		
		return "home/queryByCategory";
	}
	
	@RequestMapping(value="/search")
	public String search(Model model, String search){
		
		List<Article> articles = articleService.selectAll();
		List<Article> articleList = articleService.selectByTitlePublished(search);
		List<ArticleCustom> searchArticle = new ArrayList<>();
		
		
		for(int i = 0; i < articleList.size(); i++){
System.out.println(articleList.get(i).getSummary());
			Category category = categoryService.selectById(articleList.get(i).getCategory());
			ArticleCustom articleCustom = new ArticleCustom();
			BeanUtils.copyProperties(articleList.get(i), articleCustom);
			
			Date date = articleList.get(i).getPublishTime();
			
			articleCustom.setCategoryName(category.getCategoryName());
			articleCustom.setPublishMonth(DateUtil.getMonth(date));
			articleCustom.setPublishDay(DateUtil.getDay(date));
			
			searchArticle.add(articleCustom);
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

		model.addAttribute("searchArticle", searchArticle); //结果集
		model.addAttribute("categoryList", categoryCustomList); //所有分类
		model.addAttribute("hotArticle", hotArticle); //最热文章
		model.addAttribute("articleList", articles); //用于最新文章
		
		return "home/searchArticle";
		
	}
	
}
