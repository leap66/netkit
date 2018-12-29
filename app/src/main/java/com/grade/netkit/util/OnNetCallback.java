package com.grade.netkit.util;

/**
 * OnNetCallback :
 * <p>
 * </> Created by ylwei on 2018/7/23.
 */
public interface OnNetCallback<T> {
  void onResult(boolean state, T t, String errorMsg);
}
