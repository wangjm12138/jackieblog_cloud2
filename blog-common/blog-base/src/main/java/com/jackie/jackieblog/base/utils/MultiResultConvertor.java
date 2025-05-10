package com.jackie.jackieblog.base.utils;


import com.jackie.jackieblog.base.response.PageResponse;
import com.jackie.jackieblog.base.vo.MultiResult;

import static com.jackie.jackieblog.base.response.ResponseCode.SUCCESS;

/**
 * @author jackie wang
 */
public class MultiResultConvertor {

    public static <T> MultiResult<T> convert(PageResponse<T> pageResponse) {
        MultiResult<T> multiResult = new MultiResult<T>(true, SUCCESS.name(), SUCCESS.name(), pageResponse.getDatas(), pageResponse.getTotal(), pageResponse.getCurrentPage(), pageResponse.getPageSize(), pageResponse.getTotalPage());
        return multiResult;
    }
}
