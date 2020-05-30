package com.feedmycat.railjackshop.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.feedmycat.railjackshop.Entities.Product;
import com.feedmycat.railjackshop.Repositories.ProductRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductViewModel extends AndroidViewModel {

  private ProductRepository repository;
  private LiveData<List<Product>> allProducts;

  public ProductViewModel(@NonNull Application application) {
    super(application);
    repository = new ProductRepository(application);
    allProducts = repository.getAllProducts();
  }

  //Methods to pass data from the ViewModel to the repository
  public void insert(Product product) {
    repository.insert(product);
  }

  public void update(Product product) {
    repository.update(product);
  }

  public void delete(Product product) {
    repository.delete(product);
  }

  public void deleteAllProducts() {
    repository.deleteAllProducts();
  }

  public List<Product> getProductById(int id) throws ExecutionException, InterruptedException {
    return repository.getProductById(id);
  }

  public LiveData<List<Product>> getAllProducts() {
    return allProducts;
  }
}
