package com.zc.explore.dao;

import java.sql.JDBCType;
import java.util.Date;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class TopicDynamicSqlSupport {
  public static final Topic topic = new Topic();
  public static final SqlColumn<Integer> id = topic.id;
  public static final SqlColumn<String> title = topic.title;
  public static final SqlColumn<Integer> userID = topic.userID;
  public static final SqlColumn<Date> createdAt = topic.createdAt;
  public static final SqlColumn<Date> updatedAt = topic.updatedAt;

  public static final class Topic extends AliasableSqlTable {
    public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);
    public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);
    public final SqlColumn<Integer> userID = column("user_id", JDBCType.INTEGER);
    public final SqlColumn<Date> createdAt = column("created_at", JDBCType.DATE);
    public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.DATE);

    public Topic() {
      super("Topic", Topic::new);
    }
  }
}
