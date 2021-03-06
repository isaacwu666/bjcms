package tomweb.xyz.bjcms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tomweb.xyz.bjcms.pojo.ArticleCoverPhoto;
import tomweb.xyz.bjcms.pojo.ArticleCoverPhotoExample;

import java.util.Collection;
import java.util.List;

/**
* Created by Mybatis Generator on 2020/07/10
*/
@Repository
@Mapper
public interface ArticleCoverPhotoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_cover_photo
     *
     * @mbg.generated 2020-07-10
     */
    long countByExample(ArticleCoverPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_cover_photo
     *
     * @mbg.generated 2020-07-10
     */
    int deleteByExample(ArticleCoverPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_cover_photo
     *
     * @mbg.generated 2020-07-10
     */
    int insert(ArticleCoverPhoto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_cover_photo
     *
     * @mbg.generated 2020-07-10
     */
    int insertSelective(ArticleCoverPhoto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_cover_photo
     *
     * @mbg.generated 2020-07-10
     */
    List<ArticleCoverPhoto> selectByExample(ArticleCoverPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_cover_photo
     *
     * @mbg.generated 2020-07-10
     */
    int updateByExampleSelective(@Param("record") ArticleCoverPhoto record, @Param("example") ArticleCoverPhotoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_cover_photo
     *
     * @mbg.generated 2020-07-10
     */
    int updateByExample(@Param("record") ArticleCoverPhoto record, @Param("example") ArticleCoverPhotoExample example);

    List<ArticleCoverPhoto> selectByAricleIds(@Param("ids") List ids);
}