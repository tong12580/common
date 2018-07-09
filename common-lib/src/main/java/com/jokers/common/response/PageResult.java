package com.jokers.common.response;

/**
 * @author yuton
 * @version 1.0
 * @description 分页
 * @since 2018/7/9 23:41
 */
public class PageResult<T> extends Result<T> implements IPageResult<T> {

    private int totalPages;
    private int totalElements;
    private int pageNum = 1;
    private int pageSize = 5;

    @Override
    public boolean isEnd() {
        return pageNum >= totalPages;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public int getPageNum() {
        return pageNum;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public int getTotalElements() {
        return totalElements;
    }

    @Override
    public void setTotalElements(int count) {
        this.totalElements = count;
    }
}
