package tomweb.xyz.bjcms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tomweb.xyz.bjcms.pojo.BjArticle;
import tomweb.xyz.bjcms.pojo.BjArticleExample;

public interface BjArticleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    long countByExample(BjArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    int deleteByExample(BjArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    int insert(BjArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    int insertSelective(BjArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    List<BjArticle> selectByExampleWithBLOBs(BjArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    List<BjArticle> selectByExample(BjArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    BjArticle selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    int updateByExampleSelective(@Param("record") BjArticle record, @Param("example") BjArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") BjArticle record, @Param("example") BjArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    int updateByExample(@Param("record") BjArticle record, @Param("example") BjArticleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    int updateByPrimaryKeySelective(BjArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(BjArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bj_article
     *
     * @mbg.generated Sun Jul 12 23:06:17 CST 2020
     */
    int updateByPrimaryKey(BjArticle record);
}