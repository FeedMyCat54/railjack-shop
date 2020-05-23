package com.feedmycat.railjackshop;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
          .addCallback(roomCallback)
          .build();
    }
    return instance;
  }

  private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
    //Fills the database when it is created
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      new PopulateDbAsyncTask(instance).execute();
    }
  };

  //Async task for populating the database on the background thread
  private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
    private CustomerDao customerDao;
    private AdministratorDao administratorDao;

    public PopulateDbAsyncTask(ShopDatabase db) {
      this.customerDao = db.customerDao();
      this.administratorDao = db.administratorDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
      customerDao.insert(new Customer("cus1", "1234", 100));
      customerDao.insert(new Customer("cus2", "1234", 200));
      customerDao.insert(new Customer("cus3", "1234", 50));
      administratorDao.insert(new Administrator("admin1", "admin"));
      return null;
    }
  }
}
