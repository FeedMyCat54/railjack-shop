package com.feedmycat.railjackshop.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.feedmycat.railjackshop.Adapters.CartAdapter;
import com.feedmycat.railjackshop.Entities.CartItem;
import com.feedmycat.railjackshop.Entities.Customer;
import com.feedmycat.railjackshop.Entities.Product;
import com.feedmycat.railjackshop.Entities.Sale;
import com.feedmycat.railjackshop.R;
import com.feedmycat.railjackshop.ViewModels.CartItemViewModel;
import com.feedmycat.railjackshop.ViewModels.CustomerViewModel;
import com.feedmycat.railjackshop.ViewModels.ProductViewModel;
import com.feedmycat.railjackshop.ViewModels.SaleViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartFragment extends Fragment {

  private static final String ARG_CART_ID = "argCartId";
  private static final String ARG_USER_ID = "argUserId";

  private CartItemViewModel cartItemViewModel;
  private ProductViewModel productViewModel;
  private CustomerViewModel customerViewModel;
  private SaleViewModel saleViewModel;

  private List<Product> products = new ArrayList<>();
  private List<Customer> allCustomers = new ArrayList<>();
  private List<CartItem> allCartItems = new ArrayList<>();

  public static CartFragment newInstance(int userId, int cartId) {
    CartFragment fragment = new CartFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_USER_ID, userId);
    args.putInt(ARG_CART_ID, cartId);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_cart, container, false);

    RecyclerView recyclerView = v.findViewById(R.id.rv_cart);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setHasFixedSize(true);

    final CartAdapter adapter = new CartAdapter();
    recyclerView.setAdapter(adapter);

    cartItemViewModel = new ViewModelProvider.AndroidViewModelFactory(
        getActivity().getApplication()).create(CartItemViewModel.class);
    productViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
        .create(ProductViewModel.class);
    cartItemViewModel.findItemsForCart(getArguments().getInt(ARG_CART_ID, 0)).observe(this,
        new Observer<List<CartItem>>() {
          @Override
          public void onChanged(List<CartItem> cartItems) {
            allCartItems = cartItems;
            for (CartItem cartItem : allCartItems
            ) {
              try {
                Product product = productViewModel.getProductById(cartItem.getProductId()).get(0);
                products.add(product);
              } catch (ExecutionException | InterruptedException e) {
                System.out.println(e.getMessage());
              }
            }
            adapter.setCartItemsAndProducts(allCartItems, products);
          }
        });

    customerViewModel = new ViewModelProvider.AndroidViewModelFactory(
        getActivity().getApplication()).create(CustomerViewModel.class);
    customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
      @Override
      public void onChanged(List<Customer> customers) {
        allCustomers = customers;
      }
    });

    saleViewModel = new ViewModelProvider.AndroidViewModelFactory(
        getActivity().getApplication()).create(SaleViewModel.class);

    FloatingActionButton fabBuy = v.findViewById(R.id.fab_buy_cart);
    fabBuy.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        int total = calculateTotal();
        buyItemsInCart(total);
        adapter.setCartItemsAndProducts(allCartItems, products);
      }
    });

    return v;
  }

  // Returns the total cost of all the items in the cart
  private int calculateTotal() {
    int total = 0;
    for (CartItem item : allCartItems) {
      try {
        Product product = productViewModel.getProductById(item.getProductId()).get(0);
        total += item.getQuantity() * product.getPrice();
      } catch (ExecutionException | InterruptedException e) {
        System.out.println(e.getMessage());
      }
    }
    return total;
  }

  // Removes the items from the cart, reduces the balance of the customer and creates a new sale
  private void buyItemsInCart(int total) {
    for (Customer customer : allCustomers) {
      if (customer.getId() == getArguments().getInt(ARG_USER_ID, 0)) {
        if (customer.getBalance() > total) {
          for (CartItem cartItem : allCartItems) {
            Customer newCustomer = new Customer(customer.getUsername(), customer.getPassword(),
                customer.getBalance() - total);
            newCustomer.setId(customer.getId());
            customerViewModel.update(newCustomer);
            try {
              Product product = productViewModel.getProductById(cartItem.getProductId()).get(0);
              saleViewModel.insert(new Sale(customer.getId(), product.getId()));
            } catch (ExecutionException | InterruptedException e) {
              System.out.println(e.getMessage());
            }
            cartItemViewModel.delete(cartItem);
          }
          allCartItems.clear();
          products.clear();
          Toast.makeText(getActivity(), "Items purchased", Toast.LENGTH_SHORT).show();
          return;
        } else if (customer.getBalance() < total) {
          Toast.makeText(getActivity(), "Not enough platinum", Toast.LENGTH_SHORT).show();
        }
      }
    }
  }
}
