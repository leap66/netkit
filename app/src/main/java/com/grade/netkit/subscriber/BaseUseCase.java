package com.grade.netkit.subscriber;


import com.grade.netkit.client.ApiClient;
import com.grade.netkit.client.UpdateClient;
import com.trello.rxlifecycle.LifecycleProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * BaseUseCase :
 * <p>
 * </> Created by ylwei on 2018/12/28.
 */
public abstract class BaseUseCase<T> {

  // 构建UseCase
  protected abstract Observable buildUseCaseObservable();

  public void subscribe(LifecycleProvider provider, Subscriber useCaseSubscriber) {
    Observable temp = buildUseCaseObservable()//
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    if (provider != null)
      temp.compose(provider.bindToLifecycle());
    temp.subscribe(useCaseSubscriber);
  }

  protected T platformApiClient() {
    return ApiClient.platformClient().create(getType());
  }

  public T updateClient() {
    return UpdateClient.updateClient().create(getType());
  }

  private Class<T> getType() {
    Class<T> entityClass;
    Type t = getClass().getGenericSuperclass();
    Type[] p = ((ParameterizedType) t).getActualTypeArguments();
    entityClass = (Class<T>) p[0];
    return entityClass;
  }
}
