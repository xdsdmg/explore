package com.zc.explore.dao.model;

public class User {
  private Long id;
  private String name;
  private String pwd;
  private String email;

  public User(String name, String pwd, String email) {
    this.name = name;
    this.pwd = pwd;
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPwd() {
    return this.pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", name='" + getName() + "'" +
        ", pwd='" + getPwd() + "'" +
        ", email='" + getEmail() + "'" +
        "}";
  }
}
