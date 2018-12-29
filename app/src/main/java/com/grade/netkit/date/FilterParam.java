package com.grade.netkit.date;

import java.io.Serializable;

/**
 * FilterParam : 网络请求参数
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class FilterParam implements Serializable {
  private String property;
  private Object value;

  public FilterParam() {
  }

  public FilterParam(String property, Object value) {
    this.property = property;
    this.value = value;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String toString() {
    return property + value.toString();
  }
}
