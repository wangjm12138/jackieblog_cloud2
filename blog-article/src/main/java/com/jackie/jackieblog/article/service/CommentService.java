package com.jackie.jackieblog.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jackie.jackieblog.article.dao.CommentServiceMapper;
import com.jackie.jackieblog.article.dto.CommentNodeDTO;
import com.jackie.jackieblog.article.dto.CommentTreeDTO;
import com.jackie.jackieblog.article.entity.Article;
import com.jackie.jackieblog.article.entity.convertor.ArticleConvertor;
import com.jackie.jackieblog.article.entity.convertor.CommentConvertor;
import com.jackie.jackieblog.article.vo.CommentTreeVo;
import com.jackie.jackieblog.base.response.PageResponse;
import com.jackie.jackieblog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentServiceMapper commentServiceMapper;

    private CommentTreeDTO convertToDTO(CommentNodeDTO commentNodeDTO) {
        CommentTreeDTO commentTreeDTO = new CommentTreeDTO();
        commentTreeDTO.setId(commentNodeDTO.getId());
        commentTreeDTO.setContent(commentNodeDTO.getContent());
        commentTreeDTO.setArticleId(commentTreeDTO.getArticleId());
        commentTreeDTO.setCreateDate(commentNodeDTO.getCreateDate());
        commentTreeDTO.setUpdateDate(commentNodeDTO.getUpdateDate());
        commentTreeDTO.setUserId(commentTreeDTO.getUserId());
        commentTreeDTO.setDepth(commentNodeDTO.getDepth());
        commentTreeDTO.setTreeRoot(commentNodeDTO.getTreeRoot());
        return commentTreeDTO;
    }

    private List<CommentTreeDTO> rootCommentTree(List<CommentNodeDTO> commentNodeDTOList) {

        Map<Long, CommentTreeDTO> dtoMap = commentNodeDTOList.stream().map(this::convertToDTO).collect(Collectors.toMap(CommentTreeDTO::getId, Function.identity()));
        List<CommentTreeDTO> rootComments = new ArrayList<>();

        for(CommentTreeDTO commentTreeDTO : dtoMap.values()){
            if(commentTreeDTO.getTreeRoot() == commentTreeDTO.getId()) {
                rootComments.add(commentTreeDTO);
            } else {
                CommentTreeDTO parent =  dtoMap.get((long)commentTreeDTO.getTreeRoot());
                if(parent != null) {
                    if(parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(commentTreeDTO);
                }
            }
        }
        return rootComments;
    }

    public PageResponse<CommentTreeVo> listCommentByArticle(long articleId, int pageNum, int limit){
        Page<CommentNodeDTO> page = new Page<>(pageNum, limit);
        IPage<CommentNodeDTO> commentNodeDTOIPage = commentServiceMapper.listCommentByArticle(
                page,
                articleId);
        List<CommentNodeDTO> records = commentNodeDTOIPage.getRecords();
        List<CommentTreeDTO> rootCommentTree = this.rootCommentTree(records);

        PageResponse<CommentTreeDTO> commentTreeDTOPageResponse;
        if (commentNodeDTOIPage.getCurrent() == commentNodeDTOIPage.getPages()) {
            commentTreeDTOPageResponse = PageResponse.of(rootCommentTree, commentNodeDTOIPage.getTotal(), commentNodeDTOIPage.getSize(), commentNodeDTOIPage.getCurrent(), commentNodeDTOIPage.getPages(), true);
        } else {
            commentTreeDTOPageResponse = PageResponse.of(rootCommentTree, commentNodeDTOIPage.getTotal(), commentNodeDTOIPage.getSize(), commentNodeDTOIPage.getCurrent(), commentNodeDTOIPage.getPages(), false);
        }
        return PageResponse.of(CommentConvertor.INSTANCE.mapToVo(commentTreeDTOPageResponse.getDatas()),commentTreeDTOPageResponse.getTotal(), commentTreeDTOPageResponse.getPageSize(), commentTreeDTOPageResponse.getCurrentPage(), commentTreeDTOPageResponse.getTotalPage(), commentTreeDTOPageResponse.isLast());
    }
}
