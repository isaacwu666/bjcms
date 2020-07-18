package tomweb.xyz.bjcms.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
* Created by Mybatis Generator on 2020/07/17
* 数据库表格名字：category
*/
@ApiModel(value="文章类目")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)/*链式调用*/
public class Category {
    /**
     * 文章分类ID
     */
    @ApiModelProperty(value="文章分类ID",required = true)
    private Integer categoryId;

    /**
     * 类目名称
     */
    @ApiModelProperty(value="类目名称",required = true)
    @NotNull(message ="'类目名称'不能为空")
    private String categoryName;


    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",required = true)
    @NotNull(message ="'创建时间'不能为空")
    private Date createdOn;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",required = false)
    private Date updatedOn;

    /**
     * 排序
     */
    @ApiModelProperty(value="排序",required = true)
    @NotNull(message ="'排序'不能为空")
    private Integer orderNo;

    /**
     * 是否删除
     */
    @ApiModelProperty(value="是否删除",required = true)
    @NotNull(message ="'是否删除'不能为空")
    private Boolean isDelete;
}