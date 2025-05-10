package com.jackie.jackieblog.article.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ArticleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 浏览数
     */
    private Integer lookNum;

    /**
     * 收藏数
     */
    private Integer loveNum;

    /**
     * 置顶
     */
    private Integer weight;

    /**
     * 创建时间
     */
    private String createDate;

//    private CategoryVo category;

}
