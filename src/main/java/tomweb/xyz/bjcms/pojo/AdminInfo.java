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
* 数据库表格名字：admin_info
*/
@ApiModel(value="管理员账号信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)/*链式调用*/
public class AdminInfo {
    /**
     * 管理账号主键
     */
    @ApiModelProperty(value="管理账号主键",required = true)
    @NotNull(message ="'管理账号主键'不能为空")
    private Integer adminId;

    /**
     * 账号名称
     */
    @ApiModelProperty(value="账号名称",required = false)
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码",required = false)
    private String password;

    /**
     * 账号昵称
     */
    @ApiModelProperty(value="账号昵称",required = false)
    private String nickName;

    /**
     * 状态，1正常，2锁定
     */
    @ApiModelProperty(value="状态，1正常，2锁定",required = false)
    private Integer status;

    /**
     * 账号锁定时间
     */
    @ApiModelProperty(value="账号锁定时间",required = false)
    private Date lockTime;

    /**
     * 登陆的IP
     */
    @ApiModelProperty(value="登陆的IP",required = false)
    private String loginIp;
}