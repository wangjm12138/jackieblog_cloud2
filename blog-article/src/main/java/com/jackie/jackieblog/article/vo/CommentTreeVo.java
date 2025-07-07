package com.jackie.jackieblog.article.vo;

import com.jackie.jackieblog.article.dto.CommentTreeDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentTreeVo {

    private Long id;

    private Long articleId;

    private String username;

    private String content;

    private Date createDate;

    private Date updateDate;

    private List<CommentTreeVo> children;
}
