package com.feedmycat.railjackshop;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private CustomerViewModel customerViewModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //References to the ViewModels
    customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

    //Customer observer
    customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
      @Override
      public void onChanged(List<Customer> customers) {
        //Update RecyclerView
        Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
