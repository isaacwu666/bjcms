package tomweb.xyz.bjcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomweb.xyz.bjcms.dao.CategoryMapper;
import tomweb.xyz.bjcms.pojo.Category;
import tomweb.xyz.bjcms.pojo.CategoryExample;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public CategoryMapper getCategoryMapper() {
        return categoryMapper;
    }

    /**
     * 根据id查询类
     *
     * @param ids
     * @return
     */
    public Map<Integer, Category> queryCategoryMapByIds(Collection<Integer> ids) {

        CategoryExample example = new CategoryExample();
        example.createCriteria().andCategoryIdIn(new ArrayList<>(ids))
                .andIsDeleteEqualTo(false);
        example.setOrderByClause("order_no asc");
        List<Category> categories = categoryMapper.selectByExample(example);
        Map<Integer, Category> haseMap = new HashMap<>();
        categories.forEach(i -> {
            haseMap.put(i.getCategoryId(), i);
        });
        return haseMap;
    }


    public List<Category> getIndexList() {
        CategoryExample example = new CategoryExample();
        example.createCriteria()
                .andIsDeleteEqualTo(false);
        example.setOrderByClause("order_no asc");
        example.setDistinct(true);

        List<Category> categories = categoryMapper.selectByExample(example);
        return categories;
    }

    public Category getById(Integer categoryId) {
        if (categoryId == null) {
            return null;
        }
        return categoryMapper.selectByPrimaryKey(categoryId);
    }
}
