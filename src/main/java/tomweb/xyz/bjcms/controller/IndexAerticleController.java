package tomweb.xyz.bjcms.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tomweb.xyz.bjcms.dao.ArticleCoverPhotoMapper;
import tomweb.xyz.bjcms.dto.BaseQuery;
import tomweb.xyz.bjcms.pojo.ArticleCoverPhoto;
import tomweb.xyz.bjcms.pojo.BjAccount;
import tomweb.xyz.bjcms.pojo.BjArticle;
import tomweb.xyz.bjcms.pojo.BjArticleExample;
import tomweb.xyz.bjcms.service.ArticleCoverPhotoService;
import tomweb.xyz.bjcms.service.BjArticleService;
import tomweb.xyz.bjcms.utils.BaiJiaHaoUtils;
import tomweb.xyz.bjcms.vo.BjArticleDetail;
import tomweb.xyz.bjcms.vo.BjArticleListVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Controller
public class IndexAerticleController {
    @Autowired
    BaiJiaHaoUtils baiJiaHaoUtils;
    @Autowired
    BjArticleService bjArticleService;
    @Autowired
    ArticleCoverPhotoService articleCoverPhotoService;
    @Value("${spring.profiles.active}")
    String active;

    private boolean isProd() {
        return Objects.equals("prod", active);
    }

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index(BaseQuery baseQuery) {
        Page page = baseQuery.startPage();
        BjArticleExample bjArticleExample = new BjArticleExample();
        bjArticleExample.createCriteria().andIsDeleteEqualTo(false)
                .andPublicStatusEqualTo(1);
        ;
        bjArticleExample.setOrderByClause("updated_at desc");
        List<BjArticle> bjArticles = bjArticleService.getBjArticleMapper().selectByExampleWithBLOBs(bjArticleExample);
        Set<Integer> articleIs = new HashSet<>();
        List<BjArticleListVo> bjArticleVos = new ArrayList<>();
        for (BjArticle bjArticle : bjArticles) {
            if (bjArticle.getUpdatedAt() == null) {
                if (bjArticle.getUpdateOn() != null) {
                    bjArticle.setUpdatedAt(bjArticle.getUpdateOn().getTime() / 1000);
                } else {
                    bjArticle.setUpdatedAt(bjArticle.getCreatedOn().getTime() / 1000);
                }
//                continue;
            }
            articleIs.add(bjArticle.getId());
            if (bjArticle.getArticleBody() != null) {
                bjArticle.setArticleBody(baiJiaHaoUtils.splitAndFilterString(bjArticle.getArticleBody(), bjArticle.getArticleBody().length()));
            }

            bjArticleVos.add(new BjArticleListVo(bjArticle));
        }
        Map<Integer, List<ArticleCoverPhoto>> coverMap = articleCoverPhotoService.queryArticleCoverPhotoMap(articleIs);
        for (BjArticleListVo bjArticleVo : bjArticleVos) {
            bjArticleVo.setCoverPhotos(coverMap.get(bjArticleVo.getId()));
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("prod", isProd());
        modelAndView.addObject("keyWords",null);
        modelAndView.addObject("description",null);

        modelAndView.addObject("bjArticles", bjArticleVos);
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

    @RequestMapping("/a/{id}")
    public String detail(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        BjArticle bjArticle = bjArticleService.getBjArticleMapper().selectByPrimaryKey(id);
        if (bjArticle == null || bjArticle.getPublicStatus() != 1) {
            return "/error/404";
        }
        BjArticleDetail bjArticleDetail = new BjArticleDetail();
        bjArticleDetail.setCovers(articleCoverPhotoService.selectByAricleIds(id));
        BeanUtils.copyProperties(bjArticle, bjArticleDetail);
        model.addAttribute("bjArticleDetail", bjArticleDetail);
        model.addAttribute("prod", isProd());
        return "article";
    }

    /**
     * 网站robots.txt
     *
     * @param response
     */
    @RequestMapping("/robots.txt")
    public void robots(HttpServletResponse response) {
        //text/plain; charset=UTF-8
        response.setContentType("text/plain; charset=UTF-8");
        String txt = "User-agent: *\n" +
                "Disallow: \n" +
                "Disallow: /admin/\n" +
                "Disallow: /adminApi/";

        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(txt.getBytes("UTF-8"));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @RequestMapping("/sitemap.xml")
    public Object sitemap() {


        return null;
    }

    @RequestMapping("/sitemap.html")
    public Object sitemapHtml() {


        return null;
    }
}

