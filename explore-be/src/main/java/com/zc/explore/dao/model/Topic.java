package com.zc.explore.dao.model;

import java.util.Date;

public class Topic {
  private int id;
  private String title;

  private int userID;
  private String userName;

  private Date createdAt;
  private Date updatedAt;

  public Topic() {
  }

  // public Topic(int id, String title, int userID, Date createdAt, Date updatedAt) {
  //   this.id = id;
  //   this.title = title;
  //   this.userID = userID;
  //   this.createdAt = createdAt;
  //   this.updatedAt = updatedAt;
  // }

  public Topic(int id, String title, int userID, String userName, Date createdAt, Date updatedAt) {
    this.id = id;
    this.title = title;
    this.userID = userID;
    this.userName = userName;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getUserID() {
    return this.userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", title='" + getTitle() + "'" +
        ", userID='" + getUserID() + "'" +
        ", userName='" + getUserName() + "'" +
        ", createdAt='" + getCreatedAt() + "'" +
        ", updatedAt='" + getUpdatedAt() + "'" +
        "}";
  }
}
