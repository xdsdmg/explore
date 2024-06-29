package com.zc.explore.model.topic;

import java.util.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Topic {
  private int id;
  private String title;
  private Long userID;
  private String userName;
  private String content;
  private Date createdAt;
  private Date updatedAt;

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

  public Long getUserID() {
    return this.userID;
  }

  public void setUserID(Long userID) {
    this.userID = userID;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
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
        ", content='" + getContent() + "'" +
        ", createdAt='" + getCreatedAt() + "'" +
        ", updatedAt='" + getUpdatedAt() + "'" +
        "}";
  }
}
