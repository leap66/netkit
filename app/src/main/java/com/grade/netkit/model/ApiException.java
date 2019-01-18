package com.grade.netkit.model;

import java.io.IOException;

/**
 * ApiException : 网络返回异常解析
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class ApiException extends IOException {

  private int code;

  public ApiException(int code, String message) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
