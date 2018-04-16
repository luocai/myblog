package com.blog.service;

import com.blog.pojo.Comment;

public interface CommentService {
	
	boolean insertComment(Comment comment);
	
	boolean deleteComment(Comment comment);
	
	Comment selectCommentById(Integer id);
}
