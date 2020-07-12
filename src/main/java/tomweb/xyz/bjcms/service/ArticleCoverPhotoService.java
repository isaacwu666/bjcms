package tomweb.xyz.bjcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tomweb.xyz.bjcms.dao.ArticleCoverPhotoMapper;
import tomweb.xyz.bjcms.pojo.ArticleCoverPhoto;
import tomweb.xyz.bjcms.pojo.ArticleCoverPhotoExample;

import java.util.*;

@Service
public class ArticleCoverPhotoService {
    @Autowired
    ArticleCoverPhotoMapper articleCoverPhotoMapper;

    public Map<Integer, List<ArticleCoverPhoto>> queryArticleCoverPhotoMap(Collection articleIds) {
        if (CollectionUtils.isEmpty(articleIds)) {
            return new HashMap<>();
        }

        List<ArticleCoverPhoto> list = articleCoverPhotoMapper.selectByAricleIds(new ArrayList(articleIds));
        Map<Integer, List<ArticleCoverPhoto>> map = new HashMap<>();
        for (ArticleCoverPhoto articleCoverPhoto : list) {
            Integer id = articleCoverPhoto.getArticleId();
            List<ArticleCoverPhoto> photos = map.get(id);
            if (photos == null) {
                photos = new ArrayList<>();
                photos.add(articleCoverPhoto);
                map.put(id, photos);
            } else {
                photos.add(articleCoverPhoto);
            }
        }
        return map;
    }

    public List<ArticleCoverPhoto> selectByAricleIds(Integer articleId) {
        if (articleId == null) {
            return new ArrayList<>();
        }
        ArticleCoverPhotoExample example = new ArticleCoverPhotoExample();
        example.createCriteria().andArticleIdEqualTo(articleId);
        example.setOrderByClause(" order_no asc");
        return articleCoverPhotoMapper.selectByExample(example);
    }
}
