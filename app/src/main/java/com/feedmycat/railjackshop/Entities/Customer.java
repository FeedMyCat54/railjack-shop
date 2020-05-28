package com.feedmycat.railjackshop.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer_table")
public class Customer {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private String username;
  private String password;
  private int balance;

  public Customer(String username, String password, int balance) {
    this.username = username;
    this.password = password;
    this.balance = balance;
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

  public int getBalance() {
    return balance;
  }
}
