package com.jackie.jackieblog.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackie.jackieblog.article.entity.ArticleBody;

public interface ArticleBodyServiceMapper extends BaseMapper<ArticleBody> {
    ArticleBody searchArticleById(Long id);
}
