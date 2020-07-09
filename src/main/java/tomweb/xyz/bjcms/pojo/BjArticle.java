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
* Created by Mybatis Generator on 2020/07/10
* 数据库表格名字：bj_article
*/
@ApiModel(value="从百家平台同步过来的账号信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)/*链式调用*/
public class BjArticle {
    /**
     * 记录ID
     */
    @ApiModelProperty(value="记录ID",required = true)
    @NotNull(message ="'记录ID'不能为空")
    private Integer id;

    /**
     * 文章id
     */
    @ApiModelProperty(value="文章id",required = true)
    @NotNull(message ="'文章id'不能为空")
    private String articleId;

    /**
     * appId
     */
    @ApiModelProperty(value="appId",required = true)
    @NotNull(message ="'appId'不能为空")
    private String appId;

    /**
     * nid
     */
    @ApiModelProperty(value="nid",required = true)
    @NotNull(message ="'nid'不能为空")
    private String nid;

    /**
     * 百度平台更新于
     */
    @ApiModelProperty(value="百度平台更新于",required = true)
    @NotNull(message ="'百度平台更新于'不能为空")
    private Long updatedAt;

    /**
     * 文章类型，news-图文、gallery-图集、video-视频，不传默认查询所有支持的文章类型
     */
    @ApiModelProperty(value="文章类型，news-图文、gallery-图集、video-视频，不传默认查询所有支持的文章类型",required = true)
    @NotNull(message ="'文章类型，news-图文、gallery-图集、video-视频，不传默认查询所有支持的文章类型'不能为空")
    private String type;

    /**
     * status
     */
    @ApiModelProperty(value="status",required = true)
    @NotNull(message ="'status'不能为空")
    private String status;

    /**
     * 文章状态集，不传默认查询所有支持的文章状态集 draft-草稿、publish-已发布、pre_publish-待发布、withdraw-已撤回、rejected-未通过
     */
    @ApiModelProperty(value="文章状态集，不传默认查询所有支持的文章状态集 draft-草稿、publish-已发布、pre_publish-待发布、withdraw-已撤回、rejected-未通过",required = true)
    @NotNull(message ="'文章状态集，不传默认查询所有支持的文章状态集 draft-草稿、publish-已发布、pre_publish-待发布、withdraw-已撤回、rejected-未通过'不能为空")
    private String collection;

    /**
     * 标题
     */
    @ApiModelProperty(value="标题",required = true)
    @NotNull(message ="'标题'不能为空")
    private String title;

    /**
     * 文章访问URL
     */
    @ApiModelProperty(value="文章访问URL",required = true)
    @NotNull(message ="'文章访问URL'不能为空")
    private String articleUrl;

    /**
     * 记录创建时间
     */
    @ApiModelProperty(value="记录创建时间",required = false)
    private Date createdOn;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",required = false)
    private Date updateOn;

    /**
     * 是否移除
     */
    @ApiModelProperty(value="是否移除",required = false)
    private Boolean isDelete;

    /**
     * 文章内容
     */
    @ApiModelProperty(value="文章内容",required = false)
    private String articleBody;
}