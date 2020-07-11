package tomweb.xyz.bjcms.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
* Created by Mybatis Generator on 2020/07/10
* 数据库表格名字：article_cover_photo
*/
@ApiModel(value="文章封面数据表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)/*链式调用*/
public class ArticleCoverPhoto {
    /**
     * 文章ID
     */
    @ApiModelProperty(value="文章ID",required = true)
    @NotNull(message ="'文章ID'不能为空")
    private Integer articleId;

    /**
     * 图片URL
     */
    @ApiModelProperty(value="图片URL",required = true)
    @NotNull(message ="'图片URL'不能为空")
    private String photoUrl;

    /**
     * 排序编码
     */
    @ApiModelProperty(value="排序编码",required = true)
    @NotNull(message ="'排序编码'不能为空")
    private Integer orderNo;
}