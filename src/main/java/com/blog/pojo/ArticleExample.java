package com.blog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andArticleIdIsNull() {
            addCriterion("article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Integer value) {
            addCriterion("article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Integer value) {
            addCriterion("article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Integer value) {
            addCriterion("article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Integer value) {
            addCriterion("article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Integer> values) {
            addCriterion("article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Integer> values) {
            addCriterion("article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("article_id not between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNull() {
            addCriterion("article_title is null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNotNull() {
            addCriterion("article_title is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEqualTo(String value) {
            addCriterion("article_title =", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotEqualTo(String value) {
            addCriterion("article_title <>", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThan(String value) {
            addCriterion("article_title >", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThanOrEqualTo(String value) {
            addCriterion("article_title >=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThan(String value) {
            addCriterion("article_title <", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThanOrEqualTo(String value) {
            addCriterion("article_title <=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLike(String value) {
            addCriterion("article_title like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotLike(String value) {
            addCriterion("article_title not like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIn(List<String> values) {
            addCriterion("article_title in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotIn(List<String> values) {
            addCriterion("article_title not in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleBetween(String value1, String value2) {
            addCriterion("article_title between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotBetween(String value1, String value2) {
            addCriterion("article_title not between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andPublicTimeIsNull() {
            addCriterion("public_time is null");
            return (Criteria) this;
        }

        public Criteria andPublicTimeIsNotNull() {
            addCriterion("public_time is not null");
            return (Criteria) this;
        }

        public Criteria andPublicTimeEqualTo(Date value) {
            addCriterion("public_time =", value, "publicTime");
            return (Criteria) this;
        }

        public Criteria andPublicTimeNotEqualTo(Date value) {
            addCriterion("public_time <>", value, "publicTime");
            return (Criteria) this;
        }

        public Criteria andPublicTimeGreaterThan(Date value) {
            addCriterion("public_time >", value, "publicTime");
            return (Criteria) this;
        }

        public Criteria andPublicTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("public_time >=", value, "publicTime");
            return (Criteria) this;
        }

        public Criteria andPublicTimeLessThan(Date value) {
            addCriterion("public_time <", value, "publicTime");
            return (Criteria) this;
        }

        public Criteria andPublicTimeLessThanOrEqualTo(Date value) {
            addCriterion("public_time <=", value, "publicTime");
            return (Criteria) this;
        }

        public Criteria andPublicTimeIn(List<Date> values) {
            addCriterion("public_time in", values, "publicTime");
            return (Criteria) this;
        }

        public Criteria andPublicTimeNotIn(List<Date> values) {
            addCriterion("public_time not in", values, "publicTime");
            return (Criteria) this;
        }

        public Criteria andPublicTimeBetween(Date value1, Date value2) {
            addCriterion("public_time between", value1, value2, "publicTime");
            return (Criteria) this;
        }

        public Criteria andPublicTimeNotBetween(Date value1, Date value2) {
            addCriterion("public_time not between", value1, value2, "publicTime");
            return (Criteria) this;
        }

        public Criteria andWriterIdIsNull() {
            addCriterion("writer_id is null");
            return (Criteria) this;
        }

        public Criteria andWriterIdIsNotNull() {
            addCriterion("writer_id is not null");
            return (Criteria) this;
        }

        public Criteria andWriterIdEqualTo(Integer value) {
            addCriterion("writer_id =", value, "writerId");
            return (Criteria) this;
        }

        public Criteria andWriterIdNotEqualTo(Integer value) {
            addCriterion("writer_id <>", value, "writerId");
            return (Criteria) this;
        }

        public Criteria andWriterIdGreaterThan(Integer value) {
            addCriterion("writer_id >", value, "writerId");
            return (Criteria) this;
        }

        public Criteria andWriterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("writer_id >=", value, "writerId");
            return (Criteria) this;
        }

        public Criteria andWriterIdLessThan(Integer value) {
            addCriterion("writer_id <", value, "writerId");
            return (Criteria) this;
        }

        public Criteria andWriterIdLessThanOrEqualTo(Integer value) {
            addCriterion("writer_id <=", value, "writerId");
            return (Criteria) this;
        }

        public Criteria andWriterIdIn(List<Integer> values) {
            addCriterion("writer_id in", values, "writerId");
            return (Criteria) this;
        }

        public Criteria andWriterIdNotIn(List<Integer> values) {
            addCriterion("writer_id not in", values, "writerId");
            return (Criteria) this;
        }

        public Criteria andWriterIdBetween(Integer value1, Integer value2) {
            addCriterion("writer_id between", value1, value2, "writerId");
            return (Criteria) this;
        }

        public Criteria andWriterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("writer_id not between", value1, value2, "writerId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdIsNull() {
            addCriterion("catalog_id is null");
            return (Criteria) this;
        }

        public Criteria andCatalogIdIsNotNull() {
            addCriterion("catalog_id is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogIdEqualTo(Integer value) {
            addCriterion("catalog_id =", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdNotEqualTo(Integer value) {
            addCriterion("catalog_id <>", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdGreaterThan(Integer value) {
            addCriterion("catalog_id >", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("catalog_id >=", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdLessThan(Integer value) {
            addCriterion("catalog_id <", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdLessThanOrEqualTo(Integer value) {
            addCriterion("catalog_id <=", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdIn(List<Integer> values) {
            addCriterion("catalog_id in", values, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdNotIn(List<Integer> values) {
            addCriterion("catalog_id not in", values, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdBetween(Integer value1, Integer value2) {
            addCriterion("catalog_id between", value1, value2, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("catalog_id not between", value1, value2, "catalogId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}