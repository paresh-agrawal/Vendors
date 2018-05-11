package com.iitmandi.vendors;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by paresh on 13/12/17.
 */

public class SimpleFragmentPagerAdapterVendors extends FragmentStatePagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapterVendors(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new VendorsListFruits();
        } else if (position == 1){
            return new VendorsListVegetables();
        } else {
            return new VendorsListMilk();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Fruits";
            case 1:
                return "Vegetables";
            case 2:
                return "Milk";
            default:
                return null;
        }
    }

}
