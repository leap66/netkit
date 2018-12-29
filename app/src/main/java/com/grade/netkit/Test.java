package com.grade.netkit;

import android.content.Context;

import com.grade.netkit.date.Response;
import com.grade.netkit.date.SummaryResponse;
import com.grade.netkit.subscriber.HttpSubscriber;
import com.grade.netkit.subscriber.SummarySubscriber;
import com.grade.netkit.usecase.Test1Case;
import com.grade.netkit.usecase.Test2Case;
import com.grade.unit.callback.OnNetCallback;
import com.grade.unit.model.base.BEntity;
import com.trello.rxlifecycle.LifecycleProvider;

public class Test {

  public static void register(LifecycleProvider provider, String id, final OnNetCallback<BEntity> callback) {
    new Test1Case(id).subscribe(provider, new HttpSubscriber<BEntity>() {

      @Override
      public void onFailure(String errorMsg, Response response) {
        callback.onResult(false, null, errorMsg);
      }

      @Override
      public void onSuccess(Response<BEntity> response) {
        callback.onResult(false, response.getData(), "");
      }
    });
  }

  public static void register(Context context, BEntity entity, final OnNetCallback<BEntity> callback) {
    new Test2Case(entity).subscribe(null, new SummarySubscriber<BEntity, Boolean>(context) {
      @Override
      public void onFailure(String errorMsg, SummaryResponse response) {
        callback.onResult(false, null, errorMsg);
      }

      @Override
      public void onSuccess(SummaryResponse<BEntity, Boolean> response) {
        callback.onResult(false, response.getData(), "");
      }
    });
  }
}
