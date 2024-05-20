package com.zc.explore.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.explore.dao.mapper.TopicMapper;
import com.zc.explore.dao.model.Topic;
import com.zc.explore.model.topic.ListRequest;

@Service
public class TopicService {
  @Autowired
  private TopicMapper topicDao;
  @Autowired
  private SqlSessionFactory sqlSessionFactory;

  public List<Topic> list(ListRequest req) {

    // try (SqlSession session = sqlSessionFactory.openSession()) {
    // TopicMapper mapper = session.getMapper(TopicMapper.class);
    // SelectStatementProvider selectStatement = select

    // List<PersonRecord> rows = mapper.selectMany(selectStatement);
    // assertThat(rows).hasSize(3);
    // }

    System.out.printf("topic: %s\n", topicDao.list(new ListRequest("ab", 1, 1)).toString());

    return null;
  }
}
