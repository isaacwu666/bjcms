package tomweb.xyz.bjcms.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import tomweb.xyz.bjcms.pojo.ArticleCoverPhoto;
import tomweb.xyz.bjcms.pojo.BjArticle;
import tomweb.xyz.bjcms.pojo.Category;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Data
public class BjArticleListVo {

    private Integer id;

    /**
     * 文章分类ID
     */
    @ApiModelProperty(value="文章分类ID",required = true)
    @NotNull(message ="'文章分类ID'不能为空")
    private Integer categoryId;

    /**
     * 类目名称
     */
    @ApiModelProperty(value="类目名称",required = true)
    @NotNull(message ="'类目名称'不能为空")
    private String categoryName;


    @ApiModelProperty(value = "百度平台更新于", required = true)
    private Date bjUpdateAt;
    @ApiModelProperty(value = "百度平台更新于字符串", required = true)
    public String getBjUpdateAtStr() {
        if (bjUpdateAt == null) {
            return null;
        }
        return DateUtils.formatDate(bjUpdateAt, "yyyy-MM-dd");

    }

    /**
     * 文章类型，news-图文、gallery-图集、video-视频，不传默认查询所有支持的文章类型
     */
    @ApiModelProperty(value = "文章类型，news-图文、gallery-图集、video-视频，不传默认查询所有支持的文章类型", required = true)
    @NotNull(message = "'文章类型，news-图文、gallery-图集、video-视频，不传默认查询所有支持的文章类型'不能为空")
    private String type;


    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    @NotNull(message = "'标题'不能为空")
    private String title;

    /**
     * 文章访问URL
     */
    @ApiModelProperty(value = "文章访问URL", required = true)
    @NotNull(message = "'文章访问URL'不能为空")
    private String articleUrl;

    /**
     * 记录创建时间
     */
    @ApiModelProperty(value = "记录创建时间", required = false)
    private Date createdOn;


    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容", required = false)
    private String articleBody;


    public BjArticleListVo(BjArticle bjArticle) {
        if (bjArticle == null) {
            return;
        }

        BeanUtils.copyProperties(bjArticle, this);
        if (getArticleBody()!=null){
            if (getArticleBody().length()>200){
                setArticleBody(getArticleBody().substring(0,200));
            }
        }
        if (bjArticle.getUpdatedAt() != null) {
            bjUpdateAt = new Date(bjArticle.getUpdatedAt() * 1000);
        }
    }

    List<ArticleCoverPhoto> coverPhotos;

    public void addCategory(Category category) {
        if (category==null){
            return;
        }
        this.categoryName=category.getCategoryName();
    }
}
