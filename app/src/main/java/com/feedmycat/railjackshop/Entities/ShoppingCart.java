package com.feedmycat.railjackshop.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import static androidx.room.ForeignKey.CASCADE;
import androidx.room.PrimaryKey;

@Entity(tableName = "shopping_cart_table", foreignKeys = {
        @ForeignKey(entity = Customer.class, parentColumns = "id", childColumns = "customerId",
            onDelete = CASCADE, onUpdate = CASCADE)
})
public class ShoppingCart {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int customerId;

    public ShoppingCart(int customerId) {
        this.customerId = customerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }
}
