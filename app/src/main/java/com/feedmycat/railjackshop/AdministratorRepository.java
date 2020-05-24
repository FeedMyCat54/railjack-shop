package com.feedmycat.railjackshop;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class AdministratorRepository {
  private AdministratorDao administratorDao;
  private LiveData<List<Administrator>> allAdministrators;

  public AdministratorRepository(Application application) {
    //Get a database instance
    ShopDatabase database = ShopDatabase.getInstance(application);

    //Get a reference to the Daos and the live data from the database
    administratorDao = database.administratorDao();
    allAdministrators = administratorDao.getAllAdministrators();
  }

  //Database operations
  public void insert(Administrator administrator) {
    new InsertAdministratorAsyncTask(administratorDao).execute(administrator);
  }

  public void update(Administrator administrator) {
    new UpdateAdministratorAsyncTask(administratorDao).execute(administrator);
  }

  public void delete(Administrator administrator) {
    new DeleteAdministratorAsyncTask(administratorDao).execute(administrator);
  }

  public void deleteAllAdministrators() {
    new DeleteAllAdministratorsAsyncTask(administratorDao).execute();
  }

  public LiveData<List<Administrator>> getAllAdministrators() {
    return allAdministrators;
  }

  public Administrator getAdministratorById(int id) {
    return administratorDao.getAdministratorById(id);
  }

  private static class InsertAdministratorAsyncTask extends AsyncTask<Administrator, Void, Void> {
    private AdministratorDao administratorDao;

    private InsertAdministratorAsyncTask(AdministratorDao administratorDao) { this.administratorDao = administratorDao;}

    @Override
    protected Void doInBackground(Administrator... administrators) {
      administratorDao.insert(administrators[0]);
      return null;
    }
  }

  private static class UpdateAdministratorAsyncTask extends AsyncTask<Administrator, Void, Void> {
    private AdministratorDao administratorDao;

    private UpdateAdministratorAsyncTask(AdministratorDao administratorDao) { this.administratorDao = administratorDao;}

    @Override
    protected Void doInBackground(Administrator... administrators) {
      administratorDao.update(administrators[0]);
      return null;
    }
  }

  private static class DeleteAdministratorAsyncTask extends AsyncTask<Administrator, Void, Void> {
    private AdministratorDao administratorDao;

    private DeleteAdministratorAsyncTask(AdministratorDao administratorDao) { this.administratorDao = administratorDao;}

    @Override
    protected Void doInBackground(Administrator... administrators) {
      administratorDao.delete(administrators[0]);
      return null;
    }
  }

  private static class DeleteAllAdministratorsAsyncTask extends AsyncTask<Void, Void, Void> {
    private AdministratorDao administratorDao;

    private DeleteAllAdministratorsAsyncTask(AdministratorDao administratorDao) { this.administratorDao = administratorDao;}

    @Override
    protected Void doInBackground(Void... voids) {
      administratorDao.deleteAllAdministrators();
      return null;
    }
  }
}
