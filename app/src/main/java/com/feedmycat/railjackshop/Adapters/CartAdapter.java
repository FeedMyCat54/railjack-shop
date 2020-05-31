package com.feedmycat.railjackshop.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.feedmycat.railjackshop.Entities.CartItem;
import com.feedmycat.railjackshop.Entities.Product;
import com.feedmycat.railjackshop.R;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ItemHolder> {
  private List<CartItem> cartItems = new ArrayList<>();
  private List<Product> products = new ArrayList<>();

  @NonNull
  @Override
  public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View cartView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.cart_item, parent, false);
    return new ItemHolder(cartView);
  }

  @Override
  public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
    CartItem currentCartItem = cartItems.get(position);
    Product currentProduct = products.get(position);

    holder.textViewName.setText(currentProduct.getName());
    holder.textViewStat.setText(currentProduct.getStat());
    holder.textViewManufacturer.setText(currentProduct.getManufacturer());
    holder.textViewQuantity.setText(currentCartItem.getQuantity() + " items");
  }

  @Override
  public int getItemCount() {
    return cartItems.size();
  }

  public void setCartItemsAndProducts(List<CartItem> cartItems, List<Product> products) {
    this.cartItems = cartItems;
    this.products = products;
    notifyDataSetChanged();
  }

  class ItemHolder extends RecyclerView.ViewHolder {
    private TextView textViewName, textViewStat, textViewQuantity, textViewManufacturer;
    private ImageButton buttonRemove;

    public ItemHolder(@NonNull View itemView) {
      super(itemView);
      textViewName = itemView.findViewById(R.id.tv_name_cart);
      textViewStat = itemView.findViewById(R.id.tv_stat_cart);
      textViewQuantity = itemView.findViewById(R.id.tv_quantity);
      textViewManufacturer = itemView.findViewById(R.id.tv_manufacturer_cart);
      buttonRemove = itemView.findViewById(R.id.btn_remove);
    }
  }
}
