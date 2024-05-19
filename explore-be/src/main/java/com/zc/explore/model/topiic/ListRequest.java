package com.zc.explore.model.topiic;

import com.zc.explore.model.request.PageRequest;

public class ListRequest extends PageRequest {
  private String titleFuzzySearch;

  public ListRequest(String titleFuzzySearch, int limit, int offset) {
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
