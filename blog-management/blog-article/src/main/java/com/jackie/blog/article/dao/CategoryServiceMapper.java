package com.jackie.blog.article.dao;

import com.jackie.blog.article.entity.Category;
import com.jackie.blog.article.entity.CategoryDetails;

import java.util.List;

public interface CategoryServiceMapper {

    List<Category> listCategoryByMenuId(Integer MenuId);

    Category listCategoryById(Integer CategoryId);


    CategoryDetails listCategoryDetailsById(Integer CategoryDetailsId);

    List<CategoryDetails> listDetailsByCategoryId(Integer CategoryId);
}
