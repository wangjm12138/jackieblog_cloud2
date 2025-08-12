package com.jackie.blog.article.controller;

import com.jackie.blog.article.param.CommentParam;
import com.jackie.blog.article.vo.CommentTreeVo;
import com.jackie.blog.article.service.CommentService;
import com.jackie.blog.base.response.PageResponse;
import com.jackie.blog.base.utils.MultiResultConvertor;
import com.jackie.blog.base.vo.MultiResult;
import com.jackie.blog.base.vo.Result;
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
@RequestMapping("api/blog/comment")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @GetMapping("/{articleId}")
    public MultiResult<CommentTreeVo> listArticleComment(@PathVariable("articleId") Long articleId, @RequestParam(name="page", required = false, defaultValue = "1") Integer page, @RequestParam(name="limit",required = false, defaultValue = "10") Integer limit) {
        PageResponse<CommentTreeVo> pageResponse =  commentService.listCommentByArticle(articleId, page, limit);
        return MultiResultConvertor.convert(pageResponse);
    }

    @PostMapping()
    public Result pushArticleComment(@RequestBody CommentParam commentParam) {
        return  commentService.pushCommentByArticle(commentParam);
    }
}
