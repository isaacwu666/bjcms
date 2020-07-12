package tomweb.xyz.bjcms.controller;

import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tomweb.xyz.bjcms.dao.ArticleCoverPhotoMapper;
import tomweb.xyz.bjcms.dto.BaseQuery;
import tomweb.xyz.bjcms.pojo.ArticleCoverPhoto;
import tomweb.xyz.bjcms.pojo.ArticleCoverPhotoExample;
import tomweb.xyz.bjcms.pojo.BjArticle;
import tomweb.xyz.bjcms.pojo.BjArticleExample;
import tomweb.xyz.bjcms.service.BjArticleService;
import tomweb.xyz.bjcms.vo.BaseVo;
import tomweb.xyz.bjcms.vo.BjArticleDetail;
import tomweb.xyz.bjcms.vo.BjArticleListVo;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
//@RequestMapping("admin")
@Api
@RestController
public class ArticleApi extends BaseApi {
    @Value("${base.upload.fail.clue.path}")
    String filePath;
    @Value("${server.image.root}")
    String serverImageRoot;

    @Autowired
    BjArticleService bjArticleService;
    @Autowired
    ArticleCoverPhotoMapper articleCoverPhotoMapper;

    @GetMapping("adminApi/aritcleList")
    public BaseVo<List<BjArticleListVo>> queryList(BaseQuery baseQuery) {
        BjArticleExample example = new BjArticleExample();
        example.createCriteria().andIsDeleteEqualTo(false);
        Page page = baseQuery.startPage();
        bjArticleService.getBjArticleMapper().selectByExampleWithBLOBs(example);
        return success(page);
    }


    @GetMapping("adminApi/aritcle")
    public BaseVo<BjArticleDetail> queryList(@RequestParam Integer id) {

        BjArticle bjArticle = bjArticleService.getBjArticleMapper().selectByPrimaryKey(id);
        if (bjArticle == null) {
            return error();
        }
        BjArticleDetail bjArticleDetail = new BjArticleDetail();
        BeanUtils.copyProperties(bjArticle, bjArticleDetail);
        ArticleCoverPhotoExample example = new ArticleCoverPhotoExample();
        example.createCriteria().andArticleIdEqualTo(id);
        example.setOrderByClause(" order_no asc");
        List<ArticleCoverPhoto> photos = articleCoverPhotoMapper.selectByExample(example);
        bjArticleDetail.setCovers(photos);
        return success(bjArticleDetail);
    }

    /**
     * 更新文章
     *
     * @param bjArticle
     * @return
     */
    @PutMapping("adminApi/aritcle")
    public BaseVo<BjArticleDetail> update(@RequestBody BjArticleDetail bjArticle) {

        if (bjArticle.getId() == null) {
            return error();
        }
        Integer id = bjArticle.getId();
        List<ArticleCoverPhoto> coverPhotos = bjArticle.getCovers();

        if (!CollectionUtils.isEmpty(coverPhotos)) {
            if (  coverPhotos.get(0)!=null){

                ArticleCoverPhotoExample example = new ArticleCoverPhotoExample();
                example.createCriteria().andArticleIdEqualTo(bjArticle.getId());
                articleCoverPhotoMapper.deleteByExample(example);

                for (ArticleCoverPhoto coverPhoto : coverPhotos) {
                    if (coverPhoto==null){
                        continue;
                    }
                    coverPhoto.setArticleId(id);
                    if (coverPhoto.getPhotoUrl()==null){
                        continue;
                    }
                    articleCoverPhotoMapper.insert(coverPhoto);
                }
            }

        }

        bjArticleService.getBjArticleMapper().updateByPrimaryKeyWithBLOBs(bjArticle);
        return success();
    }

    @ApiOperation("上传文件")
    @PostMapping("adminApi/file")

    public BaseVo uploadFile(@RequestParam("fileName") MultipartFile file, HttpServletRequest request) {
        String string = UUID.randomUUID().toString().replaceAll("-", "");
        String a2 = file.getOriginalFilename();
        String[] s = a2.split("\\.");
        String newPath = filePath + "/" + string;
        if (s.length > 0) {
            newPath = newPath + "." + s[s.length - 1];
        }

        File nFile = new File(newPath);

        try {

            String url = serverImageRoot + "/upload/" + string;
            if (s.length > 0) {
                url = url + "." + s[s.length - 1];
            }
            file.transferTo(nFile);
            return success(url);
        } catch (IOException e) {
            e.printStackTrace();
            return error();
        }

    }


}
