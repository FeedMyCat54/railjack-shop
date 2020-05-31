package com.feedmycat.railjackshop;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.feedmycat.railjackshop.Daos.CartItemDao;
import com.feedmycat.railjackshop.Daos.SaleDao;
import com.feedmycat.railjackshop.Daos.ShoppingCartDao;
import com.feedmycat.railjackshop.Entities.Administrator;
import com.feedmycat.railjackshop.Entities.CartItem;
import com.feedmycat.railjackshop.Entities.Customer;
import com.feedmycat.railjackshop.Entities.Product;
import com.feedmycat.railjackshop.Daos.AdministratorDao;
import com.feedmycat.railjackshop.Daos.CustomerDao;
import com.feedmycat.railjackshop.Daos.ProductDao;
import com.feedmycat.railjackshop.Entities.Sale;
import com.feedmycat.railjackshop.Entities.ShoppingCart;

@Database(entities = {Customer.class, Administrator.class, Product.class, ShoppingCart.class,
    CartItem.class, Sale.class}, version = 1, exportSchema = false)
public abstract class ShopDatabase extends RoomDatabase {
  private static ShopDatabase instance;

  public abstract CustomerDao customerDao();
  public abstract AdministratorDao administratorDao();
  public abstract ProductDao productDao();
  public abstract ShoppingCartDao shoppingCartDao();
  public abstract CartItemDao cartItemDao();
  public abstract SaleDao saleDao();

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
    private ProductDao productDao;
    private ShoppingCartDao shoppingCartDao;
    private CartItemDao cartItemDao;

    public PopulateDbAsyncTask(ShopDatabase db) {
      this.customerDao = db.customerDao();
      this.administratorDao = db.administratorDao();
      this.productDao = db.productDao();
      this.shoppingCartDao = db.shoppingCartDao();
      this.cartItemDao = db.cartItemDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
      customerDao.insert(new Customer("cus1", "1234", 100));
      customerDao.insert(new Customer("cus2", "1234", 200));
      customerDao.insert(new Customer("cus3", "1234", 50));
      administratorDao.insert(new Administrator("admin1", "admin"));
      shoppingCartDao.insert(new ShoppingCart(1));
      shoppingCartDao.insert(new ShoppingCart(2));
      shoppingCartDao.insert(new ShoppingCart(3));
      productDao.insert(new Product("Shield Array", "Zetki", "+1500 Shield cap", 50, 10));
      productDao.insert(new Product("Reactor", "Vidar", "+95 Avionics cap", 90, 5));
      productDao.insert(new Product("Engines", "Lavan", "+30 m/s", 65, 40));
      cartItemDao.insert(new CartItem(1, 2, 1));
      cartItemDao.insert(new CartItem(2, 3, 1));
      return null;
    }
  }
}
