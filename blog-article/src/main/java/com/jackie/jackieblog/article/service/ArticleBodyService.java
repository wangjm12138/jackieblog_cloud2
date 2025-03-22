package com.jackie.jackieblog.article.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jackie.jackieblog.article.dao.ArticleBodyServiceMapper;
import com.jackie.jackieblog.article.entity.ArticleBody;
import com.jackie.jackieblog.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: Jackie Wang
 * @WechatID: ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年06月24日 12:30
 * @Description:
 */
@Service
public class ArticleBodyService {


    @Autowired
    private ArticleBodyServiceMapper articleBodyServiceMapper;



    public Result searchArticleById(Long id){
        System.out.println(id);
        ArticleBody record = articleBodyServiceMapper.searchArticleById(id);
        return Result.success(record);
    }
}
