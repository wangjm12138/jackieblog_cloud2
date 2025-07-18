package com.jackie.blog.article.vo;

import com.jackie.blog.article.entity.CategoryDetails;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {

    private Integer id;

    private String name;

    private Integer menuId;

    private Integer amount;

    private List<CategoryDetails> details;
}
