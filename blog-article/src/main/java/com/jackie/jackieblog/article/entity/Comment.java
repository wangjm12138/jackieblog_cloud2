package com.jackie.jackieblog.article.entity;


import com.jackie.jackieblog.datasource.domain.entity.BaseEntity;
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
