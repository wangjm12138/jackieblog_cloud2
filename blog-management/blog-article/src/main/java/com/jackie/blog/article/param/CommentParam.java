package com.jackie.blog.article.param;

import lombok.Data;

@Data
public class CommentParam {

    private Long articleId;
    private Long userId;
    private String nickname;
    private String content;
    private String createDate;
}
