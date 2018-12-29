package com.grade.netkit.usecase;

import com.grade.netkit.subscriber.BaseUseCase;

import rx.Observable;

/**
 * Test1Case :
 * <p>
 * </> Created by ylwei on 2018/12/28.
 */
public class Test1Case extends BaseUseCase<AuthApi> {
  private String id;

  public Test1Case(String id) {
    this.id = id;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return platformApiClient().test1(id);
  }
}
