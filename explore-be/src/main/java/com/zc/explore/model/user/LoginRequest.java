package com.zc.explore.model.user;

public class LoginRequest {
  private String email;
  private String pwd;

  public LoginRequest(String email, String pwd) {
    this.email = email;
    this.pwd = pwd;
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
        " email='" + getEmail() + "'" +
        ", pwd='" + getPwd() + "'" +
        "}";
  }
}
