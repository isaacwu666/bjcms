package tomweb.xyz.bjcms.controller;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tomweb.xyz.bjcms.dto.BaseQuery;
import tomweb.xyz.bjcms.dto.admin.AddCategory;
import tomweb.xyz.bjcms.pojo.BjArticle;
import tomweb.xyz.bjcms.pojo.BjArticleExample;
import tomweb.xyz.bjcms.pojo.Category;
import tomweb.xyz.bjcms.pojo.CategoryExample;
import tomweb.xyz.bjcms.service.BjArticleService;
import tomweb.xyz.bjcms.service.CategoryService;
import tomweb.xyz.bjcms.utils.BeanVlidate;
import tomweb.xyz.bjcms.vo.BaseVo;

import java.util.Date;
import java.util.List;

@RestController
public class CategoryApi extends BaseApi {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BjArticleService bjArticleService;

    @GetMapping("/adminApi/category")
    @ApiOperation("查询所有类目信息")
    public BaseVo<List<Category>> query(BaseQuery baseQuery, String categoryName) {
        Page page = baseQuery.startPage();
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria().andIsDeleteEqualTo(false);
        if (!StringUtils.isEmpty(categoryName)) {
            criteria.andCategoryNameLike("%" + categoryName.trim() + "%");
        }
        example.setOrderByClause("order_no asc");
        List list = categoryService.getCategoryMapper().selectByExample(example);
        return success(page);
    }

    @PostMapping("/adminApi/category")
    @ApiOperation("写入类目信息")
    public BaseVo<List<Category>> insert(@RequestBody AddCategory category) {
        BeanVlidate.validate(category);
        CategoryExample example = new CategoryExample();
        example.createCriteria().andCategoryNameLike(category.getCategoryName().trim()).andIsDeleteEqualTo(false);
        example.or().andOrderNoEqualTo(category.getOrderNo()).andIsDeleteEqualTo(false);
        long count = categoryService.getCategoryMapper().countByExample(example);
        if (count > 0) {
            return error("名字或者是排序号冲突");
        }
        categoryService.getCategoryMapper().insertSelective(category.toCategory());

        return success();
    }

    @PutMapping("/adminApi/category")
    @ApiOperation("更新类目信息")
    public BaseVo<Category> update(@RequestBody Category category) {
        if (category.getCategoryId() == null) {
            return error("主键不能为空");
        }
        CategoryExample example = new CategoryExample();
        if (!StringUtils.isEmpty(category.getCategoryName())) {
            example.createCriteria().andCategoryNameLike(category.getCategoryName().trim()).andIsDeleteEqualTo(false)
                    .andCategoryIdNotEqualTo(category.getCategoryId());
        }
        if (category.getOrderNo() != null) {
            example.or().andOrderNoEqualTo(category.getOrderNo()).andIsDeleteEqualTo(false)
                    .andOrderNoNotEqualTo(category.getCategoryId());
        }

        long count = categoryService.getCategoryMapper().countByExample(example);
        if (count > 0) {
            return error("名字或者是排序号冲突");
        }
        categoryService.getCategoryMapper().updateByPrimaryKeySelective(category);
        return success(categoryService.getCategoryMapper().selectByPrimaryKey(category.getCategoryId()));
    }


    @DeleteMapping("/adminApi/category/{categoryId}")
    @ApiOperation("删除类目信息")
    public BaseVo delete(@PathVariable("categoryId") Integer categoryId) {
        //还有文章不能删除
        BjArticleExample example = new BjArticleExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId)
                .andIsDeleteEqualTo(false);

        long count = bjArticleService.getBjArticleMapper().countByExample(example);
        if (count > 0) {
            return error("还有" + count + "篇文章关联，不能删除");
        }
        Category toUpdate = new Category();
        toUpdate.setCategoryId(categoryId)
                .setIsDelete(true)
                .setUpdatedOn(new Date());
        categoryService.getCategoryMapper().updateByPrimaryKeySelective(toUpdate);

        return success();

    }

}
