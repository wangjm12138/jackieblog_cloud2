package com.jackie.jackieblog.article.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackie.jackieblog.article.entity.Tag;

import java.util.List;

public interface TagServiceMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询最热的标签 前n条
     * @param limit
     * @return
     */
    List<Long> findHotsTagIds(int limit);

    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
