package com.blog.service;

import java.util.List;

import com.blog.pojo.Comment;

public interface CommentService {
	
	boolean insertComment(Comment comment);
	
	boolean deleteComment(Comment comment);
	
	Comment selectCommentById(Integer id);
	
	List<Comment> selectByArticleId(Integer id);
	
	List<Comment> selectAll();
	
	//增加回复
	boolean updateComment(Comment comment);
}
