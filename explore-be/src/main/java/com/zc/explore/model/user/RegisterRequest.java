package com.zc.explore.model.user;

public class RegisterRequest {
  private String name;
  private String email;
  private String pwd;

  public RegisterRequest(String name, String pwd, String email) {
    this.name = name;
    this.pwd = pwd;
    this.email = email;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
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
        " name='" + getName() + "'" +
        ", email='" + getEmail() + "'" +
        ", pwd='" + getPwd() + "'" +
        "}";
  }
}
