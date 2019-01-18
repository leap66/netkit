package com.grade.netkit.subscriber;

import android.content.Context;

import com.grade.netkit.model.AuthEvent;
import com.grade.netkit.model.Response;
import com.grade.netkit.model.TokenExpiredException;
import com.grade.netkit.mgr.NetMgr;
import com.grade.netkit.util.GsonUtil;
import com.grade.netkit.util.HttpUtil;
import com.grade.netkit.widget.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

/**
 * HttpSubscriber :
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public abstract class HttpSubscriber<T> extends rx.Subscriber<Response<T>> {
  private LoadingDialog loadingDialog;

  protected HttpSubscriber(Context context) {
    loadingDialog = new LoadingDialog(context);
  }

  protected HttpSubscriber() {
  }

  @Override
  public void onStart() {
    startLoading();
    super.onStart();
  }

  @Override
  public void onCompleted() {
    stopLoading();
    if (isUnsubscribed())
      unsubscribe();
  }

  @Override
  public void onError(Throwable throwable) {
    stopLoading();
    if (throwable instanceof TokenExpiredException) {
      // 使用EventBus通知跳转到登陆页面
      EventBus.getDefault().post(new AuthEvent(AuthEvent.TOKEN_EXPIRED));
    } else {
      onFailure(HttpUtil.parseThrowable(throwable), null);
    }
    if (NetMgr.getErrCallback() != null)
      NetMgr.getErrCallback().failure(GsonUtil.toJson(throwable));
  }

  @Override
  public void onNext(Response<T> t) {
    stopLoading();
    if (t.isSuccess()) {
      onSuccess(t);
    } else {
      if (t.getMessage() != null && t.getMessage().size() > 0) {
        onFailure(t.getMessage().get(0), t);
      } else {
        onFailure(null, t);
      }
    }
  }

  private void startLoading() {
    if (loadingDialog != null)
      loadingDialog.show();
  }

  private void stopLoading() {
    if (loadingDialog != null && loadingDialog.isShowing())
      loadingDialog.dismiss();
  }

  public abstract void onFailure(String errorMsg, Response<T> response);

  public abstract void onSuccess(Response<T> response);
}
