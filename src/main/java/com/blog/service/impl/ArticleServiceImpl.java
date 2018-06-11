package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mapper.ArticleMapper;
import com.blog.pojo.Article;
import com.blog.pojo.ArticleExample;
import com.blog.pojo.ArticleExample.Criteria;
import com.blog.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper articleMapper;
	
	@Override
	public boolean insertArticle(Article article) {
		//返回值大于零则插入成功
		return articleMapper.insertSelective(article) > 0;
		
	}

	@Override
	public boolean deleteArticle(Article article) {
		ArticleExample articleExample = new ArticleExample();
		Criteria criteria = articleExample.createCriteria();
		criteria.andArticleIdEqualTo(article.getArticleId());
		// 疑问？？
		return articleMapper.deleteByExample(articleExample) > 0;
	}

	@Override
	public boolean updateArticle(Article article) {
	
		
		return articleMapper.updateByPrimaryKeySelective(article) > 0;
	}

	@Override
	public List<Article> selectAll() {
//		ArticleExample articleExample = new ArticleExample();
//		Criteria criteria = articleExample.createCriteria();
//		criteria.andArticleIdIsNotNull();
		
		return articleMapper.selectAll();
	}

//	@Override
//	public List<Article> selectByPage() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<Article> selectByKeyWords(String keywords) {
//		ArticleExample articleExample = new ArticleExample();
//		Criteria  criteria = articleExample.createCriteria();
//		
//		return null;
//	}

	@Override
	public Article selectById(Integer id) {
		
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Article> selectByTitle(String title) {
		
		ArticleExample articleExample = new ArticleExample();
		Criteria criteria = articleExample.createCriteria();
		criteria.andArticleTitleLike("%"+title+"%");
		
		return articleMapper.selectByExample(articleExample);
	}

	@Override
	public List<Article> selectPublished() {
		ArticleExample articleExample = new ArticleExample();
		Criteria criteria = articleExample.createCriteria();
		//查找发布了的
		criteria.andStateEqualTo(1); 
		
		return articleMapper.selectByExample(articleExample);
	}

	@Override
	public List<Article> selectPublishing() {
		ArticleExample articleExample = new ArticleExample();
		Criteria criteria = articleExample.createCriteria();
		//查找发布了的
		criteria.andStateEqualTo(0); 
		
		return articleMapper.selectByExample(articleExample);
	}

	@Override
	public List<Article> selectByCategory(Integer categoryId) {
		ArticleExample articleExample = new ArticleExample();
		Criteria criteria = articleExample.createCriteria();
		criteria.andCategoryEqualTo(categoryId);
		criteria.andStateEqualTo(1);
		
		return articleMapper.selectByExample(articleExample);
	}

	@Override
	public Article selectLastArticle(Integer id) {
		
		return articleMapper.selectLastArticle(id);
	}

	@Override
	public Article selectNextArticle(Integer id) {
		
		return articleMapper.selectNextArticle(id);
	}
	@Override
	public List<Article> selectHotArticle() {
		
		return articleMapper.selectHotArticle();
	}
	@Override
	public List<Article> selectByTitlePublished(String search) {
		
		ArticleExample articleExample = new ArticleExample();
		Criteria criteria = articleExample.createCriteria();
		criteria.andArticleTitleLike("%"+search+"%");
		criteria.andStateEqualTo(1);
		
		return articleMapper.selectByExample(articleExample);
		
	}

}
