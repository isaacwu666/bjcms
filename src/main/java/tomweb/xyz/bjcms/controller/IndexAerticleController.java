package tomweb.xyz.bjcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tomweb.xyz.bjcms.pojo.BjAccount;
import tomweb.xyz.bjcms.service.BjArticleService;
import tomweb.xyz.bjcms.utils.BaiJiaHaoUtils;

import java.net.URISyntaxException;

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
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/index.html")
    public ModelAndView index2() {
        return new ModelAndView("index");
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

