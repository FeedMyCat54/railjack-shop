package com.feedmycat.railjackshop;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface AdministratorDao {

  @Insert
  void insert(Administrator administrator);

  @Update
  void update(Administrator administrator);

  @Delete
  void delete(Administrator administrator);

  @Query("DELETE FROM administrator_table")
  void deleteAllAdministrators();

  @Query("SELECT * FROM administrator_table ORDER BY id ASC")
  LiveData<List<Administrator>> getAllAdministrators();

  @Query("SELECT * FROM administrator_table WHERE id = :id")
  Administrator getAdministratorById(int id);
}
