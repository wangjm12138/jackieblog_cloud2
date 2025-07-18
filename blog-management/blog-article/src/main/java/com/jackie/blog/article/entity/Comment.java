package com.jackie.blog.article.entity;


import com.jackie.blog.datasource.domain.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Comment extends BaseEntity {

    private Long articleId;

    private Long userId;

    private String content;

    private Date createTime;

    private Date updateTime;
}
