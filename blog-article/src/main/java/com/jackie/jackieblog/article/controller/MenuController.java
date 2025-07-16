package com.jackie.jackieblog.article.controller;

import com.jackie.jackieblog.article.service.MenuService;
import com.jackie.jackieblog.article.vo.MenuTreeVo;
import com.jackie.jackieblog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0.0
 * @Author: Jackie Wang
 * @WechatID: star373629168
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.com
 * @Date: 2024/12/13 20:55
 */
@RestController
@RequestMapping("api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @GetMapping("/all")
    public Result<List<MenuTreeVo>> listMenu() {
        return menuService.listMenu();
    }

    @GetMapping("/{id}/all")
    public Result<List<MenuTreeVo>>  listAllCategoryByMenuId(@PathVariable("id") Integer id) throws InterruptedException {
        return menuService.listAllCategoryByMenuId(id);
    }
}
