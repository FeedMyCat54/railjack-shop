package com.feedmycat.railjackshop.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.feedmycat.railjackshop.Entities.ShoppingCart;
import java.util.List;

@Dao
public interface ShoppingCartDao {

  @Insert
  void insert(ShoppingCart shoppingCart);

  @Update
  void update(ShoppingCart shoppingCart);

  @Delete
  void delete(ShoppingCart shoppingCart);

  @Query("SELECT * FROM shopping_cart_table WHERE customerId=:customerId")
  List<ShoppingCart> findShoppingCartsForUser(final int customerId);
}
