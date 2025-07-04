package com.jackie.jackieblog.article.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentNodeDTO {

    private Long id;

    private Long articleId;

    private Long userId;

    private String content;

    private Date createTime;

    private Date updateTime;

    private int depth;

    private int tree_root;
}
