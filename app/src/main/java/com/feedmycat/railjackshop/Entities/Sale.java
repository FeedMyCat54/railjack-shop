package com.feedmycat.railjackshop.Entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "sale_table", foreignKeys = {
    @ForeignKey(entity = Customer.class, parentColumns = "id", childColumns = "userId",
        onDelete = CASCADE, onUpdate = CASCADE),
    @ForeignKey(entity = CartItem.class, parentColumns = "id", childColumns = "itemId",
        onDelete = CASCADE, onUpdate = CASCADE)})
public class Sale {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private int userId;
  private int itemId;

  public Sale(int userId, int itemId) {
    this.userId = userId;
    this.itemId = itemId;
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

  public int getItemId() {
    return itemId;
  }
}
