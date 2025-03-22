package com.jackie.jackieblog.article.service;

import com.jackie.jackieblog.article.dao.CategoryServiceMapper;
import com.jackie.jackieblog.article.dao.MenuServiceMapper;
import com.jackie.jackieblog.article.entity.Menu;
import com.jackie.jackieblog.article.vo.AllMenuVo;
import com.jackie.jackieblog.article.vo.MenuByIdVo;
import com.jackie.jackieblog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0.0
 * @Author: Jackie Wang
 * @WechatID: star373629168
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.com
 * @Date: 2024/12/15 11:08
 */
@Service
public class MenuService {

    @Autowired
    private MenuServiceMapper menuServiceMapper;
    public Result listMenu() {

        List<AllMenuVo> records = menuServiceMapper.listMenu();
        return Result.success(records);
    }

    public Result listAllCategoryByMenuId(Integer id) {
        List<MenuByIdVo> records = menuServiceMapper.listAllCategoryByMenuId(id);
        return Result.success(records);
    }
}
