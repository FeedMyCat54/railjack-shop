package com.feedmycat.railjackshop.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.feedmycat.railjackshop.Entities.Sale;
import com.feedmycat.railjackshop.R;
import com.feedmycat.railjackshop.ViewModels.SaleViewModel;
import java.util.List;

public class SalesFragment extends Fragment {

  SaleViewModel saleViewModel;

  public static SalesFragment newInstance() {
    return new SalesFragment();
  }

  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.sales_fragment, container, false);

    final TextView salesList = v.findViewById(R.id.tv_sales);

    saleViewModel = new ViewModelProvider.AndroidViewModelFactory(
        getActivity().getApplication()).create(SaleViewModel.class);
    saleViewModel.getAllSales().observe(this, new Observer<List<Sale>>() {
      @Override
      public void onChanged(List<Sale> sales) {
        StringBuilder sb = new StringBuilder();
        for (Sale sale : sales) {
          //String text = usersList.getText().toString() + "/n" + customer.getUsername() + " " + customer.getBalance();
          sb.append("\n").append("user: ")
              .append(sale.getUserId()).append(" purchased item: ")
              .append(sale.getProductId());
          salesList.setText(sb);
        }
      }
    });

    return v;
  }

}
