package com.blog.pojo;

import java.util.Date;

public class Article {
    private Integer articleId;

    private String articleTitle;

    private Date publishTime;

    private Integer writerId;

    private Integer category;

    private Integer click;

    private Integer state;
    
    private String articleContent;
    
    private String summary;

    @Override
	public String toString() {
		return "Article [articleId=" + articleId + ", articleTitle=" + articleTitle + ", publishTime=" + publishTime
				+ ", writerId=" + writerId + ", category=" + category + ", click=" + click + ", state=" + state
				+ ", articleContent=" + articleContent + ", summary=" + summary + "]";
	}

	public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}