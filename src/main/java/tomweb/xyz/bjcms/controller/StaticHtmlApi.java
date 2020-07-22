package tomweb.xyz.bjcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tomweb.xyz.bjcms.utils.StaticHtmlUtils;
import tomweb.xyz.bjcms.vo.BaseVo;

@RestController
public class StaticHtmlApi extends BaseApi {
    @Autowired
    StaticHtmlUtils staticHtmlUtils;

    @GetMapping("/adminApi/static/index")
    public BaseVo index() {
        String s
                = staticHtmlUtils.staticIndex();
        return success().setData(s);
    }

    @GetMapping("/adminApi/static/article")
    public BaseVo article(@RequestParam Integer id) {
        try {
            String s = staticHtmlUtils.staticArticle(id);
            return success().setData(s);
        } catch (Exception e) {
            return error(e.getMessage());
        }

    }
}
