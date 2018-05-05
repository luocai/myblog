package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mapper.CommentMapper;
import com.blog.pojo.Comment;
import com.blog.pojo.CommentExample;
import com.blog.pojo.CommentExample.Criteria;
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

	@Override
	public List<Comment> selectByArticleId(Integer id) {
		CommentExample commentExample = new CommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andArticleIdEqualTo(id);
		
		return commentMapper.selectByExample(commentExample);
	}

//	@Override
//	public List<Comment> selectAll() {
//		
//		CommentExample commentExample = new CommentExample();
//		Criteria criteria = commentExample.createCriteria();
//		criteria.andCommentIdIsNotNull();
//		
//		return commentMapper.selectByExample(commentExample);
//	}
	
	@Override
	public List<Comment> selectAll() {
		
		
		
		List<Comment> list = commentMapper.selectAll();
System.out.println("list:" + list);

		return list;
	}

	@Override
	public boolean updateComment(Comment comment) {
		
		return commentMapper.updateByPrimaryKey(comment) > 0;
	}

//	@Override
//	public List<Comment> selectAll() {
//		CommentExample commentExample = new CommentExample();
//		Criteria criteria = commentExample.createCriteria();
//		criteria.andCommentIdIsNotNull();
//		
//		return commentMapper.selectByExample(commentExample);
//	}

	

	
	
}
