package tomweb.xyz.bjcms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tomweb.xyz.bjcms.pojo.Config;
import tomweb.xyz.bjcms.pojo.ConfigExample;

import java.util.List;

/**
* Created by Mybatis Generator on 2020/07/18
*/
@Repository
public interface ConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    long countByExample(ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    int deleteByExample(ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    int insert(Config record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    int insertSelective(Config record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    List<Config> selectByExample(ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    Config selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    int updateByExampleSelective(@Param("record") Config record, @Param("example") ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    int updateByExample(@Param("record") Config record, @Param("example") ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    int updateByPrimaryKeySelective(Config record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config
     *
     * @mbg.generated 2020-07-18
     */
    int updateByPrimaryKey(Config record);
}