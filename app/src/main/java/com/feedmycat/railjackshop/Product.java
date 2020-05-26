package com.feedmycat.railjackshop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private String name;
  private String manufacturer;
  private String stat;
  private int price;
  private int quantity;

  public Product(String name, String manufacturer, String stat, int price, int quantity) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.stat = stat;
    this.price = price;
    this.quantity = quantity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getStat() {
    return stat;
  }

  public int getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }
}
