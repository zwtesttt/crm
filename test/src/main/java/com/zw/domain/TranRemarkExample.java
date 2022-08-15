package com.zw.domain;

import java.util.ArrayList;
import java.util.List;

public class TranRemarkExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    public TranRemarkExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNote_contentIsNull() {
            addCriterion("note_content is null");
            return (Criteria) this;
        }

        public Criteria andNote_contentIsNotNull() {
            addCriterion("note_content is not null");
            return (Criteria) this;
        }

        public Criteria andNote_contentEqualTo(String value) {
            addCriterion("note_content =", value, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentNotEqualTo(String value) {
            addCriterion("note_content <>", value, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentGreaterThan(String value) {
            addCriterion("note_content >", value, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentGreaterThanOrEqualTo(String value) {
            addCriterion("note_content >=", value, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentLessThan(String value) {
            addCriterion("note_content <", value, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentLessThanOrEqualTo(String value) {
            addCriterion("note_content <=", value, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentLike(String value) {
            addCriterion("note_content like", value, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentNotLike(String value) {
            addCriterion("note_content not like", value, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentIn(List<String> values) {
            addCriterion("note_content in", values, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentNotIn(List<String> values) {
            addCriterion("note_content not in", values, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentBetween(String value1, String value2) {
            addCriterion("note_content between", value1, value2, "note_content");
            return (Criteria) this;
        }

        public Criteria andNote_contentNotBetween(String value1, String value2) {
            addCriterion("note_content not between", value1, value2, "note_content");
            return (Criteria) this;
        }

        public Criteria andCreate_byIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreate_byIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_byEqualTo(String value) {
            addCriterion("create_by =", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotEqualTo(String value) {
            addCriterion("create_by <>", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byGreaterThan(String value) {
            addCriterion("create_by >", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byLessThan(String value) {
            addCriterion("create_by <", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byLike(String value) {
            addCriterion("create_by like", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotLike(String value) {
            addCriterion("create_by not like", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byIn(List<String> values) {
            addCriterion("create_by in", values, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotIn(List<String> values) {
            addCriterion("create_by not in", values, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeEqualTo(String value) {
            addCriterion("create_time =", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThan(String value) {
            addCriterion("create_time >", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThan(String value) {
            addCriterion("create_time <", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLike(String value) {
            addCriterion("create_time like", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotLike(String value) {
            addCriterion("create_time not like", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIn(List<String> values) {
            addCriterion("create_time in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andEdit_byIsNull() {
            addCriterion("edit_by is null");
            return (Criteria) this;
        }

        public Criteria andEdit_byIsNotNull() {
            addCriterion("edit_by is not null");
            return (Criteria) this;
        }

        public Criteria andEdit_byEqualTo(String value) {
            addCriterion("edit_by =", value, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byNotEqualTo(String value) {
            addCriterion("edit_by <>", value, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byGreaterThan(String value) {
            addCriterion("edit_by >", value, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byGreaterThanOrEqualTo(String value) {
            addCriterion("edit_by >=", value, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byLessThan(String value) {
            addCriterion("edit_by <", value, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byLessThanOrEqualTo(String value) {
            addCriterion("edit_by <=", value, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byLike(String value) {
            addCriterion("edit_by like", value, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byNotLike(String value) {
            addCriterion("edit_by not like", value, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byIn(List<String> values) {
            addCriterion("edit_by in", values, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byNotIn(List<String> values) {
            addCriterion("edit_by not in", values, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byBetween(String value1, String value2) {
            addCriterion("edit_by between", value1, value2, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_byNotBetween(String value1, String value2) {
            addCriterion("edit_by not between", value1, value2, "edit_by");
            return (Criteria) this;
        }

        public Criteria andEdit_timeIsNull() {
            addCriterion("edit_time is null");
            return (Criteria) this;
        }

        public Criteria andEdit_timeIsNotNull() {
            addCriterion("edit_time is not null");
            return (Criteria) this;
        }

        public Criteria andEdit_timeEqualTo(String value) {
            addCriterion("edit_time =", value, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeNotEqualTo(String value) {
            addCriterion("edit_time <>", value, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeGreaterThan(String value) {
            addCriterion("edit_time >", value, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeGreaterThanOrEqualTo(String value) {
            addCriterion("edit_time >=", value, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeLessThan(String value) {
            addCriterion("edit_time <", value, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeLessThanOrEqualTo(String value) {
            addCriterion("edit_time <=", value, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeLike(String value) {
            addCriterion("edit_time like", value, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeNotLike(String value) {
            addCriterion("edit_time not like", value, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeIn(List<String> values) {
            addCriterion("edit_time in", values, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeNotIn(List<String> values) {
            addCriterion("edit_time not in", values, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeBetween(String value1, String value2) {
            addCriterion("edit_time between", value1, value2, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_timeNotBetween(String value1, String value2) {
            addCriterion("edit_time not between", value1, value2, "edit_time");
            return (Criteria) this;
        }

        public Criteria andEdit_flagIsNull() {
            addCriterion("edit_flag is null");
            return (Criteria) this;
        }

        public Criteria andEdit_flagIsNotNull() {
            addCriterion("edit_flag is not null");
            return (Criteria) this;
        }

        public Criteria andEdit_flagEqualTo(String value) {
            addCriterion("edit_flag =", value, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagNotEqualTo(String value) {
            addCriterion("edit_flag <>", value, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagGreaterThan(String value) {
            addCriterion("edit_flag >", value, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagGreaterThanOrEqualTo(String value) {
            addCriterion("edit_flag >=", value, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagLessThan(String value) {
            addCriterion("edit_flag <", value, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagLessThanOrEqualTo(String value) {
            addCriterion("edit_flag <=", value, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagLike(String value) {
            addCriterion("edit_flag like", value, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagNotLike(String value) {
            addCriterion("edit_flag not like", value, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagIn(List<String> values) {
            addCriterion("edit_flag in", values, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagNotIn(List<String> values) {
            addCriterion("edit_flag not in", values, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagBetween(String value1, String value2) {
            addCriterion("edit_flag between", value1, value2, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andEdit_flagNotBetween(String value1, String value2) {
            addCriterion("edit_flag not between", value1, value2, "edit_flag");
            return (Criteria) this;
        }

        public Criteria andTran_idIsNull() {
            addCriterion("tran_id is null");
            return (Criteria) this;
        }

        public Criteria andTran_idIsNotNull() {
            addCriterion("tran_id is not null");
            return (Criteria) this;
        }

        public Criteria andTran_idEqualTo(String value) {
            addCriterion("tran_id =", value, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idNotEqualTo(String value) {
            addCriterion("tran_id <>", value, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idGreaterThan(String value) {
            addCriterion("tran_id >", value, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idGreaterThanOrEqualTo(String value) {
            addCriterion("tran_id >=", value, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idLessThan(String value) {
            addCriterion("tran_id <", value, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idLessThanOrEqualTo(String value) {
            addCriterion("tran_id <=", value, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idLike(String value) {
            addCriterion("tran_id like", value, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idNotLike(String value) {
            addCriterion("tran_id not like", value, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idIn(List<String> values) {
            addCriterion("tran_id in", values, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idNotIn(List<String> values) {
            addCriterion("tran_id not in", values, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idBetween(String value1, String value2) {
            addCriterion("tran_id between", value1, value2, "tran_id");
            return (Criteria) this;
        }

        public Criteria andTran_idNotBetween(String value1, String value2) {
            addCriterion("tran_id not between", value1, value2, "tran_id");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated do_not_delete_during_merge Thu Aug 11 15:29:10 CST 2022
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
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