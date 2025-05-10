package com.jackie.jackieblog.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.jackie.jackieblog.base.response.ResponseCode.SUCCESS;

/**
 * @author jackie wang
 */
@Getter
@Setter
public class MultiResult<T> extends Result<List<T>> {
    /**
     * 总记录数
     */
    private long total;
    /**
     * 当前页码
     */
    private long currentPage;
    /**
     * 每页记录数
     */
    private long pageSize;

    /**
     * 总页数
     */
    private long totalPage;

    public MultiResult() {
        super();
    }

    public MultiResult(Boolean success, String code, String message, List<T> data, long total, long currentPage, long pageSize, long totalPage) {
        super(success, code, message, data);
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
    }

    public static <T> MultiResult<T> successMulti(List<T> data, long total, long currentPage, long pageSize, long totalPage) {
        return new MultiResult<>(true, SUCCESS.name(), SUCCESS.name(), data, total, currentPage, pageSize, totalPage);
    }

    public static <T> MultiResult<T> errorMulti(String errorCode, String errorMsg) {
        return new MultiResult<>(true, errorCode, errorMsg, null, 0, 0, 0,0);
    }

}
