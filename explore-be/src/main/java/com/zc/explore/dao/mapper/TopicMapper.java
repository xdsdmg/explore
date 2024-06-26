package com.zc.explore.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zc.explore.model.topic.TopicPageQuery;
import com.zc.explore.model.topic.Topic;

@Mapper
public interface TopicMapper {
  List<Topic> list(TopicPageQuery query);

  int count(String titleFuzzySearch);

  @Insert("INSERT topic(`title`, `user_id`, `content`) VALUES(#{topic.title}, #{topic.userID}, #{topic.content})")
  int createTopic(@Param("topic") Topic topic);
}