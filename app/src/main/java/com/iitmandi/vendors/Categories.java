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
public class Categories extends Fragment {

    private boolean viewIsAtHome;
    private Fragment fragment = new VendorsList();

    public Categories() {
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
        View categories_fragment = inflater.inflate(R.layout.fragment_categories, container, false);
        ((MainActivity) getActivity())
                .setActionBarTitle(getResources().getString(R.string.app_name));

        RelativeLayout rl_fruits = (RelativeLayout)categories_fragment.findViewById(R.id.rl_fruits);
        RelativeLayout rl_vegetables = (RelativeLayout)categories_fragment.findViewById(R.id.rl_vegetables);
        RelativeLayout rl_milk = (RelativeLayout)categories_fragment.findViewById(R.id.rl_milk);

        rl_fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_fruits);
            }
        });
        rl_vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_vegetables);
            }
        });
        rl_milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(R.id.rl_milk);
            }
        });

        return categories_fragment;
    }

    public void displayView(int viewId) {

        String title = getString(R.string.app_name);
        Bundle bundle = new Bundle();
        switch (viewId) {

            case R.id.rl_fruits:
                bundle.putInt("key",0);
                break;
            case R.id.rl_vegetables:
                bundle.putInt("key",1);
                break;
            case R.id.rl_milk:
                bundle.putInt("key",2);
                break;

        }
        fragment.setArguments(bundle);
        viewIsAtHome = true;
        title  = "Vendors List";
        if (fragment != null) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

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