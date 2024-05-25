package com.zc.explore.model.response;

import java.util.List;

public class ResultArray<T> implements ResultIF {
  private int code;
  private String msg;
  private int total;
  private List<T> data;

  public ResultArray(int code, String msg, int total, List<T> data) {
    this.code = code;
    this.msg = msg;
    this.total = total;
    this.data = data;
  }

  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return this.msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getTotal() {
    return this.total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public Object getData() {
    return this.data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
}
