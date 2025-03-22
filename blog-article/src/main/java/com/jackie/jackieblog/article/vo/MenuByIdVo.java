package com.jackie.jackieblog.article.vo;

import com.jackie.jackieblog.article.entity.Category;
import lombok.Data;

import java.util.List;

/**
 * @version 1.0.0
 * @Author: Jackie Wang
 * @WechatID: star373629168
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.com
 * @Date: 2024/12/26 20:44
 */
@Data
public class MenuByIdVo {

    private Integer categoryId;

    private String categoryName;

    private Integer categoryAmount;

    private Integer categoryDetailsId;

    private String categoryDetailsName;

    private Integer categoryDetailsAmount;

}
