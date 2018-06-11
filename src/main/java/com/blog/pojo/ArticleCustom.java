package com.blog.pojo;

public class ArticleCustom extends Article {
	
	private String categoryName;
	
	private String publishMonth;
	
	private Integer publishDay;

	public String getPublishMonth() {
		return publishMonth;
	}

	public void setPublishMonth(String publishMonth) {
		this.publishMonth = publishMonth;
	}

	public Integer getPublishDay() {
		return publishDay;
	}

	public void setPublishDay(Integer publishDay) {
		this.publishDay = publishDay;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
