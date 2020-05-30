package com.feedmycat.railjackshop.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items_table", foreignKeys = {
    @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "productId",
        onDelete = CASCADE, onUpdate = CASCADE),
    @ForeignKey(entity = ShoppingCart.class, parentColumns = "id", childColumns = "shoppingCartId",
        onDelete = CASCADE, onUpdate = CASCADE)
})
public class CartItem {

  @PrimaryKey(autoGenerate = true)
  private int id;
  private int productId;
  private int quantity;
  private int shoppingCartId;

  public CartItem(int productId, int quantity, int shoppingCartId) {
    this.productId = productId;
    this.quantity = quantity;
    this.shoppingCartId = shoppingCartId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public int getShoppingCartId() {
    return shoppingCartId;
  }
}
