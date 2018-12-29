package com.grade.netkit.usecase;

import com.grade.netkit.subscriber.BaseUseCase;
import com.grade.unit.model.base.BEntity;

import rx.Observable;

/**
 * Test1Case :
 * <p>
 * </> Created by ylwei on 2018/12/28.
 */
public class Test2Case extends BaseUseCase<AuthApi> {
  private BEntity entity;

  public Test2Case(BEntity entity) {
    this.entity = entity;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return platformApiClient().test2(entity);
  }
}
