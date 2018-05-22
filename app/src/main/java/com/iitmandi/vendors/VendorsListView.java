package com.iitmandi.vendors;

/**
 * Created by paresh on 11/12/17.
 */

public class VendorsListView {
    private String vendor_name, vendor_phone_number, vendor_rating, vendor_profile_url;

    public VendorsListView() {
    }

    public VendorsListView(String vendor_name, String vendor_phone_number, String vendor_rating, String vendor_profile_url) {
        this.vendor_name = vendor_name;
        this.vendor_phone_number = vendor_phone_number;
        this.vendor_rating = vendor_rating;
        this.vendor_profile_url = vendor_profile_url;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_rating() {
        return vendor_rating;
    }

    public void setVendor_rating(String vendor_rating) {
        this.vendor_rating = vendor_rating;
    }

    public String getVendor_phone_number() {
        return vendor_phone_number;
    }

    public void setVendor_phone_number(String vendor_phone_number) {
        this.vendor_phone_number = vendor_phone_number;
    }

    public String getVendor_profile_url() {
        return vendor_profile_url;
    }

    public void setVendor_profile_url(String vendor_profile_url) {
        this.vendor_profile_url = vendor_profile_url;
    }
}
