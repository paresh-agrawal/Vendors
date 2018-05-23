package com.iitmandi.vendors;

/**
 * Created by paresh on 11/12/17.
 */

public class VendorsAddItemView {
    private String item_name, item_rate, item_quantity;

    public VendorsAddItemView() {
    }

    public VendorsAddItemView(String item_name, String item_rate, String item_quantity) {
        this.item_name = item_name;
        this.item_rate = item_rate;
        this.item_quantity = item_quantity;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getItem_rate() {
        return item_rate;
    }

    public void setItem_rate(String item_rate) {
        this.item_rate = item_rate;
    }
}
