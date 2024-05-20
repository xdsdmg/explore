package com.zc.explore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.explore.dao.mapper.TopicMapper;
import com.zc.explore.model.base.BasePageQuery;
import com.zc.explore.model.topic.TopicPageQuery;
import com.zc.explore.model.topic.Topic;

@Service
public class TopicService {
  @Autowired
  private TopicMapper topicDao;

  public List<Topic> list(String titleFuzzySearch, int page, int size) {
    int limit = (size > 0) ? size : BasePageQuery.DEFAULT_LIMIT;
    int offset = (page > 0 && size > 0) ? (page - 1) * size : BasePageQuery.DEFAULT_OFFSET;

    TopicPageQuery query = new TopicPageQuery(titleFuzzySearch, limit, offset);

    System.out.printf("topic: %s\n", topicDao.list(query).toString());
    return null;
  }
}
