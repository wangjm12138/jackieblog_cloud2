package com.jackie.jackieblog.article.controller;

import com.jackie.jackieblog.article.dto.CommentNodeDTO;
import com.jackie.jackieblog.article.service.CommentService;
import com.jackie.jackieblog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<List<CommentNodeDTO>> listArticleComment(@PathVariable("articleId") Long articleId) {
        return commentService.listCommentByArticle(articleId);
    }
}
