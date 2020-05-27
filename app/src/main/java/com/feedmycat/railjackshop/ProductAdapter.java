package com.feedmycat.railjackshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private List<Product> products = new ArrayList<>();

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product currentProduct = products.get(position);
        holder.textViewName.setText(currentProduct.getName());
        holder.textViewStat.setText(currentProduct.getStat());
        holder.textViewPrice.setText(String.valueOf(currentProduct.getPrice()));
        holder.textViewManufacturer.setText(currentProduct.getManufacturer());
        switch (currentProduct.getManufacturer()) {
            case "Zetki" : holder.imageViewManufacturer.setImageResource(R.drawable.HouseZetkiGold);
            case "Vidar" : holder.imageViewManufacturer.setImageResource(R.drawable.HouseVidarGold);
            case "Lavan" : holder.imageViewManufacturer.setImageResource(R.drawable.HouseLavanGold);
            default: holder.imageViewManufacturer.setImageResource(R.drawable.empyrean_lotus_opened);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        private TextView textViewName, textViewStat, textViewPrice, textViewManufacturer;
        private ImageView imageViewManufacturer;
        private ImageButton buttonAddToCart;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tv_name);
            textViewStat = itemView.findViewById(R.id.tv_stat);
            textViewPrice = itemView.findViewById(R.id.tv_price);
            textViewManufacturer = itemView.findViewById(R.id.tv_manufacturer);
            imageViewManufacturer = itemView.findViewById(R.id.iv_manufacturer);
            buttonAddToCart = itemView.findViewById(R.id.btn_addToCart);
        }
    }
}
