package com.grade.netkit.interceptor;

import android.support.annotation.NonNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Dns;

/**
 * OkHttpDns :
 * <p>
 * </> Created by ylwei on 2018/12/28.
 */
public class OkHttpDns implements Dns {

  private static OkHttpDns instance;

  public static OkHttpDns getInstance() {
    if (instance == null) {
      instance = new OkHttpDns();
    }
    return instance;
  }

  private LookUpListener lookUpListener;

  public void setLookUpListener(LookUpListener listener) {
    lookUpListener = listener;
  }

  @Override
  public List<InetAddress> lookup(@NonNull String hostname) throws UnknownHostException {
    if (this.lookUpListener != null) {
      // 通过异步解析接口获取ip
      String ip = lookUpListener.getHost(hostname);
      if (ip != null)
        // 如果ip不为null，直接使用该ip进行网络请求
        return Arrays.asList(InetAddress.getAllByName(ip));
    }
    // 如果返回null，走系统DNS服务解析域名
    return Dns.SYSTEM.lookup(hostname);
  }

  public interface LookUpListener {
    String getHost(String name);
  }
}
