package com.feedmycat.railjackshop.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.feedmycat.railjackshop.ViewModels.CustomerViewModel;
import com.feedmycat.railjackshop.Entities.Customer;
import com.feedmycat.railjackshop.R;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
  EditText etUsername, etPassword;
  TextView tvLogin;
  Button btnRegister;

  private CustomerViewModel customerViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    etUsername = findViewById(R.id.et_username);
    etPassword = findViewById(R.id.et_password);
    tvLogin = findViewById(R.id.tv_login);
    btnRegister = findViewById(R.id.btn_register);

    customerViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(CustomerViewModel.class);

    // Go to login
    tvLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(i);
      }
    });

    btnRegister.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
          Toast.makeText(RegisterActivity.this, "Please insert a username and password", Toast.LENGTH_SHORT).show();
        } else {
          register(username, password);
        }
      }
    });
  }

  void register(String username, String password) {
    List<Customer> allCustomers = customerViewModel.getAllCustomers().getValue();
    for (int i = 0; i < allCustomers.size(); i++) {
      if (allCustomers.get(i).getUsername().equals(username)) {
        Toast.makeText(this, username + " is taken", Toast.LENGTH_SHORT).show();
      } else {
        Customer customer = new Customer(username, password, 0);
        customerViewModel.insert(customer);
      }
    }
  }
}
