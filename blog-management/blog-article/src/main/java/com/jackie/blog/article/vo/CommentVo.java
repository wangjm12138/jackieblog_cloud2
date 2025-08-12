package com.jackie.blog.article.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVo  {
    private Long id;

    private Long articleId;

    private String nickname;

    private String avatar;

    private int gender;

    private String content;

    private Date createDate;

    private Date updateDate;
}
