package com.example.fe_prm.FoodOrder.Domain;

import java.util.List;

public class OrderDomain {
    private List<FoodDomain> cart;
    private int id;

    public OrderDomain(List<FoodDomain> cart, int id) {
        this.cart = cart;
        this.id = id;
    }

    public List<FoodDomain> getCart() {
        return cart;
    }

    public void setCart(List<FoodDomain> cart) {
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
