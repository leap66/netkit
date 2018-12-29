package com.grade.netkit.usecase;


import com.grade.netkit.date.Response;
import com.grade.netkit.date.SummaryResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * AuthApi :
 * <p>
 * </> Created by ylwei on 2018/12/28.
 */
public interface AuthApi {

  @GET("app/auth/test1")
  Observable<Response<Boolean>> test1(@Query("id") String id);

  @POST("app/auth/test2")
  Observable<SummaryResponse<Boolean, Boolean>> test2(@Body Boolean entity);
}
