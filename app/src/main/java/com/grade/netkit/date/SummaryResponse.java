package com.grade.netkit.date;

/**
 * SummaryResponse : 网络请求包含摘要参数
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class SummaryResponse<T, S> extends Response<T> {
  private S summary;

  public S getSummary() {
    return summary;
  }

  public void setSummary(S summary) {
    this.summary = summary;
  }

}
