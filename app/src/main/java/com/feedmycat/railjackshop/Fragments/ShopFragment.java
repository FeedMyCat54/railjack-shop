package com.feedmycat.railjackshop.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.feedmycat.railjackshop.Adapters.ProductAdapter;
import com.feedmycat.railjackshop.Entities.Product;
import com.feedmycat.railjackshop.R;
import com.feedmycat.railjackshop.ViewModels.ProductViewModel;
import java.util.List;

public class ShopFragment extends Fragment {
  private static final String ARG_CUSTOMER_ID = "argCustomerId";

  private ProductViewModel productViewModel;

  public static ShopFragment newInstance (int id) {
    ShopFragment fragment = new ShopFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_CUSTOMER_ID, id);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.shop_fragment, container, false);

    RecyclerView recyclerView = v.findViewById(R.id.rv_shop);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setHasFixedSize(true);

    final ProductAdapter adapter = new ProductAdapter();
    recyclerView.setAdapter(adapter);

    productViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(ProductViewModel.class);
    productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
      @Override
      public void onChanged(List<Product> products) {
        adapter.setProducts(products);
      }
    });

    return v;
  }
}
