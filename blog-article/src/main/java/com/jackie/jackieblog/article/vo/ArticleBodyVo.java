package com.jackie.jackieblog.article.vo;

import com.jackie.jackieblog.article.entity.ArticleBody;
import lombok.Data;

@Data
public class ArticleBodyVo extends ArticleBody {

    private String content;
}
