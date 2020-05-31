package com.feedmycat.railjackshop.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.feedmycat.railjackshop.Adapters.ProductAdapter;
import com.feedmycat.railjackshop.Adapters.ProductAdapter.OnItemClickListener;
import com.feedmycat.railjackshop.Entities.CartItem;
import com.feedmycat.railjackshop.Entities.Customer;
import com.feedmycat.railjackshop.Entities.Product;
import com.feedmycat.railjackshop.R;
import com.feedmycat.railjackshop.ViewModels.CartItemViewModel;
import com.feedmycat.railjackshop.ViewModels.CustomerViewModel;
import com.feedmycat.railjackshop.ViewModels.ProductViewModel;
import com.feedmycat.railjackshop.ViewModels.ShoppingCartViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ShopFragment extends Fragment {

  private static final String ARG_CUSTOMER_ID = "argCustomerId";

  private int cartId;
  private List<CartItem> cartItemList;
  private List<Customer> allCustomers;

  private ProductViewModel productViewModel;
  private ShoppingCartViewModel shoppingCartViewModel;
  private CartItemViewModel cartItemViewModel;
  private CustomerViewModel customerViewModel;

  private ProductAdapter adapter;

  // Factory method for the Shop fragment
  public static ShopFragment newInstance(int id) {
    ShopFragment fragment = new ShopFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_CUSTOMER_ID, id);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.shop_fragment, container, false);

    RecyclerView recyclerView = v.findViewById(R.id.rv_shop);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setHasFixedSize(true);

    adapter = new ProductAdapter();
    recyclerView.setAdapter(adapter);

    productViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
        .create(ProductViewModel.class);
    productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
      @Override
      public void onChanged(List<Product> products) {
        adapter.setProducts(products);
      }
    });

    shoppingCartViewModel = new ViewModelProvider.AndroidViewModelFactory(
        getActivity().getApplication()).create(ShoppingCartViewModel.class);
    cartItemViewModel = new ViewModelProvider.AndroidViewModelFactory(
        getActivity().getApplication()).create(CartItemViewModel.class);

    // Gets the id for the user's cart
    try {
      cartId = shoppingCartViewModel
          .findShoppingCartsForUser(getArguments().getInt(ARG_CUSTOMER_ID)).get(0).getId();
    } catch (ExecutionException | InterruptedException e) {
      System.out.println(e.getMessage());
    }

    cartItemViewModel.findItemsForCart(cartId)
        .observe(getActivity(), new Observer<List<CartItem>>() {
          @Override
          public void onChanged(List<CartItem> cartItems) {
            cartItemList = cartItems;
          }
        });

    adapter.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onAddClick(final Product product) {
        putItemInCart(product);
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

    FloatingActionButton fabAddCurrency = v.findViewById(R.id.fab_add_currency);
    fabAddCurrency.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        addCurrency(100);
      }
    });
    return v;
  }

  // Puts an item in the user's cart if the items are in stock
  private void putItemInCart(Product product) {
    if (product.getQuantity() > 0) {
      Toast.makeText(getActivity(), "Added 1x" + product.getName() + " in cart", Toast.LENGTH_SHORT).show();
      for (CartItem cartItem : cartItemList
      ) {
        if (cartItem.getProductId() == product.getId()) {
          increaseCartItemQuantity(cartItem, product);
          reduceProductQuantity(product);
          return;
        }
      }
      cartItemViewModel.insert(new CartItem(product.getId(), 1, cartId));
      reduceProductQuantity(product);
    } else {
      Toast.makeText(getActivity(), "Out of stock!", Toast.LENGTH_SHORT).show();
    }
  }

  // Reduces the quantity of a product by 1
  private void reduceProductQuantity(Product product) {
    Product newProduct = new Product(product.getName(), product.getManufacturer(),
        product.getStat(), product.getPrice(), product.getQuantity() - 1);
    newProduct.setId(product.getId());
    productViewModel.update(newProduct);
  }

  // Increases the quantity of a cart item by 1
  private void increaseCartItemQuantity(CartItem cartItem, Product product) {
    CartItem newCartItem = new CartItem(cartItem.getProductId(), cartItem.getQuantity() + 1,
        cartItem.getShoppingCartId());
    newCartItem.setId(cartItem.getId());
    cartItemViewModel.update(newCartItem);
  }

  // Add an amount to the user's currency
  private void addCurrency(int amount) {
    for (Customer customer : allCustomers) {
      if (customer.getId() == getArguments().getInt(ARG_CUSTOMER_ID, 0)) {
        Customer newCustomer = new Customer(customer.getUsername(), customer.getPassword(), customer.getBalance() + amount);
        newCustomer.setId(customer.getId());
        customerViewModel.update(newCustomer);
      }
    }
  }
}
