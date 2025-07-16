package com.jackie.jackieblog.article.service;


import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.jackie.jackieblog.article.dao.ArticleServiceMapper;
import com.jackie.jackieblog.article.dao.CategoryServiceMapper;
import com.jackie.jackieblog.article.entity.Article;
import com.jackie.jackieblog.article.entity.Category;
import com.jackie.jackieblog.article.entity.convertor.ArticleConvertor;
import com.jackie.jackieblog.article.utils.PageParams;
import com.jackie.jackieblog.article.vo.ArticleVo;
import com.jackie.jackieblog.article.vo.WrapperToFrontend;
import com.jackie.jackieblog.base.response.PageResponse;
import com.jackie.jackieblog.base.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


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

    @Autowired
    private CategoryServiceMapper categoryServiceMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

//    @Autowired
//    private StringRedisTemplate redisTemplate;
//    @Autowired
//    private StringRedisTemplate redisTemplate;


    public Result<List<ArticleVo>> listArticleTop() {
        List<Article> records = articleServiceMapper.listArticleTop();
        System.out.println(records);
        List<ArticleVo> articleVoList = new ArrayList<>();
        articleVoList = ArticleConvertor.INSTANCE.mapToVo(records);
//        for (Article record : records) {
//            articleVoList.add(copy(record));
//        }

        return Result.success(articleVoList);
    }

    public Result<List<ArticleVo>> listArticleRecent() {
        List<Article> records = articleServiceMapper.listArticleRecent();
        List<ArticleVo> articleVoList = new ArrayList<>();
        articleVoList = ArticleConvertor.INSTANCE.mapToVo(records);
//        for (Article record : records) {
//            articleVoList.add(copy(record));
//        }
        return Result.success(articleVoList);
    }

    @Cached(
            name = ":articleList:", // 缓存前缀
            key = "#pageParams.page + ':' + #pageParams.pageSize + ':' + #pageParams.menuId + ':' + #pageParams.cateId + ':' + #pageParams.cateDetailsId", // 组合 Key
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

