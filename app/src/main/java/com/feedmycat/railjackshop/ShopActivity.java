package com.feedmycat.railjackshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {
    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        tvHello = findViewById(R.id.tv_hello);
        int userID = getIntent().getExtras().getInt("EXTRA_CUSTOMER_ID");

        tvHello.setText(String.valueOf(userID));
    }
}
