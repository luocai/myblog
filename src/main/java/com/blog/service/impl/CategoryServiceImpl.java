package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mapper.CategoryMapper;
import com.blog.pojo.Category;
import com.blog.pojo.CategoryExample;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryMapper categoryMapper;
	
	@Override
	public boolean insertCategory(Category category) {
		 
		return categoryMapper.insert(category) > 0;
	}

	@Override
	public boolean updateCategory(Category category) {
		CategoryExample categoryExample = new CategoryExample();
		com.blog.pojo.CategoryExample.Criteria criteria= categoryExample.createCriteria();
		criteria.andCategoryIdEqualTo(category.getCategoryId());
		
		return categoryMapper.updateByExample(category, categoryExample) > 0;
	}

	@Override
	public boolean deleteCategory(Category category) {
		CategoryExample categoryExample = new CategoryExample();
		com.blog.pojo.CategoryExample.Criteria criteria= categoryExample.createCriteria();
		criteria.andCategoryIdEqualTo(category.getCategoryId());
		
		return categoryMapper.deleteByExample(categoryExample) > 0;
	}

	@Override
	public List<Category> selectAll() {
		CategoryExample categoryExample = new CategoryExample();
		com.blog.pojo.CategoryExample.Criteria criteria= categoryExample.createCriteria();
		criteria.andCategoryIdIsNotNull();
		
		return categoryMapper.selectByExample(categoryExample);
	}

	@Override
	public Category selectById(Integer id) {
		
		return categoryMapper.selectByPrimaryKey(id);
	}

}
