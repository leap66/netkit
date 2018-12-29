package com.grade.netkit.client;

import android.support.annotation.NonNull;

import com.grade.netkit.interceptor.ErrorLogInterceptor;
import com.grade.netkit.interceptor.NullOnEmptyConverterFactory;
import com.grade.netkit.interceptor.OkHttpDns;
import com.grade.netkit.interceptor.RequestErrorInterceptor;
import com.grade.netkit.mgr.NetMgr;
import com.grade.netkit.mgr.TokenMgr;
import com.grade.netkit.util.HttpUtil;
import com.grade.unit.callback.OnErrorCallback;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiClient : 基础API 网络访问客户端
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class ApiClient {
  private static String baseUrl;

  // 未登陆之前使用该对象 没有Token
  public static Retrofit platformClient() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient okClient = new OkHttpClient.Builder().retryOnConnectionFailure(true)
        .dns(OkHttpDns.getInstance())
        .addInterceptor(getUserCookie).addInterceptor(setUserCookie)//
        .addInterceptor(logging).addInterceptor(new ErrorLogInterceptor(NetMgr.getErrCallback()))//
        .addInterceptor(new RequestErrorInterceptor()).build();
    return new Retrofit.Builder().baseUrl(baseUrl).client(okClient)
        .addConverterFactory(new NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(HttpUtil.getHttpGson()))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
  }

  // 获取用户Cookie并设置Cookie到header
  private static Interceptor setUserCookie = new Interceptor() {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
      Request request;
      String token = TokenMgr.getUserToken();
      if (token != null) {
        request = chain.request().newBuilder().addHeader("Cookie", token)
            .addHeader("trace_id", HttpUtil.getTraceId()).build();
      } else {
        request = chain.request().newBuilder().addHeader("trace_id", HttpUtil.getTraceId()).build();
      }
      return chain.proceed(request);
    }
  };

  // 保存用户Cookie
  private static Interceptor getUserCookie = new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Request request = chain.request();
      Response response = chain.proceed(request);
      String cookie = response.headers().get("Set-Cookie");
      if (cookie != null && cookie.contains("jwt=")) {
        TokenMgr.update(cookie);
      }
      return response;
    }
  };

  public static void init(String baseUrl) {
    ApiClient.baseUrl = baseUrl;
  }

}
