package com.jackie.jackieblog.article.entity.convertor;

import com.jackie.jackieblog.article.entity.Article;
import com.jackie.jackieblog.article.vo.ArticleVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
