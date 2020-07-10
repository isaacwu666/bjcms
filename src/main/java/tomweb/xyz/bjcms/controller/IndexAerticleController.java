package tomweb.xyz.bjcms.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tomweb.xyz.bjcms.dto.BaseQuery;
import tomweb.xyz.bjcms.pojo.BjAccount;
import tomweb.xyz.bjcms.pojo.BjArticle;
import tomweb.xyz.bjcms.pojo.BjArticleExample;
import tomweb.xyz.bjcms.service.BjArticleService;
import tomweb.xyz.bjcms.utils.BaiJiaHaoUtils;
import tomweb.xyz.bjcms.vo.BjArticleListVo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexAerticleController {
    @Autowired
    BaiJiaHaoUtils baiJiaHaoUtils;
    @Autowired
    BjArticleService bjArticleService;

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index(BaseQuery baseQuery) {
        Page page = baseQuery.startPage();
        BjArticleExample bjArticleExample = new BjArticleExample();
        bjArticleExample.createCriteria().andStatusEqualTo("publish").andIsDeleteEqualTo(false)
        ;
        bjArticleExample.setOrderByClause("updated_at desc");
        List<BjArticle> bjArticles = bjArticleService.getBjArticleMapper().selectByExampleWithBLOBs(bjArticleExample);
        List<BjArticleListVo> bjArticleVos=new ArrayList<>();
        for (BjArticle bjArticle : bjArticles) {
            if (bjArticle.getUpdatedAt()==null){
                continue;
            }
            bjArticleVos.add(new BjArticleListVo(bjArticle));
        }

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("bjArticles",bjArticleVos);
        return modelAndView;
    }

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/index.html")
    public ModelAndView index2(BaseQuery baseQuery) {
        return index(baseQuery);
    }

    @GetMapping("/syncBj")
    @ResponseBody
    public Object syncBj() {
        BjAccount bjAccount = new BjAccount();
        bjAccount.setAppId("1663934735086891");
        bjAccount.setAppToken("30b639fc41348753f55b80a2980bb30e");
        bjAccount.setServerUrl("https://bjh.tomweb.xyz");
        bjAccount.setServerToken("sciacEBTfgvKpKGsbd2GcZIRnBjHgPPu");
        bjAccount.setServerEncodingAESKey("ZRpWPRmLImsqNIGtu62Kemmgv48GzS8h9ICK1PSsWRq");
        try {
            bjArticleService.syncBjArticle();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }
}

