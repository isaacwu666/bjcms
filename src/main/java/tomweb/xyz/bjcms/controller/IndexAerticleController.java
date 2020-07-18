package tomweb.xyz.bjcms.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tomweb.xyz.bjcms.dao.ArticleCoverPhotoMapper;
import tomweb.xyz.bjcms.dto.BaseQuery;
import tomweb.xyz.bjcms.pojo.*;
import tomweb.xyz.bjcms.service.ArticleCoverPhotoService;
import tomweb.xyz.bjcms.service.BjArticleService;
import tomweb.xyz.bjcms.service.CategoryService;
import tomweb.xyz.bjcms.service.ConfigService;
import tomweb.xyz.bjcms.utils.BaiJiaHaoUtils;
import tomweb.xyz.bjcms.vo.BjArticleDetail;
import tomweb.xyz.bjcms.vo.BjArticleListVo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static tomweb.xyz.bjcms.config.StaticValue.Config.*;

@Controller
public class IndexAerticleController {


    @Autowired
    BaiJiaHaoUtils baiJiaHaoUtils;
    @Autowired
    BjArticleService bjArticleService;
    @Autowired
    ArticleCoverPhotoService articleCoverPhotoService;
    @Autowired
    CategoryService categoryService;

    @Value("${spring.profiles.active}")
    String active;
    @Autowired
    ConfigService configService;

    private boolean isProd() {
        return Objects.equals("prod", active);
    }

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index(BaseQuery baseQuery, HttpServletRequest request) {
        Page page = baseQuery.startPage();
        BjArticleExample bjArticleExample = new BjArticleExample();
        bjArticleExample.createCriteria().andIsDeleteEqualTo(false)
                .andPublicStatusEqualTo(1);
        ;
        bjArticleExample.setOrderByClause("updated_at desc");
        String serverName = request.getServerName();

        List<BjArticle> bjArticles = bjArticleService.getBjArticleMapper().selectByExampleWithBLOBs(bjArticleExample);
        List<BjArticleListVo> bjArticleVos = bjArticleService.toBjAticleListVo(bjArticles);

        Map<String,String> configMap =configService.queryKeyValueMapByConfigType(CONFIG_TYPE_INDEX,CONFIG_TYPE_SITE);
        List<Category> categoryList = categoryService.getIndexList();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("prod", isProd());
        modelAndView.addObject("serverName", serverName);
        modelAndView.addObject("keyWords", null);
        modelAndView.addObject("description", null);
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("bjArticles", bjArticleVos);
        modelAndView.addAllObjects(configMap);
        return modelAndView;
    }

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/index.html")
    public ModelAndView index2(BaseQuery baseQuery, HttpServletRequest request) {
        return index(baseQuery, request);
    }

    @RequestMapping("/a/{id}")
    public String detail(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

        BjArticle bjArticle = bjArticleService.getBjArticleMapper().selectByPrimaryKey(id);
        if (bjArticle == null || bjArticle.getPublicStatus() != 1) {
            return "/error/404";
        }
        BjArticleDetail bjArticleDetail = new BjArticleDetail();
        bjArticleDetail.setCovers(articleCoverPhotoService.selectByAricleIds(id));
        String serverName = request.getServerName();
        BeanUtils.copyProperties(bjArticle, bjArticleDetail);
        Category category = categoryService.getById(bjArticle.getCategoryId());
        if (category!=null){
            bjArticleDetail.setCategoryName(category.getCategoryName());
        }
        Map<String,String> configMap =configService.queryKeyValueMapByConfigType(CONFIG_TYPE_ARTICLE,CONFIG_TYPE_SITE);
        model.addAllAttributes(configMap);
        model.addAttribute("bjArticleDetail", bjArticleDetail);
        model.addAttribute("prod", isProd());
        model.addAttribute("serverName", serverName);
        model.addAttribute("keyWords", bjArticle.getKeywords());
        model.addAttribute("description", bjArticle.getDescription());


        return "article";
    }
//    @Autowired
//    CategoryService categoryService;

    @GetMapping("/viewTmpArticle")
    public String viewTmpArticle(Model model, Integer id) {
        BjArticle bjArticle = bjArticleService.getBjArticleMapper().selectByPrimaryKey(id);
        if (bjArticle == null || bjArticle.getPublicStatus() != 1) {
            return "viewTmpArticle";
        }
        BjArticleDetail bjArticleDetail = new BjArticleDetail();
        bjArticleDetail.setCovers(articleCoverPhotoService.selectByAricleIds(id));
        BeanUtils.copyProperties(bjArticle, bjArticleDetail);
        model.addAttribute("bjArticleDetail", bjArticleDetail);
        model.addAttribute("prod", isProd());
        model.addAttribute("keyWords", bjArticle.getKeywords());
        model.addAttribute("description", bjArticle.getDescription());
        return "viewTmpArticle";
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
    @RequestMapping("/c")
    public String categotry(Integer categoryId,Model model){
        Map<String,String> configMap =configService.queryKeyValueMapByConfigType(CONFIG_TYPE_ARTICLE,CONFIG_TYPE_SITE);
        model.addAllAttributes(configMap);

        return "category";
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

