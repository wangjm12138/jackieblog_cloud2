package com.jackie.jackieblog.article.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.jackie.jackieblog.article.dao.ArticleServiceMapper;
import com.jackie.jackieblog.article.dao.CategoryServiceMapper;
import com.jackie.jackieblog.article.entity.Article;
import com.jackie.jackieblog.article.entity.Category;
import com.jackie.jackieblog.article.utils.PageParams;
import com.jackie.jackieblog.article.vo.ArticleVo;
import com.jackie.jackieblog.article.vo.WrapperToFrontend;
import com.jackie.jackieblog.base.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


    public Result listArticleTop() {
        List<Article> records = articleServiceMapper.listArticleTop();
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record));
        }

        return Result.success(articleVoList);
    }

    public Result listArticleRecent() {
        List<Article> records = articleServiceMapper.listArticleRecent();
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record));
        }
        return Result.success(articleVoList);
    }

    public Result listArticle(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
//        List<Integer> categoryIdList = new ArrayList<>();
//        if(pageParams.getMenuId()!=null) {
//            List<Category> categoryList = categoryServiceMapper.listCategoryByMenuId(pageParams.getMenuId());
//            categoryIdList = categoryList.stream().map((item) -> item.getId()).collect(Collectors.toList());
//        }
        IPage<Article> articleIPage = articleServiceMapper.listArticle(
                page,
                pageParams.getMenuId(),pageParams.getCateId(),pageParams.getCateDetailsId());
        List<Article> records = articleIPage.getRecords();
        System.out.println(records.size());
//        for (Article record : records) {
//            String viewCount = (String) redisTemplate.opsForHash().get("view_count", String.valueOf(record.getId()));
//            if (viewCount != null) {
//                record.setViewCounts(Integer.parseInt(viewCount));
//            }
//        }
        List<ArticleVo> recordsVo = copyList(records);
        WrapperToFrontend wrapperRecordsVo = new WrapperToFrontend();
        wrapperRecordsVo.setPageNum(articleIPage.getCurrent());
        wrapperRecordsVo.setPages(articleIPage.getPages());
        wrapperRecordsVo.setPageSize(articleIPage.getSize());
        wrapperRecordsVo.setTotal(articleIPage.getTotal());
        if (articleIPage.getCurrent() == articleIPage.getPages()) {
            wrapperRecordsVo.setLast(true);
        } else {
            wrapperRecordsVo.setLast(false);
        }
        wrapperRecordsVo.setValue(recordsVo);
        return Result.success(wrapperRecordsVo);
    }


    private List<ArticleVo> copyList(List<Article> records) {

        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record));
        }
        return articleVoList;
    }

     private ArticleVo copy(Article article) {

        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(String.valueOf(article.getId()));
        BeanUtils.copyProperties(article,articleVo);

        articleVo.setCreateDate(article.getCreateDate().toString());


//        if (isBody){
//            Long bodyId = article.getBodyId();
//            articleVo.setBody(findArticleBodyById(bodyId));
//        }
//        if (isCategory){
//            Long categoryId = article.getCategoryId();
//            articleVo.setCategory(categoryService.findCategoryById(categoryId));
//        }
        return articleVo;
    }

}

