package com.zc.explore.model.topic;

import com.zc.explore.model.base.BasePageQuery;

public class TopicPageQuery extends BasePageQuery {
  private String titleFuzzySearch;

  public TopicPageQuery(String titleFuzzySearch, int limit, int offset) {
    super(limit, offset);
    this.titleFuzzySearch = titleFuzzySearch;
  }

  public String getTitleFuzzySearch() {
    return this.titleFuzzySearch;
  }

  public void setTitleFuzzySearch(String titleFuzzySearch) {
    this.titleFuzzySearch = titleFuzzySearch;
  }

  @Override
  public String toString() {
    return "{" +
        " titleFuzzySearch='" + getTitleFuzzySearch() + "'" +
        "}";
  }
}
