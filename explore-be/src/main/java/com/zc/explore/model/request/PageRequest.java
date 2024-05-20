package com.zc.explore.model.request;

public class PageRequest {
  private int page;
  private int size;

  public PageRequest(int page, int size) {
    this.page = page;
    this.size = size;
  }

  public int getPage() {
    return this.page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return this.size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return "{" +
        " page='" + getPage() + "'" +
        ", size='" + getSize() + "'" +
        "}";
  }
}
