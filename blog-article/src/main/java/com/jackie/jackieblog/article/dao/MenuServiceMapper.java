package com.jackie.jackieblog.article.dao;

import com.jackie.jackieblog.article.dto.MenuDTO;

import java.util.List;

/**
 * @version 1.0.0
 * @Author: Jackie Wang
 * @WechatID: star373629168
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.com
 * @Date: 2024/12/15 11:08
 */
public interface MenuServiceMapper {
    List<MenuDTO> listMenu();

    List<MenuDTO> listAllCategoryByMenuId(Integer id);
}
