package tomweb.xyz.bjcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomweb.xyz.bjcms.dao.AdminInfoMapper;
import tomweb.xyz.bjcms.pojo.AdminInfo;
import tomweb.xyz.bjcms.pojo.AdminInfoExample;

import java.util.List;

@Service
public class AdminInfoService {
    @Autowired
    AdminInfoMapper adminInfoMapper;


    public AdminInfo login(String accountSecret, String passwordSecret) {
        AdminInfoExample example=new AdminInfoExample();
        example.createCriteria().andAccountEqualTo(accountSecret)
                .andPasswordEqualTo(passwordSecret);
       List<AdminInfo> adminInfos= adminInfoMapper.selectByExample(example);
        return adminInfos.size()>0?adminInfos.get(0):null;
    }
}
