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
* Created by Mybatis Generator on 2020/07/18
* 数据库表格名字：config
*/
@ApiModel(value="配置项目")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)/*链式调用*/
public class Config {
    /**
     * id
     */
    @ApiModelProperty(value="id",required = true)
    @NotNull(message ="'id'不能为空")
    private Integer id;

    /**
     * 配置编码
     */
    @ApiModelProperty(value="配置编码",required = true)
    @NotNull(message ="'配置编码'不能为空")
    private String configKey;

    /**
     * 配置的值
     */
    @ApiModelProperty(value="配置的值",required = true)
    @NotNull(message ="'配置的值'不能为空")
    private String configValue;

    /**
     * 配置类型
     */
    @ApiModelProperty(value="配置类型",required = true)
    @NotNull(message ="'配置类型'不能为空")
    private String configType;

    /**
     * 值的数据类型
     */
    @ApiModelProperty(value="值的数据类型",required = true)
    @NotNull(message ="'值的数据类型'不能为空")
    private String valueType;

    /**
     * 配置名称
     */
    @ApiModelProperty(value="配置名称",required = true)
    @NotNull(message ="'配置名称'不能为空")
    private String detail;

    /**
     * 
     */
    @ApiModelProperty(value="",required = true)
    @NotNull(message ="''不能为空")
    private Date createdOn;

    /**
     * 
     */
    @ApiModelProperty(value="",required = false)
    private Date updatedOn;

    /**
     * 
     */
    @ApiModelProperty(value="",required = true)
    @NotNull(message ="''不能为空")
    private Boolean isDelete;
}