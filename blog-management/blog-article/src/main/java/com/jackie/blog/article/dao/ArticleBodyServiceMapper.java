package com.jackie.blog.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackie.blog.article.entity.ArticleBody;

public interface ArticleBodyServiceMapper extends BaseMapper<ArticleBody> {
    ArticleBody searchArticleById(Long id);
}
