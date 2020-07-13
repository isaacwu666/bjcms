package tomweb.xyz.bjcms.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;
import tomweb.xyz.bjcms.dao.BjArticleMapper;
import tomweb.xyz.bjcms.dto.QueryArticleList;
import tomweb.xyz.bjcms.dto.bj.AritcleItem;
import tomweb.xyz.bjcms.dto.bj.ArticleList;
import tomweb.xyz.bjcms.pojo.BjAccount;
import tomweb.xyz.bjcms.pojo.BjArticle;
import tomweb.xyz.bjcms.pojo.BjArticleExample;
import tomweb.xyz.bjcms.pojo.BjArticle;
import tomweb.xyz.bjcms.utils.BaiJiaHaoUtils;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BjArticleService {
    @Autowired
    BjArticleMapper bjArticleMapper;
    @Autowired
    BaiJiaHaoUtils baiJiaHaoUtils;

    public BjArticleMapper getBjArticleMapper() {
        return bjArticleMapper;
    }

    public void syncBjArticle() throws Exception {
        BjAccount bjAccount = getAccount();
        Integer size = 10;
        Integer total = size;
        try {
            for (int page = 1; page * size <= total; page++) {
                ArticleList articleList = baiJiaHaoUtils.getArticleList(bjAccount, page, size);
                if (articleList.getData().getPage().isHas_next()){
                    total=total+size;
                }
                Map<String, AritcleItem> items = articleList.getData().getItems();
                for (String s : items.keySet()) {
                    AritcleItem aritcleItem=items.get(s);
                    if (aritcleItem.getAppId()==null){
                        aritcleItem.setAppId(bjAccount.getAppId());
                    }
                    syncAritcleItem(aritcleItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }
    }

    /**
     * 同步文章
     *
     * @param aritcleItem
     */
    private void syncAritcleItem(AritcleItem aritcleItem) {

        BjArticleExample example = new BjArticleExample();
        example.createCriteria().andArticleIdEqualTo(aritcleItem.getArticleId());
        List<BjArticle> articles = bjArticleMapper.selectByExampleWithBLOBs(example);
        if (ListUtils.isEmpty(articles)) {
            insertAritcleItem(aritcleItem);
            return;
        } else {
            updateAritcleItem(aritcleItem, articles.get(0));
        }

    }

    private void updateAritcleItem(AritcleItem aritcleItem, BjArticle dbBjArticle) {
        if (aritcleItem.getUpdatedAt() <= dbBjArticle.getUpdatedAt()) {
            return;
        }
        BjArticle toUpdate = new BjArticle();
        BeanUtils.copyProperties(aritcleItem, toUpdate);
        toUpdate.setId(dbBjArticle.getId());
        toUpdate.setArticleBody(baiJiaHaoUtils.getArticleBody(aritcleItem.getArticleUrl()));
        bjArticleMapper.updateByPrimaryKeyWithBLOBs(toUpdate);
    }

    private void insertAritcleItem(AritcleItem aritcleItem) {
        BjArticle bjArticle = new BjArticle();
        BeanUtils.copyProperties(aritcleItem, bjArticle);
        bjArticle.setCreatedOn(new Date());
        bjArticle.setIsDelete(false);
        bjArticle.setPublicStatus(0);
        bjArticle.setArticleBody(baiJiaHaoUtils.getArticleBody(aritcleItem.getArticleUrl()));
        System.out.println(JSONObject.toJSONString(bjArticle));
        bjArticleMapper.insertSelective(bjArticle);
    }

    private String getAerticleBodyByUrl(String url){
      return   baiJiaHaoUtils.getArticleBody(url);
    }

    /**
     * 获取百家平台账号
     *
     * @return
     */
    private BjAccount getAccount() {
        BjAccount bjAccount = new BjAccount();
        bjAccount.setAppId("1663934735086891");
        bjAccount.setAppToken("30b639fc41348753f55b80a2980bb30e");
        bjAccount.setServerUrl("https://bjh.tomweb.xyz");
        bjAccount.setServerToken("sciacEBTfgvKpKGsbd2GcZIRnBjHgPPu");
        bjAccount.setServerEncodingAESKey("ZRpWPRmLImsqNIGtu62Kemmgv48GzS8h9ICK1PSsWRq");
        return bjAccount;
    }



}
