package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mapper.UserMapper;
import com.blog.pojo.User;
import com.blog.pojo.UserExample;
import com.blog.pojo.UserExample.Criteria;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean insertUser(User user) {
		
		return userMapper.insert(user) > 0;
	}

	@Override
	public boolean deleteUser(User user) {

		return userMapper.deleteByPrimaryKey(user.getUserId()) > 0;
	}

	@Override
	public User selectById(Integer id) {
		
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User selectByUsername(String username) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
//System.out.println("size:" + userMapper.selectByExample(userExample).size());
		List<User> list = userMapper.selectByExample(userExample);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

}
