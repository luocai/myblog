package com.blog.service;

import com.blog.pojo.User;

public interface UserService {
	
	boolean insertUser(User user);
	
	boolean deleteUser(User user);
	
	User selectById(Integer id);
	
	User selectByUsername(String username);
	
}
