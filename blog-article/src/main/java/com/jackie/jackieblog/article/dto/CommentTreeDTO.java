package com.jackie.jackieblog.article.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentTreeDTO {

    private Long id;

    private Long articleId;

    private Long userId;

    private String content;

    private Date createDate;

    private Date updateDate;

    private int depth;

    private int treeRoot;

    private List<CommentTreeDTO> children;
}
