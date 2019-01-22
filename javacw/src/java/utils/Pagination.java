/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package utils;

/**
 *
 * @author HaShaN
 */
public class Pagination {
    private int page;
    private int count;
    private String sort;
    
    public Pagination() {
    }
    
    public Pagination(int page, int count) {
        this.page = page;
        this.count = count;
    }
    
    public Pagination(int page, int count, String sort) {
        this.page = page;
        this.count = count;
        this.sort = sort;
    }
    
    public int getPage() {
        return page;
    }
    
    public int getCount() {
        return count;
    }
    
    public String getSort() {
        return sort;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public void setSort(String sort) {
        this.sort = sort;
    }
    
    @Override
    public String toString() {
        return "Pagination{" + "page=" + page + ", count=" + count + ", sort=" + sort + '}';
    }
    
    public int getOffset() {
    int start = (page - 1) * count;
    start = start < 0 ? 0 : start;
    return start;
  }
}
