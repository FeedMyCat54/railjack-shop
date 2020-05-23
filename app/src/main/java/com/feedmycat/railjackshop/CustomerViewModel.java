package com.feedmycat.railjackshop;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class CustomerViewModel extends AndroidViewModel {
  private CustomerRepository repository;
  private LiveData<List<Customer>> allCustomers;

  public CustomerViewModel(@NonNull Application application) {
    super(application);
    repository = new CustomerRepository(application);
    allCustomers = repository.getAllCustomers();
  }

  //Methods to pass data from the ViewModel to the repository
  public void insert(Customer customer) {
    repository.insert(customer);
  }

  public void update(Customer customer) {
    repository.update(customer);
  }

  public void delete(Customer customer) {
    repository.delete(customer);
  }

  public void deleteAllCustomers() {
    repository.deleteAllCustomers();
  }

  public LiveData<List<Customer>> getAllCustomers() {
    return allCustomers;
  }
}
