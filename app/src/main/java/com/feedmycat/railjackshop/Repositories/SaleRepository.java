package com.feedmycat.railjackshop.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.feedmycat.railjackshop.Daos.SaleDao;
import com.feedmycat.railjackshop.Entities.Sale;
import com.feedmycat.railjackshop.ShopDatabase;
import java.util.List;

public class SaleRepository {
  private SaleDao saleDao;
  private LiveData<List<Sale>> allSales;

  public SaleRepository(Application application) {
    //Get a database instance
    ShopDatabase database = ShopDatabase.getInstance(application);

    saleDao = database.saleDao();
    allSales = saleDao.getAllSales();
  }

  // Database operation
  public void insert(Sale sale) {
    new InsertSaleAsyncTask(saleDao).execute(sale);
  }

  public void update(Sale sale) {
    new UpdateSaleAsyncTask(saleDao).execute(sale);
  }

  public void delete(Sale sale) {
    new DeleteSaleAsyncTask(saleDao).execute(sale);
  }

  public LiveData<List<Sale>> getAllSales() {
    return allSales;
  }

  private static class InsertSaleAsyncTask extends AsyncTask<Sale, Void, Void> {
    private SaleDao saleDao;

    public InsertSaleAsyncTask(SaleDao saleDao) {
      this.saleDao = saleDao;
    }

    @Override
    protected Void doInBackground(Sale... sales) {
      saleDao.insert(sales[0]);
      return null;
    }
  }

  private static class UpdateSaleAsyncTask extends AsyncTask<Sale, Void, Void> {
    private SaleDao saleDao;

    public UpdateSaleAsyncTask(SaleDao saleDao) {
      this.saleDao = saleDao;
    }

    @Override
    protected Void doInBackground(Sale... sales) {
      saleDao.update(sales[0]);
      return null;
    }
  }

  private static class DeleteSaleAsyncTask extends AsyncTask<Sale, Void, Void> {
    private SaleDao saleDao;

    public DeleteSaleAsyncTask(SaleDao saleDao) {
      this.saleDao = saleDao;
    }

    @Override
    protected Void doInBackground(Sale... sales) {
      saleDao.delete(sales[0]);
      return null;
    }
  }
}
