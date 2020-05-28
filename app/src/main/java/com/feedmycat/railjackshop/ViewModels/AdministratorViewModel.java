package com.feedmycat.railjackshop.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.feedmycat.railjackshop.Repositories.AdministratorRepository;
import com.feedmycat.railjackshop.Entities.Administrator;

import java.util.List;

public class AdministratorViewModel extends AndroidViewModel {
  private AdministratorRepository repository;
  private LiveData<List<Administrator>> allAdministrators;

  public AdministratorViewModel(@NonNull Application application) {
    super(application);
    repository = new AdministratorRepository(application);
    allAdministrators = repository.getAllAdministrators();
  }

  //Methods to pass data from the ViewModel to the repository
  public void insert(Administrator administrator) {
    repository.insert(administrator);
  }

  public void update(Administrator administrator) {
    repository.update(administrator);
  }

  public void delete(Administrator administrator) {
    repository.delete(administrator);
  }

  public void deleteAllCustomers() {
    repository.deleteAllAdministrators();
  }

  public LiveData<List<Administrator>> getAllAdministrators() {
    return allAdministrators;
  }
}
