package com.iitmandi.vendors;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;



/**
 * A simple {@link Fragment} subclass.
 */
public class AddItem extends Fragment {

    private boolean viewIsAtHome;
    private Fragment fragment = new VendorsList();

    public AddItem() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View add_item_fragment = inflater.inflate(R.layout.fragment_add_item, container, false);
        ((VendorMain) getActivity())
                .setActionBarTitle("Home");




        return add_item_fragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}