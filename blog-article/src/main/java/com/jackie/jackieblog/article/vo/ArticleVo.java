package com.jackie.jackieblog.article.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {

//    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    private String title;

    private String summary;

    private Integer commentNum;

    private Integer lookNum;

    private Integer loveNum;

    private Integer weight;
    /**
     * 创建时间
     */
    private String createDate;

//    private CategoryVo category;

}
