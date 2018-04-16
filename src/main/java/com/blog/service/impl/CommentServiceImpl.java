package com.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mapper.CommentMapper;
import com.blog.pojo.Comment;
import com.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public boolean insertComment(Comment comment) {
		
		return commentMapper.insert(comment) > 0;
	}

	@Override
	public boolean deleteComment(Comment comment) {
		return commentMapper.deleteByPrimaryKey(comment.getCommentId()) > 0;
	}

	@Override
	public Comment selectCommentById(Integer id) {
		
		return commentMapper.selectByPrimaryKey(id);
	}
	

	
	
}
