package com.grade.netkit.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.LifecycleProvider;

import org.greenrobot.eventbus.EventBus;

/**
 * RxFragment :
 * <p>
 * </> Created by ylwei on 2018/12/28.
 */
public abstract class RxFragment extends com.trello.rxlifecycle.components.support.RxFragment {
  protected LifecycleProvider provider;
  protected Context context;
  protected boolean isVisible;
  protected boolean isPrepare;
  protected boolean isImmersion;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
    provider = this;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View rootView = initComponent(inflater, container);
    createEventHandlers();
    loadData(savedInstanceState);
    EventBus.getDefault().register(this);
    return rootView;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (isLazyLoad()) {
      isPrepare = true;
      isImmersion = true;
      onLazyLoad();
    }
  }

  protected boolean isLazyLoad() {
    return true;
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (getUserVisibleHint()) {
      isVisible = true;
      onVisible();
    } else {
      isVisible = false;
      onInvisible();
    }
  }

  protected void onVisible() {
    onLazyLoad();
  }

  protected void onInvisible() {
  }

  private void onLazyLoad() {
    if (isVisible && isPrepare) {
      isPrepare = false;
    }
  }

  protected abstract View initComponent(LayoutInflater inflater, ViewGroup container);

  protected abstract void loadData(Bundle savedInstanceState);

  protected void createEventHandlers() {
  }

  @Override
  public void onDestroy() {
    EventBus.getDefault().unregister(this);
    super.onDestroy();
  }
}
