package com.atguigu.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Page是分页的模型对象
 * <T>是具体的分页模块bean类
 * @author luozuishuai
 * @Created on 2020-11-26 16:20
 */
public class Page<T> {

    public static final Integer PAGE_SIZE = 4;
    //当前页码
    private Integer pageNo;
    //总页码数
    private Integer pageTotal;
    //总记录数
    private Integer pageTotalCount;
    //每页显示数量
    private Integer pageSize = PAGE_SIZE;
    //当前页数据
    private List<T> items;

    private String url;

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        //数据有效边界检查
        if(pageNo < 1){
            this.pageNo = 1;
        }else if(pageNo > pageTotal){
            this.pageNo = pageTotal;
        }
        else{
            this.pageNo = pageNo;
        }
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageTotalCount, Integer pageSize, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageTotalCount = pageTotalCount;
        this.pageSize = pageSize;
        this.items = items;
    }

    public Page() {

    }
}
