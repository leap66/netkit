package com.grade.netkit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * QueryParam : 网络请你参数
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class QueryParam implements Serializable {
  private int start;
  private int limit;
  private List<FilterParam> filters = new ArrayList<>();
  private List<SortParam> sorters = new ArrayList<>();

  public QueryParam() {
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public List<FilterParam> getFilters() {
    return filters;
  }

  public void setFilters(List<FilterParam> filters) {
    this.filters = filters;
  }

  public List<SortParam> getSorters() {
    return sorters;
  }

  public void setSorters(List<SortParam> sorters) {
    this.sorters = sorters;
  }

  public void nextPage() {
    this.start = this.start + this.getLimit();
  }

  public void resetPage() {
    position = this.start;
    this.start = 0;
  }

  // 存储 resetPage 之前的 start
  private transient int position;

  public void prePage(boolean refresh) {
    if (refresh) {
      this.start = position;
    } else {
      this.start = this.start - this.getLimit();
      if (this.start < 0) {
        this.start = 0;
      }
    }
  }

  public FilterParam findFilter(String property) {
    for (FilterParam param : getFilters()) {
      if (property.equals(param.getProperty())) {
        return param;
      }
    }
    return null;
  }

  public void removeFilter(String property) {
    for (FilterParam param : getFilters()) {
      if (property.equals(param.getProperty())) {
        getFilters().remove(param);
        return;
      }
    }
  }
}
