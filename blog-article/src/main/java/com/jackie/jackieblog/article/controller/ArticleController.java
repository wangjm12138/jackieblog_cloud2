package com.jackie.jackieblog.article.controller;

import com.jackie.jackieblog.article.service.ArticleBodyService;
import com.jackie.jackieblog.article.service.ArticleService;
import com.jackie.jackieblog.article.utils.PageParams;
import com.jackie.jackieblog.article.vo.ArticleVo;
import com.jackie.jackieblog.base.response.MultiResponse;
import com.jackie.jackieblog.base.response.PageResponse;
import com.jackie.jackieblog.base.utils.MultiResultConvertor;
import com.jackie.jackieblog.base.vo.MultiResult;
import com.jackie.jackieblog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
        System.out.println("jackie");
        System.out.println(pageResponse.getTotalPage());
        return MultiResultConvertor.convert(pageResponse);
    }

    @GetMapping("/detail/{Id}")
    public Result listArticleDetail(@PathVariable("Id") Long id) {
        return articleBodyService.searchArticleById(id);
    }

    @GetMapping("/top4")
    public Result listArticleTop() {
        Result a = articleService.listArticleTop();
        return a;
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
