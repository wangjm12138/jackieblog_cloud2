package com.jackie.blog.article.entity;

import lombok.Data;

@Data
public class CommentRelation {
    private Long ancestor;

    private Long descendant;

    private Integer depth;
}
