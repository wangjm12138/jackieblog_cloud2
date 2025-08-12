package com.jackie.blog.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jackie.blog.api.user.request.UserQueryRequest;
import com.jackie.blog.api.user.response.UserOperatorResponse;
import com.jackie.blog.api.user.response.data.UserInfo;
import com.jackie.blog.api.user.service.UserFacadeService;
import com.jackie.blog.article.entity.Comment;
import com.jackie.blog.article.entity.CommentRelation;
import com.jackie.blog.article.param.CommentParam;
import com.jackie.blog.article.vo.CommentTreeVo;
import com.jackie.blog.article.dao.CommentServiceMapper;
import com.jackie.blog.article.dto.CommentNodeDTO;
import com.jackie.blog.article.dto.CommentTreeDTO;
import com.jackie.blog.article.entity.convertor.CommentConvertor;
import com.jackie.blog.article.vo.CommentVo;
import com.jackie.blog.base.response.PageResponse;
import com.jackie.blog.base.vo.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.jackie.blog.article.exception.CommentErrorCode.COMMENT_INSERT_COMMENT_ERROR;
import static com.jackie.blog.article.exception.CommentErrorCode.COMMENT_USER_NOT_EXIST;

@Service
public class CommentService {

    @DubboReference(version = "1.0.0")
    private UserFacadeService userFacadeService;

    @Autowired
    private CommentServiceMapper commentServiceMapper;

    private CommentTreeDTO convertToDTO(CommentNodeDTO commentNodeDTO) {
        CommentTreeDTO commentTreeDTO = new CommentTreeDTO();
        UserQueryRequest userQueryRequest = new UserQueryRequest(commentNodeDTO.getUserId());
        UserOperatorResponse userOperatorResponse = userFacadeService.query(userQueryRequest);
        UserInfo userInfo = userOperatorResponse.getUser();

        commentTreeDTO.setId(commentNodeDTO.getId());
        commentTreeDTO.setContent(commentNodeDTO.getContent());
        commentTreeDTO.setArticleId(commentNodeDTO.getArticleId());
        commentTreeDTO.setCreateDate(commentNodeDTO.getCreateDate());
        commentTreeDTO.setUpdateDate(commentNodeDTO.getUpdateDate());
        commentTreeDTO.setUserId(commentNodeDTO.getUserId());
        commentTreeDTO.setNickname(userInfo.getNickname());
        commentTreeDTO.setAvatar(userInfo.getAvatar());
        commentTreeDTO.setGender(userInfo.getGender());
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

    @Transactional
    public Result pushCommentByArticle(CommentParam commentParam){
        UserQueryRequest userQueryRequest = new UserQueryRequest(commentParam.getUserId());
        UserOperatorResponse userOperatorResponse = userFacadeService.query(userQueryRequest);
        UserInfo userInfo = userOperatorResponse.getUser();
        if (userInfo == null) {
            return Result.fail(COMMENT_USER_NOT_EXIST.getCode(),COMMENT_USER_NOT_EXIST.getMessage());
        }
        Comment comment = new Comment();
        Date currentTime = new Date();
        comment.setUserId(commentParam.getUserId());
        comment.setArticleId(commentParam.getArticleId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(currentTime);
        comment.setUpdateDate(currentTime);
        int insertComment = commentServiceMapper.pushCommentByArticle(comment);
        if(insertComment > 0){
            CommentRelation commentRelation = new CommentRelation();
            commentRelation.setAncestor(comment.getId());
            commentRelation.setDescendant(comment.getId());
            commentRelation.setDepth(0);
            int insertRelation = commentServiceMapper.pushCommentRelation(commentRelation);
            CommentVo commentVo = new CommentVo();
            commentVo.setId(comment.getId());
            commentVo.setArticleId(commentParam.getArticleId());
            commentVo.setContent(commentParam.getContent());
            commentVo.setCreateDate(currentTime);
            commentVo.setUpdateDate(currentTime);
            commentVo.setGender(userInfo.getGender());
            commentVo.setNickname(userInfo.getNickname());
            commentVo.setAvatar(userInfo.getAvatar());
            if(insertRelation > 0){
                return Result.success(commentVo);
            }
        }
        return Result.fail(COMMENT_INSERT_COMMENT_ERROR.getCode(),COMMENT_INSERT_COMMENT_ERROR.getMessage());
    }
}
