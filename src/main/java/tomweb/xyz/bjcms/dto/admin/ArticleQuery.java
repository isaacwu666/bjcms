package tomweb.xyz.bjcms.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;
import tomweb.xyz.bjcms.dto.BaseQuery;
import tomweb.xyz.bjcms.pojo.BjArticleExample;
@Data
@ApiModel("后台文章查询")
public class ArticleQuery extends BaseQuery {

    @ApiModelProperty("文章标题")
    String title;
    @ApiModelProperty("文章类目ID")
    Integer categoryId;
    /**
     * 在主页的发布状态，0未发布，1发布
     */
    @ApiModelProperty(value = "在主页的发布状态，0未发布，1发布", required = false)
    private Integer publicStatus;


    public BjArticleExample toExample() {
        BjArticleExample example = new BjArticleExample();
        BjArticleExample.Criteria criteria = example.createCriteria().andIsDeleteEqualTo(false);
        if (!StringUtils.isEmpty(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (categoryId != null) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if (publicStatus != null) {
            criteria.andPublicStatusEqualTo(publicStatus);
        }
        example.setOrderByClause("created_on desc, updated_at desc");

        return example;
    }

}
