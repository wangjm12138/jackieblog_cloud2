package com.jackie.blog.article.entity.convertor;

import com.jackie.blog.article.vo.ArticleVo;
import com.jackie.blog.article.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author jackie wang
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ArticleConvertor {

    ArticleConvertor INSTANCE = Mappers.getMapper(ArticleConvertor.class);

    public ArticleVo mapToVo(Article request);

    /**
     * 转换为VO
     *
     * @param request
     * @return
     */
    public List<ArticleVo> mapToVo(List<Article> request);
}
