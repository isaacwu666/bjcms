/**
  * Copyright 2020 bejson.com 
  */
package tomweb.xyz.bjcms.dto.bj;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class AritcleItem {
     @ApiModelProperty("文章ID")
    @JSONField(name = "article_id")
    private String articleId;
     @ApiModelProperty("")
    @JSONField(name = "app_id")
    private String appId;
     @ApiModelProperty("")
    @JSONField(name = "nid")
    private String nid;
     @ApiModelProperty("更新于")
    @JSONField(name = "updated_at")
    private long updatedAt;
     @ApiModelProperty("文章类型，news-图文、gallery-图集、video-视频，不传默认查询所有支持的文章类型")
    @JSONField(name = "type")
    private String type;
     @ApiModelProperty("状态\t含义\t隶属状态集\n" +
             "draft\t草稿\tdraft\n" +
             "publish\t已发布\tpublish\n" +
             "published\t已发布旧状态码\tpublish\n" +
             "pre_publish\t等待发布，一般用于定时发布\tpre_publish\n" +
             "audit\t审核中\tpre_publish\n" +
             "withdraw\t撤回\twithdraw\n" +
             "rejected\t审核未通过\trejected\n" +
             "forbidden\t已下线\trejected")
    @JSONField(name = "status")
    private String status;
     @ApiModelProperty("文章状态集，不传默认查询所有支持的文章状态集 draft-草稿、publish-已发布、pre_publish-待发布、withdraw-已撤回、rejected-未通过")
    @JSONField(name = "collection")
    private String collection;
     @ApiModelProperty("文章标题")
    @JSONField(name = "title")
    private String title;
     @ApiModelProperty("文章地址")
    @JSONField(name = "article_url")
    private String articleUrl;




}