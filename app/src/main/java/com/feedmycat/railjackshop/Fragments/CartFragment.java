package com.feedmycat.railjackshop.Fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.feedmycat.railjackshop.Adapters.CartAdapter;
import com.feedmycat.railjackshop.Entities.CartItem;
import com.feedmycat.railjackshop.Entities.Product;
import com.feedmycat.railjackshop.R;
import com.feedmycat.railjackshop.ViewModels.CartItemViewModel;
import com.feedmycat.railjackshop.ViewModels.ProductViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CartFragment extends Fragment {

  private static final String ARG_CART_ID = "argCartId";

  private CartItemViewModel cartItemViewModel;
  private ProductViewModel productViewModel;

  private List<Product> products = new ArrayList<>();

  public static CartFragment newInstance(int id) {
    CartFragment fragment = new CartFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_CART_ID, id);
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
              for (CartItem cartItem : cartItems
              ) {
                try {
                  Product product = productViewModel.getProductById(cartItem.getProductId()).get(0);
                  products.add(product);
                } catch (ExecutionException | InterruptedException e) {
                  System.out.println(e.getMessage());
                }
              }
              adapter.setCartItemsAndProducts(cartItems, products);
            }
          });
    return v;
  }
}
