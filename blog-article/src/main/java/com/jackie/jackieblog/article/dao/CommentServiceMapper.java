package com.jackie.jackieblog.article.dao;

import com.jackie.jackieblog.article.dto.CommentNodeDTO;

import java.util.List;

public interface CommentServiceMapper {

    List<CommentNodeDTO> listCommentByArticle(Long articleId);

}
