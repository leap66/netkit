package com.grade.netkit.client;


import com.grade.netkit.interceptor.ErrorLogInterceptor;
import com.grade.netkit.util.HttpUtil;
import com.grade.netkit.interceptor.NullOnEmptyConverterFactory;
import com.grade.netkit.interceptor.RequestErrorInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * UpdateClient : 基础API APP更新客户端
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class UpdateClient {
  private static String baseUrl;
  private static Retrofit updateClient;

  // 未登陆之前使用该对象 没有Token
  public static Retrofit updateClient() {

    if (null != updateClient)
      return updateClient;
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient okClient = new OkHttpClient.Builder().retryOnConnectionFailure(true)
        .addInterceptor(logging).connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(new ErrorLogInterceptor(ApiClient.callback))//
        .addInterceptor(new RequestErrorInterceptor()).build();
    updateClient = new Retrofit.Builder().baseUrl(baseUrl).client(okClient)
        .addConverterFactory(new NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(HttpUtil.getHttpGson()))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

    return updateClient;
  }

  public static void init(String baseUrl) {
    UpdateClient.baseUrl = baseUrl;
  }
}
