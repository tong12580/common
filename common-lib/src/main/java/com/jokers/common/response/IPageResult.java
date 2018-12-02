package com.jokers.common.response;

/**
 * 分页实体类
 *
 * @author yuton
 * @version 1.0
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
     * @param pageNum 第几页
     */
    void setPageNum(int pageNum);

    int getPageNum();

    /**
     * @param pageSize  每页记录数
     */
    void setPageSize(int pageSize);

    int getPageSize();

    /**
     * @param totalPages 总页面数
     */
    void setTotalPages(int totalPages);

    int getTotalElements();

    /**
     *
     * @param count 总记录数
     */
    void setTotalElements(int count);

}
