package com.grade.netkit.mgr;

import android.content.Context;

import com.grade.netkit.client.ApiClient;
import com.grade.netkit.util.OnErrorCallback;

/**
 * NetMgr :
 * <p>
 * </> Created by ylwei on 2018/12/29.
 */
public class NetMgr {
  private static OnErrorCallback callback;
  private static String netTag = "";

  public static void init(Context context, String url) {
    NetContext.init(context);
    NetStorageMgr.init();
    TokenMgr.init();
    ApiClient.init(url);
  }

  public static void setErrCallback(OnErrorCallback callback) {
    NetMgr.callback = callback;
  }

  public static OnErrorCallback getErrCallback() {
    return callback;
  }

  public static String getNetTag() {
    return netTag;
  }

  public static void setNetTag(String netTag) {
    NetMgr.netTag = netTag;
  }
}
