package com.zc.explore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;

import com.zc.explore.model.topiic.Topic;

public interface TopicMapper
    extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Topic>, CommonUpdateMapper {
  @SelectProvider(type = SqlProviderAdapter.class, method = "select")
  @Results(id = "Topic", value = {
      @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
      @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
      @Result(column = "user_id", property = "userID", jdbcType = JdbcType.VARCHAR),
      @Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.DATE),
      @Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.DATE),
  })
  List<Topic> list(SelectStatementProvider selectStatement);
}
