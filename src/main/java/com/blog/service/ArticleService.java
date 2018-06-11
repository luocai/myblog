package com.blog.service;

import java.util.List;

import com.blog.pojo.Article;
import com.blog.pojo.ArticleExample;
import com.blog.pojo.ArticleExample.Criteria;

public interface ArticleService {

	boolean insertArticle(Article article);
	boolean deleteArticle(Article article);
	
	boolean updateArticle(Article article);
	
	//查找全部
	List<Article> selectAll();
	
//	List<Article> selectByPage();
	/*List<Article> selectByKeyWords(String keywords);*/
	Article selectById(Integer id);
	
	List<Article> selectByTitle(String title);
	
	List<Article> selectPublished();
	List<Article> selectPublishing();
	
	List<Article> selectByCategory(Integer categoryId);
	
	//查找上一篇和下一篇
	Article selectLastArticle(Integer id);
	Article selectNextArticle(Integer id);
	
	//查找点击率最高的文章
	public List<Article> selectHotArticle();


	public List<Article> selectByTitlePublished(String search);

}
