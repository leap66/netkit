package com.grade.netkit.model;

/**
 * TokenExpiredException : Token过期异常实体类
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class TokenExpiredException extends ApiException {

  public TokenExpiredException(int code, String message) {
    super(code, message);
  }
}
