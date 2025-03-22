package com.jackie.jackieblog.article.service;

import com.jackie.jackieblog.article.dao.CategoryServiceMapper;
import com.jackie.jackieblog.article.entity.Category;
import com.jackie.jackieblog.article.entity.CategoryDetails;
import com.jackie.jackieblog.article.vo.CategoryVo;
import com.jackie.jackieblog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jackie Wang
 * @WechatID: ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年06月23日 18:02
 * @Description:
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryServiceMapper categoryServiceMapper;

    public Result listArticleByCategoryId(Integer categoryId) {

        Category record = categoryServiceMapper.listCategoryById(categoryId);
        System.out.println(record);
        List<CategoryVo> categoryVoList = CreateCategoryVoList(record);

        return Result.success(categoryVoList);
    }

    private List<CategoryVo> CreateCategoryVoList(Category record) {

        List<CategoryVo> categoryVoList = new ArrayList<>();

        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setId(record.getId());
        categoryVo.setName(record.getName());
        categoryVo.setAmount(record.getAmount());
        categoryVo.setMenuId(record.getMenuId());
        List<CategoryDetails> categoryDetailsList = categoryServiceMapper.listDetailsByCategoryId(record.getId());

        categoryVo.setDetails(categoryDetailsList);

        categoryVoList.add(categoryVo);
        return categoryVoList;
    }


    public Result listCategoryById(Integer categoryId) {
        System.out.println(categoryId);
        Category record = categoryServiceMapper.listCategoryById(categoryId);
        return Result.success(record);
    }

    public Result listCategoryDetailsById(Integer categoryDetailsId) {
        CategoryDetails record = categoryServiceMapper.listCategoryDetailsById(categoryDetailsId);
        return Result.success(record);
    }
}
