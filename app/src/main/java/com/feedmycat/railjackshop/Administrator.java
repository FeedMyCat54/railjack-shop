package com.feedmycat.railjackshop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "administrator_table")
public class Administrator {

  @PrimaryKey(autoGenerate = true)
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
