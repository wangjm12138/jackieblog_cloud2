package com.jackie.blog.article.controller;

import com.jackie.blog.article.exception.ArticleException;
import com.jackie.blog.article.exception.CommentErrorCode;
import com.jackie.blog.article.param.CommentParam;
import com.jackie.blog.article.service.HtmlSanitizerService;
import com.jackie.blog.article.service.MultiLevelRateLimitService;
import com.jackie.blog.article.utils.RateLimitResult;
import com.jackie.blog.article.vo.CommentTreeVo;
import com.jackie.blog.article.service.CommentService;
import com.jackie.blog.base.exception.ErrorCode;
import com.jackie.blog.base.response.PageResponse;
import com.jackie.blog.base.utility.HelpFun;
import com.jackie.blog.base.utils.MultiResultConvertor;
import com.jackie.blog.base.vo.MultiResult;
import com.jackie.blog.base.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jackie.blog.article.exception.CommentErrorCode.COMMENT_TOO_MANY_COMMENT;

/**
 * @Author:  Jackie Wang
 * @WechatId:  ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年02月19日 15:28
 */
@RestController
@RequestMapping("api/blog/comment")
@Slf4j
public class CommentController {

    private static final HelpFun helpFun = new HelpFun();

    @Autowired
    private CommentService commentService;

    @Autowired
    private HtmlSanitizerService htmlSanitizerService;

    @Autowired
    private MultiLevelRateLimitService rateLimitService;

    @GetMapping("/{articleId}")
    public MultiResult<CommentTreeVo> listArticleComment(@PathVariable("articleId") Long articleId, @RequestParam(name="page", required = false, defaultValue = "1") Integer page, @RequestParam(name="limit",required = false, defaultValue = "10") Integer limit) {
        PageResponse<CommentTreeVo> pageResponse =  commentService.listCommentByArticle(articleId, page, limit);
        return MultiResultConvertor.convert(pageResponse);
    }

    @PostMapping()
    public Result pushArticleComment(@RequestBody CommentParam commentParam, HttpServletRequest httpRequest) {
        //html净化
        String sanitizedContent = htmlSanitizerService.sanitize(commentParam.getContent());
        // 2. 检查是否包含不安全内容（可选，用于审计）
        if (htmlSanitizerService.containsUnsafeContent(commentParam.getContent())) {
            log.warn("用户 {} 的评论包含不安全内容", commentParam.getUserId());
            // 可以记录到安全审计日志
        }
        commentParam.setContent(sanitizedContent);

        String clientIp = helpFun.getClientIp(httpRequest);
        String userAgent = httpRequest.getHeader("User-Agent");
        RateLimitResult limitResult = rateLimitService.checkCommentRateLimit(
                String.valueOf(commentParam.getUserId()), clientIp, userAgent
        );
        if(!limitResult.isAllowed()) {
            String retryTime = String.valueOf(limitResult.getRetryAfterSeconds());
            COMMENT_TOO_MANY_COMMENT.setMssage("评论太频繁, 您需等待"+ retryTime+"s");
            throw new ArticleException(COMMENT_TOO_MANY_COMMENT);
        }
        return  commentService.pushCommentByArticle(commentParam);
    }
}
