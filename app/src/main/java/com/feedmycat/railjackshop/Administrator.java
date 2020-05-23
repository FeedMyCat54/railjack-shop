package com.feedmycat.railjackshop;

import androidx.room.Entity;

@Entity(tableName = "administrator_table")
public class Administrator {

  private int id;
  private String username;
  private String password;

  public Administrator(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
