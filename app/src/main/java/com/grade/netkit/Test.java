package com.grade.netkit;

import android.content.Context;

import com.grade.netkit.model.Response;
import com.grade.netkit.model.SummaryResponse;
import com.grade.netkit.subscriber.HttpSubscriber;
import com.grade.netkit.subscriber.SummarySubscriber;
import com.grade.netkit.usecase.Test1Case;
import com.grade.netkit.usecase.Test2Case;
import com.grade.netkit.util.OnNetCallback;
import com.trello.rxlifecycle.LifecycleProvider;

public class Test {

  public static void register(LifecycleProvider provider, String id, final OnNetCallback<Boolean> callback) {
    new Test1Case(id).subscribe(provider, new HttpSubscriber<Boolean>() {

      @Override
      public void onFailure(String errorMsg, Response response) {
        callback.onResult(false, null, errorMsg);
      }

      @Override
      public void onSuccess(Response<Boolean> response) {
        callback.onResult(true, response.getData(), "");
      }
    });
  }

  public static void register(Context context, Boolean entity, final OnNetCallback<Boolean> callback) {
    new Test2Case(entity).subscribe(null, new SummarySubscriber<Boolean, Boolean>(context) {
      @Override
      public void onFailure(String errorMsg, SummaryResponse response) {
        callback.onResult(false, null, errorMsg);
      }

      @Override
      public void onSuccess(SummaryResponse<Boolean, Boolean> response) {
        callback.onResult(true, response.getData(), "");
      }
    });
  }
}
