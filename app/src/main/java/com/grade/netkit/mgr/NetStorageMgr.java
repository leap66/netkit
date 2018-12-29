package com.grade.netkit.mgr;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * StorageMgr : 物理缓存管理
 * <p>
 * </> Created by ylwei on 2018/2/24.
 */
public class NetStorageMgr {
  private static SharedPreferences storage;

  // 初始化缓存管理
  public static void init() {
    Context context = NetContext.getInstance();
    storage = context.getSharedPreferences(context.getPackageName() + ".net", Context.MODE_PRIVATE);
  }

  // 缓存数据 t 级别 user
  public static void set(String key, String value) throws RuntimeException {
    setStorage(key, value);
  }

  // 获取对应key的缓存
  public static String get(String key) {
    return getStorage(key);
  }

  // 设置缓存信息
  private static void setStorage(String key, String value) {
    SharedPreferences.Editor editor = storage.edit();
    editor.putString(key, value);
    editor.apply(); // 先提交内存，再异步提交硬盘
    // editor.commit(); //同步提交内存及硬盘
  }

  // 获取缓存信息
  private static String getStorage(String key) {
    return storage.getString(key, null);
  }
}
