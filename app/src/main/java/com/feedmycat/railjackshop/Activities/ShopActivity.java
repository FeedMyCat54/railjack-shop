package com.feedmycat.railjackshop.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.feedmycat.railjackshop.Entities.ShoppingCart;
import com.feedmycat.railjackshop.Fragments.CartFragment;
import com.feedmycat.railjackshop.Fragments.ShopFragment;
import com.feedmycat.railjackshop.Fragments.UsersFragment;
import com.feedmycat.railjackshop.R;
import com.feedmycat.railjackshop.ViewModels.ShoppingCartViewModel;
import com.google.android.material.navigation.NavigationView;
import java.util.concurrent.ExecutionException;

public class ShopActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ShoppingCartViewModel shoppingCartViewModel;

    private int customerId;
    private int cartId;

    // Sets up the Drawer, the ActionBar and the default fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shoppingCartViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
            .create(ShoppingCartViewModel.class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        customerId = getIntent().getIntExtra("EXTRA_CUSTOMER_ID", 0);
        try {
            cartId = shoppingCartViewModel.findShoppingCartsForUser(customerId).get(0).getId();
        } catch (ExecutionException | InterruptedException e) {
            e.getMessage();
        }

        fragmentTransaction.add(R.id.container_fragment, ShopFragment.newInstance(customerId));
        fragmentTransaction.commit();

//        ShopFragment fragment = ShopFragment.newInstance(customerId);
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    // Checks which item was clicked and loads the correct fragment
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.shop) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, ShopFragment.newInstance(customerId));
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.my_cart) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, CartFragment.newInstance(customerId ,cartId));
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.about) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, UsersFragment
                .newInstance());
            fragmentTransaction.commit();
        }
        return true;
    }
}
