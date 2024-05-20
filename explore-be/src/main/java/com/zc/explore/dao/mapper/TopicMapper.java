package com.zc.explore.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zc.explore.dao.model.Topic;
import com.zc.explore.model.topic.ListRequest;

// public interface TopicMapper
//     extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Topic>, CommonUpdateMapper {
//   @SelectProvider(type = SqlProviderAdapter.class, method = "select")
//   @Results(id = "Topic", value = {
//       @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
//       @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
//       @Result(column = "user_id", property = "userID", jdbcType = JdbcType.VARCHAR),
//       @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.DATE),
//       @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.DATE),
//   })
//   List<Topic> list(SelectStatementProvider selectStatement);
// }

@Mapper
public interface TopicMapper {
  List<Topic> list(ListRequest query);
}