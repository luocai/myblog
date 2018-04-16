package com.blog.service;

import java.util.List;

import com.blog.pojo.Article;

public interface ArticleService {

	boolean insertArticle(Article article);
	boolean deleteArticle(Article article);
	
	boolean updateArticle(Article article);
	
	List<Article> selectAll();
	
//	List<Article> selectByPage();
	/*List<Article> selectByKeyWords(String keywords);*/
	Article selectById(Integer id);
	
}
