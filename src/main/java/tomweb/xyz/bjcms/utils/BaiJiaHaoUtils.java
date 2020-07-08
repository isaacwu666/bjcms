package tomweb.xyz.bjcms.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tomweb.xyz.bjcms.dto.QueryArticleList;
import tomweb.xyz.bjcms.pojo.BjAccount;

/**
 * 百家号工具类
 */
@Component
public class BaiJiaHaoUtils {
    @Autowired
    RestTemplate restTemplate;



    public Object getArticleList(BjAccount bjAccount,Integer page,Integer size){
        QueryArticleList articleList=bjAccount.toQueryArticleList(null);
        articleList.setPage_no(page);
        articleList.setPage_size(size);

        String url="https://baijiahao.baidu.com/builderinner/open/resource/article/gallery";
        

        return null;
    }



}
