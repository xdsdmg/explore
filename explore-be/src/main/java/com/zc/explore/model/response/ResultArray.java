package com.zc.explore.model.response;

public class ResultArray implements ResultIF {
  private int code;
  private String msg;
  private int total;
  private Object[] data;

  public ResultArray(int code, String msg, int total, Object[] data) {
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

  public void setData(Object[] data) {
    this.data = data;
  }
}
