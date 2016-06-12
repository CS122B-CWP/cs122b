package com.example.pengyuanfan.fablix.json;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class ShoppingCartItem {
    //String qty;
    String customer_id;
    String item_id;
    String price;
    String title;
    /*
    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }*/

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
