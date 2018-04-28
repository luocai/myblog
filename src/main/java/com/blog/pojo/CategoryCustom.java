package com.blog.pojo;

public class CategoryCustom extends Category {
	
	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	private int articleCount;
}
