package com.jackie.jackieblog.article.service;

import com.jackie.jackieblog.article.dao.ArticleServiceMapper;
import com.jackie.jackieblog.article.dao.CommentServiceMapper;
import com.jackie.jackieblog.article.dto.CommentNodeDTO;
import com.jackie.jackieblog.article.vo.CommentVo;
import com.jackie.jackieblog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentServiceMapper commentServiceMapper;


    public Result<List<CommentNodeDTO>> listCommentByArticle(long articleId){
        List<CommentNodeDTO> commentNodeDTOS= commentServiceMapper.listCommentByArticle(articleId);
        return  Result.success(commentNodeDTOS);
    }
}
