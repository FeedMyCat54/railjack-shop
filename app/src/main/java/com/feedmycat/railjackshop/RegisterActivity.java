package com.feedmycat.railjackshop;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {
  EditText etUsername, etPassword;
  TextView tvRegister;
  Button btnRegister;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    etUsername = findViewById(R.id.et_username);
    etPassword = findViewById(R.id.et_password);
    tvRegister = findViewById(R.id.tv_login);
    btnRegister = findViewById(R.id.btn_register);
  }
}
