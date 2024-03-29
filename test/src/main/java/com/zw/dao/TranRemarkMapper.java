package com.zw.dao;

import com.zw.domain.TranRemark;
import com.zw.domain.TranRemarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TranRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    int countByExample(TranRemarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    int deleteByExample(TranRemarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    int insert(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    int insertSelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    List<TranRemark> selectByExample(TranRemarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    TranRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    int updateByExampleSelective(@Param("record") TranRemark record, @Param("example") TranRemarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    int updateByExample(@Param("record") TranRemark record, @Param("example") TranRemarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    int updateByPrimaryKeySelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Thu Aug 11 15:29:10 CST 2022
     */
    int updateByPrimaryKey(TranRemark record);

    int insertTranRemarkByList(List<TranRemark> list);

    List<TranRemark> selectTranRemark(String id);
}