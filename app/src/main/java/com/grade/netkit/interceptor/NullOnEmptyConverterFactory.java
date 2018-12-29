package com.grade.netkit.interceptor;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

/**
 * NullOnEmptyConverterFactory : 网络请求null转换
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class NullOnEmptyConverterFactory extends Factory {

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                          Retrofit retrofit) {
    final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type,
        annotations);
    return new Converter<ResponseBody, Object>() {
      @Override
      public Object convert(ResponseBody body) throws IOException {
        if (body.contentLength() == 0)
          return null;
        return delegate.convert(body);
      }
    };
  }
}