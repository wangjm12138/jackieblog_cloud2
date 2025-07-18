package com.jackie.blog.article.entity.convertor;

import com.jackie.blog.article.vo.CommentTreeVo;
import com.jackie.blog.article.dto.CommentTreeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author jackie wang
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CommentConvertor {

    CommentConvertor INSTANCE = Mappers.getMapper(CommentConvertor.class);

    public CommentTreeVo mapToVo(CommentTreeDTO request);

    /**
     * 转换为VO
     *
     * @param request
     * @return
     */
    public List<CommentTreeVo> mapToVo(List<CommentTreeDTO> request);
}
