package com.jackie.blog.article.entity;

import com.jackie.blog.datasource.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


/**
 * @Author:  Jackie Wang
 * @WechatId:  ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年02月19日 21:59
 */
@Getter
@Setter
public class Article extends BaseEntity {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 创建日期
     */
    private Timestamp createDate;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 浏览数
     */
    private Integer lookNum;

    /**
     * 收藏数
     */
    private Integer loveNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 置顶
     */
    private Integer weight;


    /**
     * 作者id
     */
    private Long authorId;

    /**
     *类别id
     */
    private Integer menuId;

    /**
     *类别id
     */
    private Long categoryId;

    /**
     *类别id
     */
    private Long categoryDetailsId;

}