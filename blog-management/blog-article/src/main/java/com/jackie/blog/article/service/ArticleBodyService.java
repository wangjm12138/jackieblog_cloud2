package com.jackie.blog.article.service;

import com.jackie.blog.article.vo.ArticleBodyVo;
import com.jackie.blog.article.dao.ArticleBodyServiceMapper;
import com.jackie.blog.article.entity.ArticleBody;
import com.jackie.blog.base.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
