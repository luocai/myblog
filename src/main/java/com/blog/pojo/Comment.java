package com.blog.pojo;

import java.util.Date;

public class Comment {
    @Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", userId=" + userId + ", commentTime=" + commentTime
				+ ", articleId=" + articleId + ", commentContent=" + commentContent + ", userName=" + userName
				+ ", reply=" + reply + ", replyState=" + replyState + "]";
	}

	private Integer commentId;

    private Integer userId;

    private Date commentTime;

    private Integer articleId;

    private String commentContent;

    private String userName;

    private String reply;

    private Integer replyState;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public Integer getReplyState() {
        return replyState;
    }

    public void setReplyState(Integer replyState) {
        this.replyState = replyState;
    }
}