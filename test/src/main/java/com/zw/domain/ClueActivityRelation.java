package com.zw.domain;

public class ClueActivityRelation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_clue_activity_relation.id
     *
     * @mbggenerated Tue Aug 09 20:42:21 CST 2022
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_clue_activity_relation.clue_id
     *
     * @mbggenerated Tue Aug 09 20:42:21 CST 2022
     */
    private String clue_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_clue_activity_relation.activity_id
     *
     * @mbggenerated Tue Aug 09 20:42:21 CST 2022
     */
    private String activity_id;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_clue_activity_relation.id
     *
     * @return the value of tbl_clue_activity_relation.id
     *
     * @mbggenerated Tue Aug 09 20:42:21 CST 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_clue_activity_relation.id
     *
     * @param id the value for tbl_clue_activity_relation.id
     *
     * @mbggenerated Tue Aug 09 20:42:21 CST 2022
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_clue_activity_relation.clue_id
     *
     * @return the value of tbl_clue_activity_relation.clue_id
     *
     * @mbggenerated Tue Aug 09 20:42:21 CST 2022
     */
    public String getClue_id() {
        return clue_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_clue_activity_relation.clue_id
     *
     * @param clue_id the value for tbl_clue_activity_relation.clue_id
     *
     * @mbggenerated Tue Aug 09 20:42:21 CST 2022
     */
    public void setClue_id(String clue_id) {
        this.clue_id = clue_id == null ? null : clue_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_clue_activity_relation.activity_id
     *
     * @return the value of tbl_clue_activity_relation.activity_id
     *
     * @mbggenerated Tue Aug 09 20:42:21 CST 2022
     */
    public String getActivity_id() {
        return activity_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_clue_activity_relation.activity_id
     *
     * @param activity_id the value for tbl_clue_activity_relation.activity_id
     *
     * @mbggenerated Tue Aug 09 20:42:21 CST 2022
     */
    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id == null ? null : activity_id.trim();
    }
}