package com.grade.netkit.date;

import java.io.Serializable;

/**
 * SortParam : 网络请求返回数据排序
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class SortParam implements Serializable {
  private String property;
  private String direction;

  public SortParam() {
  }

  public SortParam(String property, String direction) {
    this.property = property;
    this.direction = direction;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }
}
