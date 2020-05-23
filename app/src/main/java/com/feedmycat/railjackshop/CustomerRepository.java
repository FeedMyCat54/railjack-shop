package com.feedmycat.railjackshop;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class CustomerRepository {
  private CustomerDao customerDao;
  private LiveData<List<Customer>> allCustomers;

  public CustomerRepository(Application application) {
    //Get a database instance
    ShopDatabase database = ShopDatabase.getInstance(application);

    //Get a reference to the Daos and the live data from the database
    customerDao = database.customerDao();
    allCustomers = customerDao.getAllCustomers();
  }

  //Database operations
  public void insert(Customer customer) {
    new InsertCustomerAsyncTask(customerDao).execute(customer);
  }

  public void update(Customer customer) {
    new UpdateCustomerAsyncTask(customerDao).execute(customer);
  }

  public void delete(Customer customer) {
    new DeleteCustomerAsyncTask(customerDao).execute(customer);
  }

  public void deleteAllCustomers(Customer customer) {
    new DeleteAllCustomerAsyncTask(customerDao).execute();
  }
  public LiveData<List<Customer>> getAllCustomers() {
    return allCustomers;
  }

  //Async tasks so that we run the operations on the background thread
  private static class InsertCustomerAsyncTask extends AsyncTask<Customer, Void, Void> {
    private  CustomerDao customerDao;

    private InsertCustomerAsyncTask(CustomerDao customerDao) {
      this.customerDao = customerDao;
    }

    @Override
    protected Void doInBackground(Customer... customers) {
      customerDao.insert(customers[0]);
      return null;
    }
  }

  private static class UpdateCustomerAsyncTask extends AsyncTask<Customer, Void, Void> {
    private  CustomerDao customerDao;

    private UpdateCustomerAsyncTask(CustomerDao customerDao) {
      this.customerDao = customerDao;
    }

    @Override
    protected Void doInBackground(Customer... customers) {
      customerDao.update(customers[0]);
      return null;
    }
  }

  private static class DeleteCustomerAsyncTask extends AsyncTask<Customer, Void, Void> {
    private  CustomerDao customerDao;

    private DeleteCustomerAsyncTask(CustomerDao customerDao) {
      this.customerDao = customerDao;
    }

    @Override
    protected Void doInBackground(Customer... customers) {
      customerDao.delete(customers[0]);
      return null;
    }
  }

  private static class DeleteAllCustomerAsyncTask extends AsyncTask<Void, Void, Void> {
    private  CustomerDao customerDao;

    private DeleteAllCustomerAsyncTask(CustomerDao customerDao) {
      this.customerDao = customerDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      customerDao.deleteAllCustomers();
      return null;
    }
  }
}
