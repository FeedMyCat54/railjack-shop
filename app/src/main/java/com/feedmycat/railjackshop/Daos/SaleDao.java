package com.feedmycat.railjackshop.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.feedmycat.railjackshop.Entities.Sale;
import java.util.List;

@Dao
public interface SaleDao {
  @Insert
  void insert(Sale sale);

  @Update
  void update(Sale sale);

  @Delete
  void delete(Sale sale);

  @Query("SELECT * FROM sale_table ORDER BY id ASC")
    LiveData<List<Sale>> getAllSales();
}
