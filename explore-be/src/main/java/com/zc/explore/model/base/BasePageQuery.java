package com.zc.explore.model.base;

public class BasePageQuery {
  public static final int DEFAULT_LIMIT = 10;
  public static final int DEFAULT_OFFSET = 0;

  private int limit;
  private int offset;

  public BasePageQuery(int limit, int offset) {
    this.limit = limit;
    this.offset = offset;
  }

  public int getLimit() {
    return this.limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public int getOffset() {
    return this.offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  @Override
  public String toString() {
    return "{" +
        " limit='" + getLimit() + "'" +
        ", offset='" + getOffset() + "'" +
        "}";
  }
}
