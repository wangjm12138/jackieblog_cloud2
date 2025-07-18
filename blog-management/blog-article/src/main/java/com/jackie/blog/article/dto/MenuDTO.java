package com.jackie.blog.article.dto;

import lombok.Data;

/**
 * @version 1.0.0
 * @Author: Jackie Wang
 * @WechatID: star373629168
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.com
 * @Date: 2024/12/26 20:44
 */
@Data
public class MenuDTO {

    private Integer menuId;

    private String menuName;

    private Integer menuAmount;

    private String menuIcon;

    private Integer categoryId;

    private String categoryName;

    private Integer categoryAmount;

    private String categoryIcon;

    private Integer categoryDetailsId;

    private String categoryDetailsName;

    private Integer categoryDetailsAmount;

    private String categoryDetailsIcon;
}
