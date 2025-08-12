package com.jackie.blog.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jackie.blog.article.dto.CommentNodeDTO;
import com.jackie.blog.article.entity.Comment;
import com.jackie.blog.article.entity.CommentRelation;
import org.apache.ibatis.annotations.Param;

public interface CommentServiceMapper extends BaseMapper<Comment> {

    IPage<CommentNodeDTO> listCommentByArticle(Page<CommentNodeDTO> page, @Param("articleId")Long articleId);

    /**
     * 插入评论数并返回主键ID
     * @param comment 评论数
     * @return 影响的行数
     */
    int pushCommentByArticle(Comment comment);

    int pushCommentRelation(CommentRelation commentRelation);
}
