package com.jackie.blog.article.service;


import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.jackie.blog.article.vo.ArticleVo;
import com.jackie.blog.article.dao.ArticleServiceMapper;
import com.jackie.blog.article.dao.CategoryServiceMapper;
import com.jackie.blog.article.entity.Article;
import com.jackie.blog.article.entity.convertor.ArticleConvertor;
import com.jackie.blog.article.utils.PageParams;
import com.jackie.blog.base.response.PageResponse;
import com.jackie.blog.base.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Author:  Jackie Wang
 * @WechatId:  ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年02月19日 19:59
 */
@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleServiceMapper articleServiceMapper;

//    @Autowired
//    private CategoryServiceMapper categoryServiceMapper;
//
//    @Autowired
//    private TagService tagService;


    public Result<List<ArticleVo>> listArticleTop() {
        List<Article> records = articleServiceMapper.listArticleTop();
        System.out.println(records);
        List<ArticleVo> articleVoList;
        articleVoList = ArticleConvertor.INSTANCE.mapToVo(records);
        return Result.success(articleVoList);
    }

    public Result<List<ArticleVo>> listArticleRecent() {
        List<Article> records = articleServiceMapper.listArticleRecent();
        List<ArticleVo> articleVoList;
        articleVoList = ArticleConvertor.INSTANCE.mapToVo(records);

        return Result.success(articleVoList);
    }
    //JetCache的@Cached注解基于AOP实现SpEL表达式依赖这个-parameter编译，所以maven要加入这个编译或者intelij idea加入
    @Cached(
            name = ":articleList:", // 缓存前缀
            key = "#pageParams.toString()", // 组合 Key
            expire = 60, // 缓存 60 秒
            timeUnit = TimeUnit.MINUTES,
            cacheType = CacheType.REMOTE // 本地 + 远程缓存
    )
    public PageResponse<ArticleVo> listArticle(PageParams pageParams) {
        PageResponse<Article> articlePageResponse;
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());

        IPage<Article> articleIPage = articleServiceMapper.listArticle(
                page,
                pageParams.getMenuId(),pageParams.getCateId(),pageParams.getCateDetailsId());
        List<Article> records = articleIPage.getRecords();

        if (articleIPage.getCurrent() == articleIPage.getPages()) {
            articlePageResponse = PageResponse.of(records, articleIPage.getTotal(), articleIPage.getSize(), articleIPage.getCurrent(), articleIPage.getPages(), true);
        } else {
            articlePageResponse = PageResponse.of(records, articleIPage.getTotal(), articleIPage.getSize(), articleIPage.getCurrent(), articleIPage.getPages(), false);
        }
        return PageResponse.of(ArticleConvertor.INSTANCE.mapToVo(articlePageResponse.getDatas()),articlePageResponse.getTotal(), articlePageResponse.getPageSize(), articlePageResponse.getCurrentPage(), articlePageResponse.getTotalPage(), articlePageResponse.isLast());
    }

}

