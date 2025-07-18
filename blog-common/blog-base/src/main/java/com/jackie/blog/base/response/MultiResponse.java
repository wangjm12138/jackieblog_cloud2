package com.jackie.blog.base.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 响应
 *
 * @author jackie wang
 */
@Setter
@Getter
public class MultiResponse<T> extends BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> datas;

    public static <T> MultiResponse<T> of(List<T> datas) {
        MultiResponse<T> multiResponse =  new MultiResponse<>();
        multiResponse.setDatas(datas);
        multiResponse.setSuccess(true);
        return multiResponse;
    }
}
