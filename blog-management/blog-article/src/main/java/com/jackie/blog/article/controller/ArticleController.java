package com.jackie.blog.article.controller;

import com.jackie.blog.article.service.ArticleBodyService;
import com.jackie.blog.article.service.ArticleService;
import com.jackie.blog.article.vo.ArticleBodyVo;
import com.jackie.blog.article.vo.ArticleVo;
import com.jackie.blog.article.utils.PageParams;
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
@RequestMapping("api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleBodyService articleBodyService;
    /**
     * 首页 文章列表
     * @param
     * @return
     */
    @PostMapping
    public MultiResult<ArticleVo> listArticle(@RequestBody PageParams pageParams) throws InterruptedException {
        PageResponse<ArticleVo> pageResponse = articleService.listArticle(pageParams);
        return MultiResultConvertor.convert(pageResponse);
    }

    @GetMapping("/detail/{Id}")
    public Result<ArticleBodyVo> listArticleDetail(@PathVariable("Id") Long id) {
        return articleBodyService.searchArticleById(id);
    }

    @GetMapping("/top4")
    public Result<List<ArticleVo>> listArticleTop() {
        Result<List<ArticleVo>> top4 = articleService.listArticleTop();
        return top4;
    }

//    @GetMapping("/recent")
//    public Result listArticleRecent() {
//
//        return articleService.listArticleRecent();
//    }
//
//    @Autowired
//    private ArticleBodyService articleBodyService;
//



}
