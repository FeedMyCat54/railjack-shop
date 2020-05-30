package com.feedmycat.railjackshop.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.feedmycat.railjackshop.Entities.CartItem;
import com.feedmycat.railjackshop.Repositories.CartItemRepository;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartItemViewModel extends AndroidViewModel {

  private CartItemRepository repository;

  public CartItemViewModel(@NonNull Application application) {
    super(application);
    repository = new CartItemRepository(application);
  }

  public void insert(CartItem cartItem) {
    repository.insert(cartItem);
  }

  public void update(CartItem cartItem) {
    repository.update(cartItem);
  }

  public void delete(CartItem cartItem) {
    repository.delete(cartItem);
  }

  public LiveData<List<CartItem>> findItemsForCart(int id) throws ExecutionException, InterruptedException {
    return repository.findItemsForCart(id);
  }
}
