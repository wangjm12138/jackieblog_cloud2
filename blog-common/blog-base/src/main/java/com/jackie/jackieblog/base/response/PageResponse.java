package com.jackie.jackieblog.base.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页响应
 *
 * @author jackie wang
 */
@Setter
@Getter
public class PageResponse<T> extends MultiResponse<T> {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private long currentPage;

    /**
     * 总页数
     */
    private long totalPage;

    /**
     * 每页结果数
     */
    private long pageSize;

    /**
     * 总数
     */
    private long total;

    /**
     * 是否最后一页
     */
    private boolean last;

    public static <T> PageResponse<T> of(List<T> datas, long total, long pageSize, long currentPage, long totalPage, boolean last) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setSuccess(true);
        pageResponse.setDatas(datas);
        pageResponse.setTotal(total);
        pageResponse.setPageSize(pageSize);
        pageResponse.setCurrentPage(currentPage);
        pageResponse.setLast(last);
        pageResponse.setTotalPage(totalPage);
        return pageResponse;
    }
}
