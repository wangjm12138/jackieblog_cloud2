package com.jackie.blog.article.controller;

import com.jackie.blog.article.service.CategoryService;
import com.jackie.blog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0.0
 * @Author: Jackie Wang
 * @WechatID: star373629168
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.com
 * @Date: 2024/12/15 11:10
 */
@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}/all")
    public Result listArticleByCategoryId(@PathVariable("id") Integer categoryId) {
       return categoryService.listArticleByCategoryId(categoryId);
    }

//    @GetMapping("/")
//    public Result listArticleByCategoryId(@PathVariable("id") Integer menuId) {
//        return categoryService.listCategoryByMenuId(categoryId);
//    }
//    @GetMapping("/")
//    public Result listCategoryDetailsById(@RequestParam Integer categoryDetailsId) {
//
//        return categoryService.listCategoryById(categoryDetailsId);
//    }
}
