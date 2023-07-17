package com.example.fe_prm.FoodOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fe_prm.FoodOrder.Activity.CartListActivity;
import com.example.fe_prm.FoodOrder.Adapter.CategoryAdapter;
import com.example.fe_prm.FoodOrder.Adapter.PopularAdapter;
import com.example.fe_prm.FoodOrder.Domain.CategoryDomain;
import com.example.fe_prm.FoodOrder.Domain.FoodDomain;
import com.example.fe_prm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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
        foodList.add(new FoodDomain("Hawaii Pizza", "pop_1", "Slices pepperoni, Mozzarella Cheese, fresh oregano, black pepper, sauce", 10.49));
        foodList.add(new FoodDomain("American Buger", "pop_2", "Juicy Beef, Gouda Cheese, Hot Sauce, Tomato, Lettuce", 6.99));
        foodList.add(new FoodDomain("Vegetable Pizza", "pop_3", "Olive oil, Vegetable oil, Cherry Tomato, Organic Protein", 11.89));

        adapter2 = new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);

    }
}