package tomweb.xyz.bjcms.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tomweb.xyz.bjcms.pojo.Category;
import tomweb.xyz.bjcms.utils.BeanVlidate;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Mybatis Generator on 2020/07/17
 * 数据库表格名字：category
 */
@ApiModel(value = "文章类目")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)/*链式调用*/
public class AddCategory {


    /**
     * 类目名称
     */
    @ApiModelProperty(value = "类目名称", required = true)
    @NotNull(message = "'类目名称'不能为空")
    private String categoryName;


    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "'排序'不能为空")
    private Integer orderNo;

    public Category toCategory() {
//        BeanVlidate.validate(this);
        Category category = new Category();
        return category.setCategoryName(categoryName).setOrderNo(orderNo);

    }
}