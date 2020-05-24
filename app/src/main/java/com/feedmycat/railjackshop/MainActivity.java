package com.feedmycat.railjackshop;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private CustomerViewModel customerViewModel;
  private AdministratorViewModel administratorViewModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //References to the ViewModels
    customerViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(CustomerViewModel.class);
    administratorViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(AdministratorViewModel.class);

    //Customer observer
    customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
      @Override
      public void onChanged(List<Customer> customers) {
        //Update RecyclerView
        Toast.makeText(MainActivity.this, "Customer", Toast.LENGTH_SHORT).show();
      }
    });

    //Administrator observer
    administratorViewModel.getAllCustomers().observe(this, new Observer<List<Administrator>>() {
      @Override
      public void onChanged(List<Administrator> administrators) {
        //Update RecyclerView
        Toast.makeText(MainActivity.this, "Administrator", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
