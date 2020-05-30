package com.feedmycat.railjackshop.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.feedmycat.railjackshop.Entities.CartItem;
import java.util.List;

@Dao
public interface CartItemDao {

  @Insert
  void insert(CartItem cartItem);

  @Update
  void update(CartItem cartItem);

  @Delete
  void delete(CartItem cartItem);

  @Query("SELECT * FROM cart_item_table WHERE shoppingCartId=:shoppingCartId")
  List<CartItem> findItemsForCart(final int shoppingCartId);
}
