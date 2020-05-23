package com.feedmycat.railjackshop;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Customer.class, Administrator.class}, version = 1)
public abstract class ShopDatabase extends RoomDatabase {
  private static ShopDatabase instance;

  public abstract CustomerDao customerDao();
  public abstract AdministratorDao administratorDao();

  //Returns the database instance
  public static synchronized ShopDatabase getInstance(Context context) {
    //If the instance does not exist create one
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(),
          ShopDatabase.class, "shop_database")
          .fallbackToDestructiveMigration()
          .build();
    }
    return instance;
  }
}
