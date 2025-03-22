package com.jackie.jackieblog.article.utils;

import lombok.Data;

/**
 * @作者 Jackie Wang
 * @微信号 ilovepython12138
 * @GitHub https://github.com/wangjm12138
 * @博客 http://www.jackieblog.top
 * @date 2023年02月19日 19:41
 */
@Data
public class PageParams {

    private int page = 1;

    private int pageSize = 10;

    private Integer menuId = -1;

    private Integer cateId = -1;

    private Integer cateDetailsId = -1;
//    private Integer tagId;
//
//    private String year;
//
//    private String month;
//
//    public String getMonth(){
//        if (this.month != null && this.month.length() == 1){
//            return "0"+this.month;
//        }
//        return this.month;
//    }
}

