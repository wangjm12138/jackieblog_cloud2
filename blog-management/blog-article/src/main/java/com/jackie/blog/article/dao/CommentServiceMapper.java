package com.jackie.blog.article.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jackie.blog.article.dto.CommentNodeDTO;
import org.apache.ibatis.annotations.Param;

public interface CommentServiceMapper {

    IPage<CommentNodeDTO> listCommentByArticle(Page<CommentNodeDTO> page, @Param("articleId")Long articleId);

}
