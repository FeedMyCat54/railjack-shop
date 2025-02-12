package com.feedmycat.railjackshop.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.feedmycat.railjackshop.Entities.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

  @Insert
  void insert(Customer customer);

  @Update
  void update(Customer customer);

  @Delete
  void delete(Customer customer);

  @Query("DELETE FROM customer_table")
  void deleteAllCustomers();

  @Query("SELECT * FROM customer_table ORDER BY id ASC")
  LiveData<List<Customer>> getAllCustomers();

  @Query("SELECT * FROM customer_table WHERE id = :id")
  Customer getCustomerById(int id);
}
