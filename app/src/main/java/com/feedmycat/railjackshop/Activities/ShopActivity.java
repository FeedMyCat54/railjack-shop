package com.feedmycat.railjackshop.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.feedmycat.railjackshop.Fragments.ShopFragment;
import com.feedmycat.railjackshop.R;

public class ShopActivity extends AppCompatActivity {
    private int customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        customerId = getIntent().getIntExtra("EXTRA_CUSTOMER_ID", 0);

        ShopFragment fragment = ShopFragment.newInstance(customerId);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
