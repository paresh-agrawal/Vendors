package com.iitmandi.vendors;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class VendorsListFruits extends Fragment {

    private boolean viewIsAtHome;
    //private Fragment fragment = new EventListTechnicalList();
    private RelativeLayout rl_vendor_list_fruits;
    private List<VendorsListView> vendorListListFruits = new ArrayList<>();
    private RecyclerView rv_vendors_fruits;
    private VendorsListAdapter mAdapterFruits;
    private VendorsListView vendorsListFruits;
    private ProgressBar progressBar;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Fruits");

    public VendorsListFruits() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_vendors_list_fruits, container, false);

        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);

        rv_vendors_fruits = (RecyclerView) rootView.findViewById(R.id.rv_vendors_fruits);

        mAdapterFruits = new VendorsListAdapter(vendorListListFruits);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_vendors_fruits.setLayoutManager(mLayoutManager1);
        rv_vendors_fruits.setItemAnimator(new DefaultItemAnimator());
        //rv_vendors_fruits.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
        rv_vendors_fruits.setAdapter(mAdapterFruits);

        prepareVendorsListFruits();
        return rootView;
    }

    private void prepareVendorsListFruits() {
        // Read from the database
        progressBar.setVisibility(View.VISIBLE);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                vendorListListFruits.clear();
                String[] vendor_name = new String[20],vendor_phone = new String[20],
                        vendor_rating = new String[20], vendor_pic_url = new String[20];
                int i = 0;
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    vendor_name[i] = dsp.child("name").getValue().toString();
                    vendor_phone[i] = dsp.child("phone").getValue().toString();
                    vendor_rating[i] = dsp.child("rating").getValue().toString();
                    vendor_pic_url[i] = dsp.child("url").getValue().toString();
                    i++;

                }
                int j=0;
                for(j=0;j<i;j++) {
                    vendorsListFruits = new VendorsListView(vendor_name[j], vendor_phone[j], vendor_rating[j],vendor_pic_url[j]);
                    vendorListListFruits.add(vendorsListFruits);
                }
                Collections.sort(vendorListListFruits,
                        (l2, l1) -> l1.getVendor_rating().compareTo(l2.getVendor_rating()));
                progressBar.setVisibility(View.INVISIBLE);
                mAdapterFruits.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("value", "Failed to read value.", error.toException());
            }
        });
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
