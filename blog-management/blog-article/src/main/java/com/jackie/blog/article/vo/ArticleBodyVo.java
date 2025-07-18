package com.jackie.blog.article.vo;

import com.jackie.blog.article.entity.ArticleBody;
import lombok.Data;

@Data
public class ArticleBodyVo extends ArticleBody {

    private String content;
}
