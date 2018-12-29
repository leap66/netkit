package com.grade.netkit.usecase;

import com.grade.netkit.subscriber.BaseUseCase;

import rx.Observable;

/**
 * Test1Case :
 * <p>
 * </> Created by ylwei on 2018/12/28.
 */
public class Test2Case extends BaseUseCase<AuthApi> {
  private Boolean entity;

  public Test2Case(Boolean entity) {
    this.entity = entity;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return platformApiClient().test2(entity);
  }
}
