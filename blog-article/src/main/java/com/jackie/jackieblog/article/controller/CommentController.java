package com.jackie.jackieblog.article.controller;

import com.jackie.jackieblog.article.dto.CommentNodeDTO;
import com.jackie.jackieblog.article.service.CommentService;
import com.jackie.jackieblog.article.utils.PageParams;
import com.jackie.jackieblog.article.vo.ArticleVo;
import com.jackie.jackieblog.article.vo.CommentTreeVo;
import com.jackie.jackieblog.base.response.PageResponse;
import com.jackie.jackieblog.base.utils.MultiResultConvertor;
import com.jackie.jackieblog.base.vo.MultiResult;
import com.jackie.jackieblog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:  Jackie Wang
 * @WechatId:  ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年02月19日 15:28
 */
@RestController
@RequestMapping("api/comment")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @GetMapping("/{articleId}")
    public MultiResult<CommentTreeVo> listArticleComment(@PathVariable("articleId") Long articleId, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        PageResponse<CommentTreeVo> pageResponse =  commentService.listCommentByArticle(articleId, page, limit);
        return MultiResultConvertor.convert(pageResponse);
    }
}
