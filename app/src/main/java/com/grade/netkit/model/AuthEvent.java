package com.grade.netkit.model;

/**
 * AuthEvent : 过期 Event
 * <p>
 * </> Created by ylwei on 2018/3/1.
 */
public class AuthEvent {
  public static int TOKEN_EXPIRED = 1;
  public static int EXIT = 2;

  public int type;

  public AuthEvent(int type) {
    this.type = type;
  }
}
