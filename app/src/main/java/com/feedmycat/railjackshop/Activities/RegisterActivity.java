package com.feedmycat.railjackshop.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.feedmycat.railjackshop.Entities.Customer;
import com.feedmycat.railjackshop.Entities.ShoppingCart;
import com.feedmycat.railjackshop.R;
import com.feedmycat.railjackshop.ViewModels.CustomerViewModel;
import com.feedmycat.railjackshop.ViewModels.ShoppingCartViewModel;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
  EditText etUsername, etPassword;
  TextView tvLogin;
  Button btnRegister;

  private CustomerViewModel customerViewModel;
  private ShoppingCartViewModel shoppingCartViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    etUsername = findViewById(R.id.et_username);
    etPassword = findViewById(R.id.et_password);
    tvLogin = findViewById(R.id.tv_login);
    btnRegister = findViewById(R.id.btn_register);

    customerViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(CustomerViewModel.class);
    shoppingCartViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ShoppingCartViewModel.class);

    //Customer observer
    customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
      @Override
      public void onChanged(List<Customer> customers) {
      }
    });

    // Go to login
    tvLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(i);
      }
    });

    // Validates the inputs and registers the user if they don't already exist
    btnRegister.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
          Toast.makeText(RegisterActivity.this, "Please insert a username and password", Toast.LENGTH_SHORT).show();
        } else if (userExists(username)) {
          Toast.makeText(RegisterActivity.this, username + " is taken", Toast.LENGTH_SHORT).show();
        } else {
          register(username, password);
          Intent i = new Intent(RegisterActivity.this, MainActivity.class);
          startActivity(i);
        }
      }
    });
  }

  // Checks if the user exist
  boolean userExists(String username) {
    List<Customer> allCustomers = customerViewModel.getAllCustomers().getValue();
    for (Customer customer : allCustomers) {
      if (customer.getUsername().equals(username)) {
        return true;
      }
    }
    return false;
  }

  // Register user and create a cart for them
  void register(String username, String password) {
    Customer newCustomer = new Customer(username, password, 0);
    customerViewModel.insert(newCustomer);
    for (Customer customer : customerViewModel.getAllCustomers().getValue()) {
      if (customer.getUsername().equals(username)) {
        shoppingCartViewModel.insert(new ShoppingCart(customer.getId()));
      }
    }
  }
}
