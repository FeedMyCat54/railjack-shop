package com.feedmycat.railjackshop.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import com.feedmycat.railjackshop.Daos.ShoppingCartDao;
import com.feedmycat.railjackshop.Entities.ShoppingCart;
import com.feedmycat.railjackshop.ShopDatabase;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ShoppingCartRepository {
  private ShoppingCartDao shoppingCartDao;

  public ShoppingCartRepository(Application application) {
    ShopDatabase database = ShopDatabase.getInstance(application);

    shoppingCartDao = database.shoppingCartDao();
  }

  // Database operations
  public void insert(ShoppingCart shoppingCart) {

  }

  public void update(ShoppingCart shoppingCart) {

  }

  public void delete(ShoppingCart shoppingCart) {

  }

  public List<ShoppingCart> findShoppingCartsForUser(int id)
      throws ExecutionException, InterruptedException {
    return new findShoppingCartAsyncTask(shoppingCartDao).execute(id).get();
  }

  private static class InsertCustomerAsyncTask extends AsyncTask<ShoppingCart, Void, Void> {
    private  ShoppingCartDao shoppingCartDao;

    private InsertCustomerAsyncTask(ShoppingCartDao shoppingCartDao) {
      this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    protected Void doInBackground(ShoppingCart... customers) {
      shoppingCartDao.insert(customers[0]);
      return null;
    }
  }

  private static class UpdateCustomerAsyncTask extends AsyncTask<ShoppingCart, Void, Void> {
    private  ShoppingCartDao shoppingCartDao;

    private UpdateCustomerAsyncTask(ShoppingCartDao shoppingCartDao) {
      this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    protected Void doInBackground(ShoppingCart... customers) {
      shoppingCartDao.update(customers[0]);
      return null;
    }
  }

  private static class DeleteCustomerAsyncTask extends AsyncTask<ShoppingCart, Void, Void> {
    private  ShoppingCartDao shoppingCartDao;

    private DeleteCustomerAsyncTask(ShoppingCartDao shoppingCartDao) {
      this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    protected Void doInBackground(ShoppingCart... customers) {
      shoppingCartDao.delete(customers[0]);
      return null;
    }
  }

  private static class findShoppingCartAsyncTask extends AsyncTask<Integer, Void, List<ShoppingCart>> {
    private ShoppingCartDao shoppingCartDao;

    private findShoppingCartAsyncTask(ShoppingCartDao shoppingCartDao) {this.shoppingCartDao = shoppingCartDao;}

    @Override
    protected List<ShoppingCart> doInBackground(Integer... integers) {

      return shoppingCartDao.findShoppingCartsForUser(integers[0]);
    }
  }
}
