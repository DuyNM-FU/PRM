package com.example.fe_prm.FoodOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fe_prm.ConfirmReservation.ConfirmReservation;
import com.example.fe_prm.FoodOrder.Activity.CartListActivity;
import com.example.fe_prm.FoodOrder.Adapter.CategoryAdapter;
import com.example.fe_prm.FoodOrder.Adapter.PopularAdapter;
import com.example.fe_prm.FoodOrder.Domain.OrderDomain;
import com.example.fe_prm.FoodOrder.Domain.CategoryDomain;
import com.example.fe_prm.FoodOrder.Domain.FoodDomain;
import com.example.fe_prm.FoodOrder.Helper.ManagementCart;
import com.example.fe_prm.R;
import com.example.fe_prm.TableReservationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FoodOrder extends AppCompatActivity {
private RecyclerView.Adapter adapter, adapter2;
private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order);

        recyclerViewCategory();
        recycleViewPopular();
        openCart();
        checkOutButton();
    }

    private void checkOutButton() {
        Button btn_Next = findViewById(R.id.btn_Next);

        btn_Next.setOnClickListener(v -> {
            ManagementCart managementCart = new ManagementCart(this);
            List<FoodDomain> cartList = managementCart.getListCart();


            if (!cartList.isEmpty()) {
                startActivity(new Intent(FoodOrder.this, CartListActivity.class));
            }
            else {
                startActivity(new Intent(FoodOrder.this, ConfirmReservation.class));
            }
        });
    }

    private void openCart(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodOrder.this, CartListActivity.class));
            }
        });
    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza", "cat_1"));
        category.add(new CategoryDomain("Buger", "cat_2"));
        category.add(new CategoryDomain("Hotdog", "cat_3"));
        category.add(new CategoryDomain("Drink", "cat_4"));
        category.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recycleViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Hawaii Pizza", "pop_1", "Slices pepperoni, Mozzarella Cheese, fresh oregano, black pepper, sauce", 10000.00));
        foodList.add(new FoodDomain("American Buger", "pop_2", "Juicy Beef, Gouda Cheese, Hot Sauce, Tomato, Lettuce", 7000.00));
        foodList.add(new FoodDomain("Vegetable Pizza", "pop_3", "Olive oil, Vegetable oil, Cherry Tomato, Organic Protein", 20000.00));

        adapter2 = new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);

    }
}