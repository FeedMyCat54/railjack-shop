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
import com.feedmycat.railjackshop.Entities.Customer;
import com.feedmycat.railjackshop.R;
import com.feedmycat.railjackshop.ViewModels.CustomerViewModel;
import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {

  private CustomerViewModel customerViewModel;

  public static UsersFragment newInstance() {
    return new UsersFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.users_fragment, container, false);

    final TextView usersList = v.findViewById(R.id.tv_users);

    customerViewModel = new ViewModelProvider.AndroidViewModelFactory(
        getActivity().getApplication()).create(CustomerViewModel.class);
    customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
      @Override
      public void onChanged(List<Customer> customers) {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : customers) {
          //String text = usersList.getText().toString() + "/n" + customer.getUsername() + " " + customer.getBalance();
          sb.append("\n").append(customer.getUsername())
              .append(" ").append(customer.getBalance())
              .append(" platinum");
          usersList.setText(sb);
        }
      }
    });

    return v;
  }
}
