package com.jackie.blog.base.utils;


import com.jackie.blog.base.response.PageResponse;
import com.jackie.blog.base.vo.MultiResult;

import static com.jackie.blog.base.response.ResponseCode.SUCCESS;

/**
 * @author jackie wang
 */
public class MultiResultConvertor {

    public static <T> MultiResult<T> convert(PageResponse<T> pageResponse) {
        MultiResult<T> multiResult = new MultiResult<T>(true, SUCCESS.name(), SUCCESS.name(), pageResponse.getDatas(), pageResponse.getTotal(), pageResponse.getCurrentPage(), pageResponse.getPageSize(), pageResponse.getTotalPage());
        return multiResult;
    }
}
