package com.jackie.blog.article.entity;

import lombok.Data;

/**
 * @version 1.0.0
 * @Author: Jackie Wang
 * @WechatID: star373629168
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.com
 * @Date: 2024/12/13 06:33
 */
@Data
public class CategoryDetails {
    private Integer id;

    private String name;

    private Integer CategoryId;

    private Integer amount;
}
