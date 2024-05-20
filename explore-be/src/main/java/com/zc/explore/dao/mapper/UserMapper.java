package com.zc.explore.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zc.explore.model.user.User;

public interface UserMapper {
  @Select("SELECT * FROM user WHERE id = #{id}")
  User getUserById(@Param("id") Long id);

  @Select("SELECT count(*) FROM user WHERE email = #{email}")
  int getUserTotalOfEmail(@Param("email") String email);

  @Select("SELECT count(*) FROM user WHERE email = #{email} LOCK IN SHARE MODE")
  int getUserTotalOfEmailWithWriteLock(@Param("email") String email);

  @Insert("INSERT user(`name`, `pwd`, `email`) VALUES(#{user.name}, #{user.pwd}, #{user.email})")
  int createUser(@Param("user") User user);

  @Select("SELECT count(*) FROM user WHERE email = #{email} and pwd = #{pwd} and activated = 1")
  int checkAccountExist(@Param("email") String email, @Param("pwd") String pwd);
}