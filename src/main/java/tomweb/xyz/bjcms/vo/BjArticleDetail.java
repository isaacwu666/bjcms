package tomweb.xyz.bjcms.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import tomweb.xyz.bjcms.pojo.ArticleCoverPhoto;
import tomweb.xyz.bjcms.pojo.BjArticle;
import tomweb.xyz.bjcms.pojo.Category;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)/*链式调用*/
public class BjArticleDetail extends BjArticle {

    @ApiModelProperty("封面")
    List<ArticleCoverPhoto> covers;
    /**
     * 类目名称
     */
    @ApiModelProperty(value="类目名称",required = true)
    @NotNull(message ="'类目名称'不能为空")
    private String categoryName;


}
