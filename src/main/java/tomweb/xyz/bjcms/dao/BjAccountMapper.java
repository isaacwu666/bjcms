package tomweb.xyz.bjcms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tomweb.xyz.bjcms.pojo.BjAccount;
import tomweb.xyz.bjcms.pojo.BjAccountExample;

import java.util.List;

/**
* Created by Mybatis Generator on 2020/07/08
*/
@Repository
public interface BjAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    long countByExample(BjAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    int deleteByExample(BjAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    int insert(BjAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    int insertSelective(BjAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    List<BjAccount> selectByExample(BjAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    BjAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    int updateByExampleSelective(@Param("record") BjAccount record, @Param("example") BjAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    int updateByExample(@Param("record") BjAccount record, @Param("example") BjAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    int updateByPrimaryKeySelective(BjAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_account
     *
     * @mbg.generated 2020-07-08
     */
    int updateByPrimaryKey(BjAccount record);
}