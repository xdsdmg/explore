package com.zc.explore.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zc.explore.model.topic.TopicPageQuery;
import com.zc.explore.model.topic.Topic;

@Mapper
public interface TopicMapper {
  List<Topic> list(TopicPageQuery query);

  int count(String titleFuzzySearch);
}