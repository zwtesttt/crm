package com.zw.domain;

public class ContactsActivityRelation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts_activity_relation.id
     *
     * @mbggenerated Thu Aug 11 14:46:59 CST 2022
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts_activity_relation.contacts_id
     *
     * @mbggenerated Thu Aug 11 14:46:59 CST 2022
     */
    private String contacts_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts_activity_relation.activity_id
     *
     * @mbggenerated Thu Aug 11 14:46:59 CST 2022
     */
    private String activity_id;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts_activity_relation.id
     *
     * @return the value of tbl_contacts_activity_relation.id
     *
     * @mbggenerated Thu Aug 11 14:46:59 CST 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts_activity_relation.id
     *
     * @param id the value for tbl_contacts_activity_relation.id
     *
     * @mbggenerated Thu Aug 11 14:46:59 CST 2022
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts_activity_relation.contacts_id
     *
     * @return the value of tbl_contacts_activity_relation.contacts_id
     *
     * @mbggenerated Thu Aug 11 14:46:59 CST 2022
     */
    public String getContacts_id() {
        return contacts_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts_activity_relation.contacts_id
     *
     * @param contacts_id the value for tbl_contacts_activity_relation.contacts_id
     *
     * @mbggenerated Thu Aug 11 14:46:59 CST 2022
     */
    public void setContacts_id(String contacts_id) {
        this.contacts_id = contacts_id == null ? null : contacts_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts_activity_relation.activity_id
     *
     * @return the value of tbl_contacts_activity_relation.activity_id
     *
     * @mbggenerated Thu Aug 11 14:46:59 CST 2022
     */
    public String getActivity_id() {
        return activity_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts_activity_relation.activity_id
     *
     * @param activity_id the value for tbl_contacts_activity_relation.activity_id
     *
     * @mbggenerated Thu Aug 11 14:46:59 CST 2022
     */
    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id == null ? null : activity_id.trim();
    }
}