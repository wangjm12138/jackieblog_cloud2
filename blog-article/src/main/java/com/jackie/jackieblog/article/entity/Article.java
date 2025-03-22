package com.jackie.jackieblog.article.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;


/**
 * @Author:  Jackie Wang
 * @WechatId:  ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年02月19日 21:59
 */
@Data
public class Article {

//    public static final int Article_TOP = 1;
//
//    public static final int Article_Common = 0;

    private Long id;

    private String title;

    private Timestamp createDate;

    private String summary;

    private Integer lookNum;

    private Integer loveNum;

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