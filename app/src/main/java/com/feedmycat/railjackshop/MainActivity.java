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

        if (!username.trim().isEmpty() && !password.trim().isEmpty()) {
          if (login(username, password)) {
            Toast.makeText(MainActivity.this, "user exists", Toast.LENGTH_SHORT).show();
            //TODO Start the correct activity for the correct user
          } else {
            Toast.makeText(MainActivity.this, "wrong credentials", Toast.LENGTH_SHORT).show();
          }
        } else {
          Toast.makeText(MainActivity.this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  // Checks if a user with those credentials exists
  boolean login(String username, String password) {
    List<Customer> allCustomers = customerViewModel.getAllCustomers().getValue();
    for (int i = 0; i < allCustomers.size(); i++) {
      if (allCustomers.get(i).getUsername().equals(username) && allCustomers.get(i).getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }
}
