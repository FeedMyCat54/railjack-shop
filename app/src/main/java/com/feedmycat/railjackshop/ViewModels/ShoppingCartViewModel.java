package com.feedmycat.railjackshop.ViewModels;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.feedmycat.railjackshop.Entities.ShoppingCart;
import com.feedmycat.railjackshop.Repositories.ShoppingCartRepository;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ShoppingCartViewModel extends AndroidViewModel {
  private ShoppingCartRepository repository;

  public ShoppingCartViewModel(@NonNull Application application) {
    super(application);
    repository = new ShoppingCartRepository(application);
  }

  public void insert(ShoppingCart shoppingCart) {
    repository.insert(shoppingCart);
  }

  public void update(ShoppingCart shoppingCart) {
    repository.update(shoppingCart);
  }

  public void delete(ShoppingCart shoppingCart) {
    repository.delete(shoppingCart);
  }

  public List<ShoppingCart> findShoppingCartsForUser(int id)
      throws ExecutionException, InterruptedException {
    return repository.findShoppingCartsForUser(id);
  }
}
