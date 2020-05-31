package com.feedmycat.railjackshop.Entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "sale_table", foreignKeys = {
    @ForeignKey(entity = Customer.class, parentColumns = "id", childColumns = "userId",
        onDelete = CASCADE, onUpdate = CASCADE),
    @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "productId",
        onDelete = CASCADE, onUpdate = CASCADE)})
public class Sale {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private int userId;
  private int productId;

  public Sale(int userId, int productId) {
    this.userId = userId;
    this.productId = productId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public int getProductId() {
    return productId;
  }
}
