package com.feedmycat.railjackshop.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.feedmycat.railjackshop.Entities.Sale;
import com.feedmycat.railjackshop.Repositories.SaleRepository;
import java.util.List;

public class SaleViewModel extends AndroidViewModel {
  private SaleRepository repository;
  private LiveData<List<Sale>> allSales;

  public SaleViewModel(@NonNull Application application) {
    super(application);
    repository = new SaleRepository(application);
    allSales = repository.getAllSales();
  }

  //Methods to pass data from the ViewModel to the repository
  public void insert(Sale sale) {
    repository.insert(sale);
  }

  public void update(Sale sale) {
    repository.update(sale);
  }

  public void delete(Sale sale) {
    repository.delete(sale);
  }

  public LiveData<List<Sale>> getAllSales() {
    return allSales;
  }
}
