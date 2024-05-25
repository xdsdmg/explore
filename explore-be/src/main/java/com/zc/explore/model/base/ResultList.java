package com.zc.explore.model.base;

import java.util.List;

public class ResultList<T> {
  private List<T> data;
  private int total;

  public ResultList(List<T> data, int total) {
    this.data = data;
    this.total = total;
  }

  public List<T> getData() {
    return this.data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public int getTotal() {
    return this.total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
