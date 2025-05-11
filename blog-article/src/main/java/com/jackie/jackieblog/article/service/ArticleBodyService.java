package com.jackie.jackieblog.article.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jackie.jackieblog.article.dao.ArticleBodyServiceMapper;
import com.jackie.jackieblog.article.entity.ArticleBody;
import com.jackie.jackieblog.article.vo.ArticleBodyVo;
import com.jackie.jackieblog.article.vo.ArticleVo;
import com.jackie.jackieblog.base.vo.Result;
import org.springframework.beans.BeanUtils;
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



    public Result<ArticleBodyVo> searchArticleById(Long id){
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        ArticleBody articleBody = articleBodyServiceMapper.searchArticleById(id);
        BeanUtils.copyProperties(articleBody, articleBodyVo);

        return Result.success(articleBodyVo);
    }
}
