package com.message.common;


public class PageUtil {
    private Integer pageNum;  //当前页

    private Integer pageSize;  //每页条数

    private Integer total;    //总条数

    private Integer pages;    //总页数

    private Integer prePage;    //上一页

    private Integer nextPage;   //下一页

    private Integer startRow;    //起始行数

    protected Object list;      //结果集对象

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
        pages =total % pageSize ==0? total%pageSize:total%pageSize+1;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPrePage() {
        return pageNum ==1?1:pageNum-1;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return pageNum == pages ? pages :pageNum+1;
    }

    public void setNextPage(Integer nextpage) {
        this.nextPage = nextPage;
    }

    public Integer getStartRow() {
        return (pageNum-1)*pageSize;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }
}
