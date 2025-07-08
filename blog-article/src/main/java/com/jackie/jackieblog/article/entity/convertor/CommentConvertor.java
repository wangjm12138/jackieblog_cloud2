package com.jackie.jackieblog.article.entity.convertor;

import com.jackie.jackieblog.article.dto.CommentTreeDTO;
import com.jackie.jackieblog.article.vo.CommentTreeVo;
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
