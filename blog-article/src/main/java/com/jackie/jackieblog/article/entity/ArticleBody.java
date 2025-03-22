package com.jackie.jackieblog.article.entity;

import lombok.Data;

/**
 * @Author: Jackie Wang
 * @WechatID: ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年06月24日 12:36
 * @Description:
 */
@Data
public class ArticleBody {

    private Long id;

    private String contentHtml;

    private Long articleId;

}
