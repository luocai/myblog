package com.blog.service;

import java.util.List;

import com.blog.pojo.Category;

public interface CategoryService {
	
	boolean insertCategory(Category category);
	
	boolean updateCategory(Category category);
	
	boolean deleteCategory(Category category);
	
	List<Category> selectAll();
}
