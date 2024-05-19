package com.zc.explore.model.request;

public class PageRequest {
  private int page;
  private int limit;

  public PageRequest(int page, int limit) {
    this.page = page;
    this.limit = limit;
  }

  public int getPage() {
    return this.page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getLimit() {
    return this.limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  @Override
  public String toString() {
    return "{" +
        " page='" + getPage() + "'" +
        ", limit='" + getLimit() + "'" +
        "}";
  }
}
