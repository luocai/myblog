package com.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.pojo.Article;
import com.blog.pojo.Category;
import com.blog.pojo.CategoryCustom;
import com.blog.pojo.Comment;
import com.blog.pojo.CommentCustom;
import com.blog.service.CommentService;
import com.blog.service.impl.ArticleServiceImpl;
import com.blog.service.impl.CategoryServiceImpl;
import com.blog.service.impl.CommentServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ArticleServiceImpl articleService;
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Autowired
	private CommentServiceImpl commentService;
	
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public String main(){
		return "admin/main";
	}
	
	
	@RequestMapping("/articleList")
	public String articleList(Model model,@RequestParam(required=true, defaultValue="1")Integer page,@RequestParam(required=false,defaultValue="3")Integer pageSize){
		
		PageHelper.startPage(page, pageSize);
		
		List<Article> articles = articleService.selectPublished();
		
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles,5);
//System.out.println(pageInfo);
		model.addAttribute("articles", articles);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("flag", 1);
		
		
		return "admin/article/list";
	}
	
	@RequestMapping(value="/articlePublishing")
	public String articlePublising(Model model,@RequestParam(required=true,defaultValue="1")Integer page, @RequestParam(required=false,defaultValue="3")Integer pageSize){
		
		PageHelper.startPage(page, pageSize);
		List<Article> articles = articleService.selectPublishing();
		
		PageInfo<Article> pageInfo = new PageInfo<>(articles,5);
		
		model.addAttribute("articles", articles);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("flag", 0);
		
		return "/admin/article/list";
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
		article.setState(Integer.parseInt(request.getParameter("state")));
		
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
	
	@RequestMapping(value="/deleteSome", method=RequestMethod.POST)
	public void deleteSome(String checkedList){
		
System.out.println(checkedList);
		String[] items = checkedList.split(",");
		
		HashMap<String, String> res = new HashMap<String, String>();
/*		res.put("stateCode", "1");*/
		for (int i = 0; i < items.length; i++){
			int checked = Integer.parseInt(items[i]);
			Article article = articleService.selectById(checked);
/*			if (article == null){
				res.put("stateCode", "0");
			}*/
			articleService.deleteArticle(article);
			
		}

	}
	
	@RequestMapping(value="/selectArticle", method=RequestMethod.POST)
	public String selectArticleByTitle(@RequestParam("words") String words, Model model,@RequestParam(name="page",defaultValue="1")Integer page){
System.out.println(words);		
		PageHelper.startPage(page, 5);
		List<Article> articleList = articleService.selectByTitle(words);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articleList);
System.out.println(articleList);
		model.addAttribute("articles", articleList);
		model.addAttribute("pageInfo", pageInfo);
		
		return "/admin/article/list";
	}
	
/*	@RequestMapping(value="/articleDetail", method=RequestMethod.GET)
	public String articleDetail(Model model, Integer id){
		
		Article article = articleService.selectById(id);
		
		model.addAttribute("article", article);
		
		return "/admin/article/articleDetail";
	}*/
	
	
	@RequestMapping(value="/categoryList")
	public String categoryList(Model model,@RequestParam(required=true, defaultValue="1")Integer page,@RequestParam(required=false,defaultValue="3")Integer pageSize){
		
		PageHelper.startPage(page, pageSize);
		List<Category> list = categoryService.selectAll();
		
		List<CategoryCustom> categoryList = new ArrayList<>();;
		
		for (int i = 0; i < list.size(); i++){
			
			int size = articleService.seletcByCategory(list.get(i).getCategoryId()).size();
			
			CategoryCustom categoryCustom = new CategoryCustom();
			BeanUtils.copyProperties(list.get(i), categoryCustom);
			categoryCustom.setArticleCount(size);
			
			categoryList.add(categoryCustom);
		}
		
		PageInfo<Category> pageInfo = new PageInfo<>(list,5);

System.out.println(pageInfo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("pageInfo", pageInfo);
		
		return "admin/category/categoryList";
		
	}
	
	@RequestMapping(value="/deleteCategory")
	public void deleteCategory(Integer id){
		
		List<Article> articleList = articleService.seletcByCategory(id);
		for (int i = 0; i < articleList.size(); i++){
			articleService.deleteArticle(articleService.selectById(articleList.get(i).getArticleId()));
		}
		categoryService.deleteCategory(categoryService.selectById(id));
	}
	
	@RequestMapping(value="/addCategory", method=RequestMethod.POST)
	public @ResponseBody Object addCategory(HttpServletRequest request){
		
		String categoryName = request.getParameter("categoryName");
System.out.println(categoryName);		
		Category category = new Category();
		category.setCategoryName(categoryName);
		
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		if(categoryService.insertCategory(category)){
			res.put("stateCode", 1);
		}else{
			res.put("stateCode", 0);
		}
		
		return res;
		
	}
	
	
	@RequestMapping(value="/selectByCategory")
	public String selectByCategory(Integer categoryId,Model model,@RequestParam(name="page",defaultValue="1")Integer page){
System.out.println(categoryId);		
		PageHelper.startPage(page, 5);
		List<Article> articleList = articleService.seletcByCategory(categoryId);
		
		PageInfo<Article> pageInfo = new PageInfo<Article>(articleList, 5);
		
		model.addAttribute("articles", articleList);
		model.addAttribute("pageInfo", pageInfo);
		
		return "/admin/category/categoryResult";
	}
	
  /*  <img src="${APP_PATH }/static/images/head.jpg" alt="" width="64px">
    <strong>${commentAuthor}</strong>

    <br>
        ${Url} <br>
        ${email} <br>
        ${commentIp}*/
	
	@RequestMapping(value="/commentList")
	public String commentList(Model model,@RequestParam(required=true,defaultValue="1")Integer page){
		
		PageHelper.startPage(page, 5);
		List<Comment> commentList = commentService.selectAll();
System.out.println("commentList:" + commentList);
		List<CommentCustom> commentCustomList = new ArrayList<>();
		PageInfo<Comment> pageInfo = new PageInfo<>(commentList,5);
		
		for(int i = 0; i < commentList.size(); i++){
			
			Comment comment = commentList.get(i);
System.out.println(comment);
			CommentCustom commentCustom = new CommentCustom();
			Article article = articleService.selectById(comment.getArticleId());
			
			BeanUtils.copyProperties(comment, commentCustom);
			commentCustom.setArticleTitle(article.getArticleTitle());
			
			commentCustomList.add(commentCustom);
		}
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("commentCustomList", commentCustomList);
		
		return "/admin/comment/commentList";
	}
	
	@RequestMapping(value="/commentReply")
	public String commentReply(Model model, Integer id){
		
		Comment comment = commentService.selectCommentById(id);
		
		model.addAttribute("comment", comment);
		
		return "/admin/comment/reply";
	}
	
//	@RequestMapping(value="/addReply", method=RequestMethod.POST)
//	public @ResponseBody Object addReply(HttpServletRequest request){
//		
//		Integer id = Integer.parseInt(request.getParameter("commentId"));
//		String reply = request.getParameter("reply");
//		
//		
//		HashMap<String, Integer> res = new HashMap<String, Integer>();
//		
//		Comment comment = commentService.selectCommentById(id);
//		comment.setReplyState(1);
//System.out.println(reply);
//		comment.setReply(reply);
//System.out.println(comment);
//		if(commentService.updateComment(comment)){
//			res.put("stateCode", 1);
//		}else{
//			res.put("stateCode", 0);
//		}
//		
//		return res;
//		
//	}
	
	@RequestMapping(value="/addReply", method=RequestMethod.POST)
	public String addReply(HttpServletRequest request){
		
		Integer id = Integer.parseInt(request.getParameter("commentId"));
		String reply = request.getParameter("reply");
		

		
		Comment comment = commentService.selectCommentById(id);
		comment.setReplyState(1);

		comment.setReply(reply);
System.out.println(comment);
		commentService.updateComment(comment);
		
		return "redirect:commentList";

	}
	
	
	@RequestMapping(value="/deleteComment")
	public @ResponseBody Object deleteComment(Integer commentId){

System.out.println(commentId);
		HashMap<String, Integer> res = new HashMap<String,Integer>();
		Comment comment = commentService.selectCommentById(commentId);
		if (commentService.deleteComment(comment)){
			res.put("stateCode", 1);
		}else{
			res.put("stateCode", 0);
		}
		return res;
	}
	
	@RequestMapping(value="layout")
	public String layout(){
		
		return "redirect:login";
	}
	
}
