package com.jokers.common.response;

/**
 * @author yuton
 * @version 1.0
 * @description
 * @since 2018/7/9 23:00
 */
public interface IPageResult<T> extends IResult<T> {
    /**
     * 是否最后一页
     *
     * @return boolean
     */
    boolean isEnd();

    int getTotalPages();

    /**
     * 第几页
     */
    void setPageNum(int pageNum);

    int getPageNum();

    /**
     * 每页记录数
     */
    void setPageSize(int pageSize);

    int getPageSize();

    /**
     * 总页面数
     *
     * @param totalPages int
     */
    void setTotalPages(int totalPages);

    int getTotalElements();

    /**
     * 总记录数
     *
     * @param count int
     */
    void setTotalElements(int count);

}
