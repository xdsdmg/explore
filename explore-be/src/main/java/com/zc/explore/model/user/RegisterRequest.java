package com.zc.explore.model.user;

public class RegisterRequest {
  private String name;
  private String pwd;
  private String email;

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

  public String getPwd() {
    return this.pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "{" +
        " name='" + getName() + "'" +
        ", pwd='" + getPwd() + "'" +
        ", email='" + getEmail() + "'" +
        "}";
  }
}
