package tomweb.xyz.bjcms.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tomweb.xyz.bjcms.dto.admin.LoginDto;
import tomweb.xyz.bjcms.pojo.AdminInfo;
import tomweb.xyz.bjcms.service.AdminInfoService;
import tomweb.xyz.bjcms.utils.JwtUtils;
import tomweb.xyz.bjcms.vo.BaseVo;

import java.io.UnsupportedEncodingException;
@RestController
public class AdminInfoApi extends BaseApi {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AdminInfoService adminInfoService;

    private String deCodePassword(String p) {
        return (DigestUtils.md5Hex(p + "104psmsaoc")).toUpperCase();
    }


    @PostMapping("adminApi/login")
    public BaseVo<String> login(@RequestBody LoginDto loginDto) {
        AdminInfo adminInfo = adminInfoService.login(loginDto.getAccountSecret(), deCodePassword(loginDto.getPasswordSecret()));
        if (adminInfo == null) {
            return error();
        }
        return success(jwtUtils.generateToken(adminInfo.getAdminId()));
    }



    @PostMapping("adminApi/logout")
    public BaseVo<AdminInfo> logout(@RequestBody LoginDto loginDto) {


        return null;
    }
}
