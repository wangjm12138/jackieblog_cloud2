package com.jackie.blog.article.entity.convertor;

import com.jackie.blog.article.vo.MenuTreeVo;
import com.jackie.blog.article.dto.MenuTreeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author jackie wang
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MenuConvertor {

    MenuConvertor INSTANCE = Mappers.getMapper(MenuConvertor.class);

    public MenuTreeVo mapToVo(MenuTreeDTO request);

    /**
     * 转换为VO
     *
     * @param request
     * @return
     */
    public List<MenuTreeVo> mapToVo(List<MenuTreeDTO> request);
}
