package com.jackie.jackieblog.article.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jackie.jackieblog.article.dto.CommentNodeDTO;
import com.jackie.jackieblog.article.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentServiceMapper {

    IPage<CommentNodeDTO> listCommentByArticle(Page<CommentNodeDTO> page, @Param("articleId")Long articleId);

}
