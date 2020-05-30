package com.feedmycat.railjackshop.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.feedmycat.railjackshop.Entities.Product;

import java.util.List;

@Dao
public interface ProductDao {

  @Insert
  void insert(Product product);

  @Update
  void update(Product product);

  @Delete
  void delete(Product product);

  @Query("DELETE FROM product_table")
  void deleteAllProducts();

  @Query("SELECT * FROM product_table ORDER BY id ASC")
  LiveData<List<Product>> getAllProducts();

  @Query("SELECT * FROM product_table WHERE id=:productId")
  List<Product> getProductById(int productId);
}
