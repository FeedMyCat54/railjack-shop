package com.feedmycat.railjackshop.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.feedmycat.railjackshop.Daos.CartItemDao;
import com.feedmycat.railjackshop.Entities.CartItem;
import com.feedmycat.railjackshop.ShopDatabase;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartItemRepository {
  private CartItemDao cartItemDao;

  public CartItemRepository(Application application) {
    ShopDatabase database = ShopDatabase.getInstance(application);

    cartItemDao = database.cartItemDao();
  }

  // Database operations
  public void insert(CartItem cartItem) {
    new InsertCartItemAsyncTask(cartItemDao).execute(cartItem);
  }

  public void update(CartItem cartItem) {
    new UpdateCartItemAsyncTask(cartItemDao).execute(cartItem);
  }

  public void delete(CartItem cartItem) {
    new DeleteCartItemAsyncTask(cartItemDao).execute(cartItem);
  }

  public LiveData<List<CartItem>> findItemsForCart(int id) throws ExecutionException, InterruptedException {
    return new FindCartItemsAsyncTask(cartItemDao).execute(id).get();
  }

  private static class InsertCartItemAsyncTask extends AsyncTask<CartItem, Void, Void> {
    private CartItemDao cartItemDao;

    private InsertCartItemAsyncTask(CartItemDao cartItemDao) {
      this.cartItemDao = cartItemDao;
    }

    @Override
    protected Void doInBackground(CartItem... cartItems) {
      cartItemDao.insert(cartItems[0]);
      return null;
    }
  }

  private static class UpdateCartItemAsyncTask extends AsyncTask<CartItem, Void, Void> {
    private CartItemDao cartItemDao;

    private UpdateCartItemAsyncTask(CartItemDao cartItemDao) {
      this.cartItemDao = cartItemDao;
    }

    @Override
    protected Void doInBackground(CartItem... cartItems) {
      cartItemDao.update(cartItems[0]);
      return null;
    }
  }

  private static class DeleteCartItemAsyncTask extends AsyncTask<CartItem, Void, Void> {
    private CartItemDao cartItemDao;

    private DeleteCartItemAsyncTask(CartItemDao cartItemDao) {
      this.cartItemDao = cartItemDao;
    }

    @Override
    protected Void doInBackground(CartItem... cartItems) {
      cartItemDao.delete(cartItems[0]);
      return null;
    }
  }

  private static class FindCartItemsAsyncTask extends AsyncTask<Integer, Void, LiveData<List<CartItem>>> {
    private CartItemDao cartItemDao;

    private FindCartItemsAsyncTask(CartItemDao cartItemDao) {
      this.cartItemDao = cartItemDao;
    }

    @Override
    protected LiveData<List<CartItem>> doInBackground(Integer... integers) {
      return cartItemDao.findItemsForCart(integers[0]);
    }
  }
}
