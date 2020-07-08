package tomweb.xyz.bjcms.pojo;

//import io.swagger.annotations.ApiModel;
////import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tomweb.xyz.bjcms.dto.QueryArticleList;

import javax.validation.constraints.NotNull;

/**
 * Created by Mybatis Generator on 2020/07/08
 * 数据库表格名字：bj_account
 */
//@ApiModel(value="tomweb.xyz.bjcms.pojo.BjAccount")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)/*链式调用*/
public class BjAccount {
    /**
     * 主键ID
     */
////    @ApiModelProperty(value="主键ID",required = true)
    @NotNull(message = "'主键ID'不能为空")
    private Integer id;

    /**
     * 作者账号ID,app_id是百家号帐号的标识，配合app_token可调用百家号接口服务。
     */
////    @ApiModelProperty(value="作者账号ID,app_id是百家号帐号的标识，配合app_token可调用百家号接口服务。",required = false)
    private String appId;

    /**
     * 授权密钥,app_token是调用百家号接口的关键凭证，具有极高的安全性，请妥善保管，切勿直接交付第三方或直接存储在代码中。若app_token泄露或遗失，请立即重置
     */
//    @ApiModelProperty(value="授权密钥,app_token是调用百家号接口的关键凭证，具有极高的安全性，请妥善保管，切勿直接交付第三方或直接存储在代码中。若app_token泄露或遗失，请立即重置",required = false)
    private String appToken;

    /**
     * 服务器URL
     */
//    @ApiModelProperty(value="服务器URL",required = false)
    private String serverUrl;

    /**
     * 服务器token
     */
//    @ApiModelProperty(value="服务器token",required = false)
    private String serverToken;

    /**
     * Encoding AESKey
     */
//    @ApiModelProperty(value="Encoding AESKey",required = false)
    private String serverEncodingAESKey;

    public QueryArticleList toQueryArticleList(QueryArticleList queryArticleList) {
        if (queryArticleList == null) {
            queryArticleList = new QueryArticleList();
        }
        queryArticleList.setApp_id(appId);
        queryArticleList.setApp_token(appToken);
        return queryArticleList;
    }

}