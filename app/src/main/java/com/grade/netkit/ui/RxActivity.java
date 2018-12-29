package com.grade.netkit.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.grade.netkit.date.AuthEvent;
import com.grade.netkit.util.KeyBoardUtil;
import com.grade.netkit.widget.LoadingLayout;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * RxActivity :
 * <p>
 * </> Created by ylwei on 2018/12/28.
 */
public abstract class RxActivity extends RxAppCompatActivity {
  private boolean keyboardAutoHide = true;
  protected LifecycleProvider provider;
  protected Context context;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    provider = this;
    context = this;
    initComponent();
    createEventHandlers();
    loadData();
    EventBus.getDefault().register(this);
  }

  // 初始化界面控件
  protected abstract void initComponent();

  // 初次加载数据
  protected abstract void loadData();

  // 界面事件响应
  protected void createEventHandlers() {
  }

  // 设置软键盘是否自动隐藏
  protected void setKeyboardAutoHide(boolean b) {
    this.keyboardAutoHide = b;
  }

  // 点击空白处隐藏软键盘
  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
      View v = getCurrentFocus();
      if (isEdt(v, ev) && keyboardAutoHide)
        KeyBoardUtil.keyShow(v, false);
      return super.dispatchTouchEvent(ev);
    }
    if (getWindow().superDispatchTouchEvent(ev))
      return getWindow().superDispatchTouchEvent(ev);
    return onTouchEvent(ev);
  }

  // 判断当前焦点是否是输入框
  private boolean isEdt(View v, MotionEvent event) {
    if (v instanceof EditText) {
      int[] leftTop = {
          0, 0};
      v.getLocationInWindow(leftTop);
      int left = leftTop[0];
      int top = leftTop[1];
      int bottom = top + v.getHeight();
      int right = left + v.getWidth();
      return !(event.getX() > left && event.getX() < right && event.getY() > top
          && event.getY() < bottom);
    }
    return false;
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void handleTokenExpired(AuthEvent event) {
    tokenExpired(event);
  }

  protected void tokenExpired(AuthEvent event) {
  }

  protected LoadingLayout getLoadingLayout() {
    return null;
  }

  protected void startLoading() {
    if (getLoadingLayout() != null)
      getLoadingLayout().startLoading();
  }

  protected void stopLoading() {
    if (getLoadingLayout() != null)
      getLoadingLayout().stopLoading();
  }

  @Override
  protected void onDestroy() {
    EventBus.getDefault().unregister(this);
    super.onDestroy();
  }
}
