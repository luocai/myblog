package com.blog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.pojo.Article;
import com.blog.pojo.Category;
import com.blog.service.impl.ArticleServiceImpl;
import com.blog.service.impl.CategoryServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ArticleServiceImpl articleService;
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public String main(){
		return "admin/main";
	}
	
	
	@RequestMapping("/articleList")
	public String articleList(Model model,@RequestParam(required=true, defaultValue="1")Integer page,@RequestParam(required=false,defaultValue="3")Integer pageSize){
		
		PageHelper.startPage(page, pageSize);
		
		List<Article> articles = articleService.selectAll();
		
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles,5);
//System.out.println(pageInfo);
		model.addAttribute("articles", articles);
		model.addAttribute("pageInfo", pageInfo);
		
		
		return "admin/article/list";
	}
	
/*	@RequestMapping("/articlePages")
	public @ResponseBody Object articlePages(HttpServletRequest request){
System.out.println("已经进入啦");		
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
System.out.print("start:" + start);
System.out.print("limit:"+ limit);
		PageHelper.offsetPage(start, limit);
		
		List<Article> articles = articleService.selectAll();
System.out.println(articles.size());
		HashMap<String, Object> hash = new HashMap<String, Object>();

		PageInfo<Article> pageInfo = new PageInfo<Article>(articles);
System.out.println(pageInfo.getTotal());	
		request.setAttribute("articles", articles);
//		hash.put("articles", articles);
		hash.put("pageInfo", pageInfo);
		hash.put("start", start);
		hash.put("limit", limit);
		
		return hash; 
	}*/
	
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeArticle(Model model){
		List<Category> categoryList = categoryService.selectAll();
		
		model.addAttribute("categoryList", categoryList);
		return "admin/article/write";
	}
	
	@RequestMapping(value = "addArticle",method = RequestMethod.POST)
	public String addArticle(HttpServletRequest request){
		Article article = new Article();
		article.setArticleTitle(request.getParameter("articleTitle"));
		article.setArticleContent(request.getParameter("articleContent"));
		article.setCategory(Integer.parseInt(request.getParameter("articleCategory")));
		article.setClick(10);
		article.setSummary("呵呵");
		article.setPublishTime(new Date());
		article.setWriterId(15);
		
		articleService.insertArticle(article);
		
		return "redirect:articleList";
	}
	
	@RequestMapping("deleteArticle")
	public @ResponseBody Object delete(Integer articleId){
System.out.println("articleId:"+articleId);		
		Article article = articleService.selectById(articleId);
		
		HashMap<String, String> hash = new HashMap<String, String>();
		
		if (article != null){
			hash.put("stateCode", "1");
			articleService.deleteArticle(article);
		}else{
			hash.put("stateCode", "0");
		}
		
		return hash;	
	}
	
	@RequestMapping(value = "/editArticle", method=RequestMethod.GET)
	public String edit(Model model,Integer id){
		
		Article article = articleService.selectById(id);
		List<Category> categoryList = categoryService.selectAll();
		
		if(article != null){
			model.addAttribute("articleTitle", article.getArticleTitle());
			model.addAttribute("articleId", article.getArticleId());
			model.addAttribute("publishTime",article.getPublishTime() );
			model.addAttribute("click", article.getClick());
			model.addAttribute("categoryId", article.getCategory());
			model.addAttribute("categoryName", categoryService.selectById(article.getCategory()).getCategoryName());
			model.addAttribute("articleContent", article.getArticleContent());
			model.addAttribute("categoryList", categoryList);
		}
		
		return "/admin/article/edit";
	}
	
	
	@RequestMapping(value="/editArticle", method=RequestMethod.POST)
	public String editArticle(HttpServletRequest request){
		Article article = new Article(); 
		article.setArticleId(Integer.parseInt(request.getParameter("articleId")));
		article.setArticleTitle(request.getParameter("articleTitle"));
		article.setArticleContent(request.getParameter("articleContent"));
		article.setCategory(Integer.parseInt(request.getParameter("articleCategory")));
		article.setClick(10);
		article.setSummary("呵呵");
		article.setPublishTime(new Date());
		article.setWriterId(15);
		
		articleService.updateArticle(article);
		
		return "redirect:articleList";
	}
	
	
}
