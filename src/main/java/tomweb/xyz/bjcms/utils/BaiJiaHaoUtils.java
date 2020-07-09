package tomweb.xyz.bjcms.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import net.minidev.json.JSONObject;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import tomweb.xyz.bjcms.dto.QueryArticleList;
import tomweb.xyz.bjcms.dto.bj.ArticleList;
import tomweb.xyz.bjcms.dto.bj.BjArticleDate;
import tomweb.xyz.bjcms.pojo.BjAccount;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 百家号工具类
 */
@Component
public class BaiJiaHaoUtils {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 请求地址 builderinner/open/resource/query/articleListall
     * 参数名称	类型	是否必须	描述
     * app_token	string	是	授权密钥
     * app_id	string	是	作者帐号ID
     * start_time	string	否	支持按照年月日格式（2019-06-01）进行查询，仅支持查询到日维度的数据
     * end_time	string	否	支持按照年月日格式（2019-07-01）进行查询，仅支持查询到日维度的数据
     * page_no	int	否	查询页码，不传默认为1
     * page_size	int	否	查询条数，不能超过20，不传默认为20
     * article_type	string	否	文章类型，news-图文、gallery-图集、video-视频，不传默认查询所有支持的文章类型
     * collection	string	否	文章状态集，不传默认查询所有支持的文章状态集 draft-草稿、publish-已发布、pre_publish-待发布、withdraw-已撤回、rejected-未通过
     *
     * @param bjAccount
     * @param page
     * @param size
     * @return
     * @throws URISyntaxException
     */
    public ArticleList getArticleList(BjAccount bjAccount, Integer page, Integer size) throws URISyntaxException {
        QueryArticleList articleList = bjAccount.toQueryArticleList(null);
        articleList.setCollection("publish");
        articleList.setArticle_type("news");
        articleList.setPage_no(page);
        articleList.setPage_size(size);
        String url = "https://baijiahao.baidu.com/builderinner/open/resource/query/articleListall";
        URI uri = new URIBuilder()

                .setScheme("https")
                .setHost("baijiahao.baidu.com")
                .setPath("/builderinner/open/resource/query/articleListall")
                .setParameter("app_id", bjAccount.getAppId())
                .setParameter("app_token", bjAccount.getAppToken())
                .setParameter("page_no", String.valueOf(page))
                .setParameter("page_size", "" + size)
                .setParameter("start_time", "2010-01-01")
                .setParameter("end_time", DateUtils.formatDate(new Date(), "yyyy-MM-dd"))
                .setParameter("article_type", articleList.getArticle_type())
                .setParameter("collection", articleList.getCollection())
                .build();

        String res = restTemplate.getForEntity(uri, String.class).getBody();
        System.out.println("res" + res);

        ArticleList resultMap =
                JSON.parseObject(res, new TypeReference<ArticleList>() {
                });
        return resultMap;
    }


    public String getArticleBody(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        String string = restTemplate.getForEntity(url, String.class).getBody();
        //清晰
        return string;
    }
}
