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
		ArticleExample articleExample = new ArticleExample();
		Criteria criteria = articleExample.createCriteria();
		criteria.andArticleIdEqualTo(article.getArticleId());
		
		return articleMapper.updateByExample(article, articleExample) > 0;
	}

	@Override
	public List<Article> selectAll() {
		ArticleExample articleExample = new ArticleExample();
		Criteria criteria = articleExample.createCriteria();
		criteria.andArticleIdIsNotNull();
		
		return articleMapper.selectByExample(articleExample);
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
		ArticleExample articleExample = new ArticleExample();
		Criteria criteria = articleExample.createCriteria();
		criteria.andArticleIdEqualTo(id);
		return null;
	}

}
