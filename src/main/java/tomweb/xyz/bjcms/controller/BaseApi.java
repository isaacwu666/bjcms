package tomweb.xyz.bjcms.controller;

import com.github.pagehelper.Page;
import tomweb.xyz.bjcms.vo.BaseVo;
import tomweb.xyz.bjcms.vo.BjArticleListVo;

import java.util.List;

public class BaseApi {

    public BaseVo success() {
        BaseVo baseVo = new BaseVo();
        baseVo.setMsg("SUCCESS");
        baseVo.setCode("SUCCESS");
        return baseVo;
    }

    public BaseVo success( Object data) {
        BaseVo baseVo = new BaseVo();
        baseVo.setMsg("SUCCESS");
        baseVo.setCode("SUCCESS");
        baseVo.setData(data);
        return baseVo;
    }
    public BaseVo success(Page page) {
        BaseVo baseVo = new BaseVo();
        baseVo.setMsg("SUCCESS");
        baseVo.setCode("SUCCESS");
        baseVo.setData(page.getResult());
        baseVo.setPage(page.getPageNum());
        baseVo.setSize(page.getPageSize());
        baseVo.setTotal(page.getTotal());
        baseVo.setTotalPage(page.getPages());

        return baseVo;
    }

    public BaseVo error() {
        BaseVo baseVo = new BaseVo();
        baseVo.setCode("ERROR");
        baseVo.setMsg("ERROR");
        return baseVo;
    }

}
