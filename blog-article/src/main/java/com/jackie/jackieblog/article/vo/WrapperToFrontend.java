package com.jackie.jackieblog.article.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Jackie Wang
 * @WechatID: ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年03月19日 9:29
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WrapperToFrontend {

    private long pageNum;

    private long pages;

    private long pageSize;

    private long total;

    private boolean last;

    private Object value;


    public static WrapperToFrontend defaultValue(Object data) {
        return new WrapperToFrontend(0, 0, 0, 0, false, data);
    }


}
