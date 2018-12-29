package com.grade.netkit.interceptor;

import android.support.annotation.NonNull;

import com.grade.netkit.R;
import com.grade.netkit.date.ApiException;
import com.grade.netkit.mgr.NetContext;
import com.grade.netkit.util.HttpUtil;
import com.grade.netkit.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * RequestErrorInterceptor : 网络请求服务端错误解析 -- 处理请求的status code
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class RequestErrorInterceptor implements Interceptor {

  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
    if (!NetworkUtil.isConnected())
      throw new ApiException(0, NetContext.getInstance().getString(R.string.network_err_0));
    Request request = chain.request();
    Response response = chain.proceed(request);
    ApiException e = HttpUtil.parse(response);
    if (e != null)
      throw e;
    return response;
  }
}
