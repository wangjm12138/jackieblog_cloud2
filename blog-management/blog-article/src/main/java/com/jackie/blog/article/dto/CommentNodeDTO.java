package com.jackie.blog.article.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentNodeDTO {

    private Long id;

    private Long articleId;

    private Long userId;

    private String content;

    private Date createDate;

    private Date updateDate;

    private int depth;

    private int treeRoot;
}
