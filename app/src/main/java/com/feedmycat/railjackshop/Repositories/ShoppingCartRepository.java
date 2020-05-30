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
    new InsertShoppingCartAsyncTask(shoppingCartDao).execute(shoppingCart);
  }

  public void update(ShoppingCart shoppingCart) {
    new UpdateShoppingCartAsyncTask(shoppingCartDao).execute(shoppingCart);
  }

  public void delete(ShoppingCart shoppingCart) {
    new DeleteShoppingCartAsyncTask(shoppingCartDao).execute(shoppingCart);
  }

  public List<ShoppingCart> findShoppingCartsForUser(int id)
      throws ExecutionException, InterruptedException {
    return new FindShoppingCartAsyncTask(shoppingCartDao).execute(id).get();
  }

  private static class InsertShoppingCartAsyncTask extends AsyncTask<ShoppingCart, Void, Void> {
    private  ShoppingCartDao shoppingCartDao;

    private InsertShoppingCartAsyncTask(ShoppingCartDao shoppingCartDao) {
      this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    protected Void doInBackground(ShoppingCart... customers) {
      shoppingCartDao.insert(customers[0]);
      return null;
    }
  }

  private static class UpdateShoppingCartAsyncTask extends AsyncTask<ShoppingCart, Void, Void> {
    private  ShoppingCartDao shoppingCartDao;

    private UpdateShoppingCartAsyncTask(ShoppingCartDao shoppingCartDao) {
      this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    protected Void doInBackground(ShoppingCart... customers) {
      shoppingCartDao.update(customers[0]);
      return null;
    }
  }

  private static class DeleteShoppingCartAsyncTask extends AsyncTask<ShoppingCart, Void, Void> {
    private  ShoppingCartDao shoppingCartDao;

    private DeleteShoppingCartAsyncTask(ShoppingCartDao shoppingCartDao) {
      this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    protected Void doInBackground(ShoppingCart... customers) {
      shoppingCartDao.delete(customers[0]);
      return null;
    }
  }

  private static class FindShoppingCartAsyncTask extends AsyncTask<Integer, Void, List<ShoppingCart>> {
    private ShoppingCartDao shoppingCartDao;

    private FindShoppingCartAsyncTask(ShoppingCartDao shoppingCartDao) {this.shoppingCartDao = shoppingCartDao;}

    @Override
    protected List<ShoppingCart> doInBackground(Integer... integers) {
      return shoppingCartDao.findShoppingCartsForUser(integers[0]);
    }
  }
}
