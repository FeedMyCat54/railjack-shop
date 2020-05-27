package com.feedmycat.railjackshop;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  EditText etUsername, etPassword;
  TextView tvRegister;
  Button btnLogin;

  private CustomerViewModel customerViewModel;
  private AdministratorViewModel administratorViewModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etUsername = findViewById(R.id.et_username);
    etPassword = findViewById(R.id.et_password);
    tvRegister = findViewById(R.id.tv_register);
    btnLogin = findViewById(R.id.btn_login);

    //References to the ViewModels
    customerViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(CustomerViewModel.class);
    administratorViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(AdministratorViewModel.class);

    //Customer observer
    customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
      @Override
      public void onChanged(List<Customer> customers) {
        Toast.makeText(MainActivity.this, "Customer data", Toast.LENGTH_SHORT).show();
      }
    });

    // Start the register activity
    tvRegister.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(i);
      }
    });

    // Login button listener. Validates that the fields are not empty and attempts to login
    btnLogin.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
          Toast.makeText(MainActivity.this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
        } else {
          login(username, password);
        }
      }
    });
  }

  // Checks if a user with those credentials exists
  void login(String username, String password) {
    List<Customer> allCustomers = customerViewModel.getAllCustomers().getValue();
    for (int i = 0; i < allCustomers.size(); i++) {
      if (allCustomers.get(i).getUsername().equals(username) && allCustomers.get(i).getPassword().equals(password)) {
        Intent intent = new Intent(MainActivity.this, ShopActivity.class);
        intent.putExtra("EXTRA_CUSTOMER_ID", allCustomers.get(i).getId());
        startActivity(intent);
        finish();
        return;
      }
    }
    Toast.makeText(MainActivity.this, "wrong credentials", Toast.LENGTH_SHORT).show();
  }
}
