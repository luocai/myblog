package com.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blog.pojo.Article;
import com.blog.pojo.Category;
import com.blog.pojo.Comment;
import com.blog.pojo.CommentCustom;
import com.blog.pojo.User;
import com.blog.service.impl.ArticleServiceImpl;
import com.blog.service.impl.CategoryServiceImpl;
import com.blog.service.impl.CommentServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import util.UploadUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ArticleServiceImpl articleService;
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Autowired
	private CommentServiceImpl commentService;
	
	/*@RequestMapping(value = "/main", method=RequestMethod.GET)
	public String main(){
		return "admin/main";
	}*/
	
	
	@RequestMapping("/articleList")
	public String articleList(Model model,@RequestParam(required=true, defaultValue="1")Integer page,@RequestParam(required=false,defaultValue="4")Integer pageSize){
		
		PageHelper.startPage(page, pageSize);
		
		List<Article> articles = articleService.selectAll();
		
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles,5);
//System.out.println(pageInfo);
		model.addAttribute("articles", articles);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("flag", 1);
		
		
		return "admin/article/list";
	}
	
	@RequestMapping(value="/articlePublishing")
	public String articlePublising(Model model,@RequestParam(required=true,defaultValue="1")Integer page, @RequestParam(required=false,defaultValue="4")Integer pageSize){
		
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
	
	
	@RequestMapping(value = "/write")
	public String writeArticle(Model model){
		List<Category> categoryList = categoryService.selectAll();
		
		model.addAttribute("categoryList", categoryList);
		return "admin/article/write";
	}
	
	@RequestMapping(value = "addArticle")
	@ResponseBody   //, @RequestParam(value = "articleCover", required = false)MultipartFile coverPic
	public Object addArticle(HttpServletRequest request, @RequestParam(value="articleCover")MultipartFile coverPic) throws Exception{
		
		System.out.println("进来啦");
		String coverPath = null;
        if (coverPic.getSize() != 0) {
            coverPath = UploadUtil.uploadImgToQiuniu(coverPic);
            System.out.println(coverPath);
        }
        
		System.out.println("进来啦");
		Article article = new Article();
		article.setArticleTitle(request.getParameter("articleTitle"));
		article.setArticleContent(request.getParameter("articleContent"));
		article.setCategory(Integer.parseInt(request.getParameter("articleCategory")));
		article.setClick(0);
		article.setSummary(request.getParameter("summary"));
		article.setPublishTime(new Date());
		
		User user = (User)request.getSession().getAttribute("user");
		article.setWriterId(user.getUserId());
		article.setState(Integer.parseInt(request.getParameter("state")));
		article.setCover(coverPath);
		
		System.out.println(article.getArticleContent());
		
		//对应的该类的数目也要加一
		Category category = categoryService.selectById(article.getCategory());
		
		
		articleService.insertArticle(article);
		category.setArticlecount(category.getArticlecount() + 1);
		
		//更新,其实这里应该用事物的...
		categoryService.updateCategory(category);
		
		Map<String,Object> map = new HashMap<>();
		map.put("state", 1);
		return map;
	}
	
	@RequestMapping("deleteArticle")
	public @ResponseBody Object delete(Integer articleId){
System.out.println("articleId:"+articleId);		
		Article article = articleService.selectById(articleId);
		Category category = categoryService.selectById(article.getCategory());
		
		HashMap<String, String> hash = new HashMap<String, String>();
		
		if (article != null){
			hash.put("stateCode", "1");
			articleService.deleteArticle(article);
			category.setArticlecount(category.getArticlecount() - 1);
			categoryService.updateCategory(category);
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
			model.addAttribute("article", article);
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("categoryName", categoryService.selectById(article.getCategory()).getCategoryName());
//			model.addAttribute("articleTitle", article.getArticleTitle());
//			model.addAttribute("articleId", article.getArticleId());
//			model.addAttribute("publishTime",article.getPublishTime() );
//			model.addAttribute("click", article.getClick());
//			model.addAttribute("categoryId", article.getCategory());			
//			model.addAttribute("articleContent", article.getArticleContent());
//			model.addAttribute("categoryList", categoryList);
		}
		
		return "/admin/article/edit";
	}
	
	
	@RequestMapping(value="/editArticle")
	@ResponseBody
	public Object editArticle(HttpServletRequest request,@RequestParam(value="articleCover",required=false)MultipartFile coverPic) throws Exception{
		
		Article article = new Article(); 
System.out.println("老子进来修改啦");
		String coverPath = null;
        if (coverPic != null) {
            coverPath = UploadUtil.uploadImgToQiuniu(coverPic);
            System.out.println(coverPath);
            article.setCover(coverPath);
        }
        
		article.setArticleId(Integer.parseInt(request.getParameter("articleId")));
		article.setArticleTitle(request.getParameter("articleTitle"));
		article.setArticleContent(request.getParameter("articleContent"));
		article.setCategory(Integer.parseInt(request.getParameter("articleCategory")));
		article.setState(Integer.parseInt(request.getParameter("state")));
		article.setSummary(request.getParameter("summary"));
		User user = (User)request.getSession().getAttribute("user");
		article.setWriterId(user.getUserId());
		
		
		System.out.println(article.getCategory());
		System.out.println(articleService.selectById(article.getArticleId()).getCategory());
		//修改后一增一减
		if(article.getCategory() != articleService.selectById(article.getArticleId()).getCategory()){
			Category categoryOld = categoryService.selectById(articleService.selectById(article.getArticleId()).getCategory());
			Category categoryNew = categoryService.selectById(article.getCategory());
			categoryOld.setArticlecount(categoryOld.getArticlecount() - 1);
			categoryNew.setArticlecount(categoryNew.getArticlecount() + 1);
System.out.println("odl:" + categoryOld);
System.out.println("new:" + categoryNew);
			categoryService.updateCategory(categoryOld);
			categoryService.updateCategory(categoryNew);
		}
		articleService.updateArticle(article);
		
		Map<String, Object> map = new HashMap<>();
		map.put("state", 1);
		return map;
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
			Category category = categoryService.selectById(article.getCategory());
/*			if (article == null){
				res.put("stateCode", "0");
			}*/
			articleService.deleteArticle(article);
			category.setArticlecount(category.getArticlecount() - 1);
			categoryService.updateCategory(category);
			
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
	public String categoryList(Model model,@RequestParam(required=true, defaultValue="1")Integer page,@RequestParam(required=false,defaultValue="4")Integer pageSize){
		
		PageHelper.startPage(page, pageSize);
		List<Category> categoryList = categoryService.selectAll();
		
		
		for (int i = 0; i < categoryList.size(); i++){
			
			int size = articleService.selectByCategory(categoryList.get(i).getCategoryId()).size();
			
			categoryList.get(i).setArticlecount(size);
		}
		
		PageInfo<Category> pageInfo = new PageInfo<>(categoryList,5);

System.out.println(pageInfo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("pageInfo", pageInfo);
		
		return "admin/category/categoryList";
		
	}
	
	@RequestMapping(value="/deleteCategory")
	public void deleteCategory(Integer id){
		
		List<Article> articleList = articleService.selectByCategory(id);
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
		category.setArticlecount(0);
		
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
		List<Article> articleList = articleService.selectByCategory(categoryId);
		
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
		
		PageHelper.startPage(page, 4);
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
		comment.setReplyTime(new Date());
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
	
	//上传图片到七牛云
	@RequestMapping("/uploadImg")
	@ResponseBody
   public Object updateImg(@RequestParam(value="picture")MultipartFile pictureFile) throws Exception {

		System.out.println("执行了");
       System.out.println("pictureFile = " + pictureFile);
       String path = null;
       if (pictureFile.getSize() != 0) {
           path = UploadUtil.uploadImgToQiuniu(pictureFile);
           System.out.println(path);
       }
       
      Map<String,Object> map = new HashMap<String, Object>();

      map.put("url", path);
      /*JSONObject json = new JSONObject(map);*/
      
      System.out.println(map);

       return map;
   }
	
}
