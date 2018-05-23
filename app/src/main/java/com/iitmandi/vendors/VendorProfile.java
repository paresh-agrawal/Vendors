package com.iitmandi.vendors;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VendorProfile extends Fragment {

    private boolean viewIsAtHome;
    private Fragment fragment = new VendorsList();
    //private Fragment fragment = new EventListTechnicalList();
    private RelativeLayout rl_vendor_list_fruits;
    private List<VendorsAddItemView> vendorListListItemsSelling = new ArrayList<>();
    private RecyclerView rv_items_selling;
    private VendorsAddItemAdapter mAdapterItemsSelling;
    private VendorsAddItemView vendorsListItemsSelling;
    private ProgressBar progressBar;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FloatingActionButton fab;
    private EditText et_item_name,et_rate,et_quantity;
    private TextView tv_rate_scale,tv_quantity_scale;
    private Spinner spinner_scale;

    public VendorProfile() {
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
        View add_item_fragment = inflater.inflate(R.layout.fragment_display_item, container, false);
        ((VendorMain) getActivity())
                .setActionBarTitle("Home");

        progressBar = (ProgressBar)add_item_fragment.findViewById(R.id.progressBar);
        rv_items_selling = (RecyclerView) add_item_fragment.findViewById(R.id.rv_items_selling);
        fab = (FloatingActionButton)add_item_fragment.findViewById(R.id.fab);

        mAdapterItemsSelling = new VendorsAddItemAdapter(vendorListListItemsSelling);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_items_selling.setLayoutManager(mLayoutManager1);
        rv_items_selling.setItemAnimator(new DefaultItemAnimator());
        //rv_items_selling.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
        rv_items_selling.setAdapter(mAdapterItemsSelling);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        myRef = database.getReference(currentUser.getDisplayName().toString()).child(currentUser.getPhoneNumber());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        prepareVendorsListFruits();


        return add_item_fragment;
    }

    private void addItem() {
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.dialog_add_item, null);

        final AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setView(promptsView)
                .setTitle("Add Item")
                .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {
                et_item_name = (EditText)promptsView.findViewById(R.id.et_item_name);
                et_rate = (EditText)promptsView.findViewById(R.id.et_rate);
                et_quantity = (EditText)promptsView.findViewById(R.id.et_quantity);
                tv_rate_scale = (TextView) promptsView.findViewById(R.id.tv_rate_scale);
                tv_quantity_scale = (TextView) promptsView.findViewById(R.id.tv_quantity_scale);
                spinner_scale = (Spinner) promptsView.findViewById(R.id.spinner_scale);

                spinner_scale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        // your code here
                        Log.d("item",spinner_scale.getSelectedItem().toString());
                        if(spinner_scale.getSelectedItem().toString().equals("₹/kg")){
                            Log.d("item","kg");
                            tv_rate_scale.setText(" ₹/kg");
                            tv_quantity_scale.setText(" kg");
                        }else if(spinner_scale.getSelectedItem().toString().equals("₹/dozen")){
                            Log.d("item","dozen");
                            tv_rate_scale.setText(" ₹/dozen");
                            tv_quantity_scale.setText(" dozen");
                        }else if(spinner_scale.getSelectedItem().toString().equals("₹/litre")){
                            Log.d("item","litre");
                            tv_rate_scale.setText(" ₹/litre");
                            tv_quantity_scale.setText("litre");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        //Dismiss once everything is OK.
                        if (et_item_name.getText().toString().equals("")){
                            et_item_name.setError("Required");
                        }else if (et_rate.getText().toString().equals("")){
                            et_rate.setError("Required");
                        }else if (et_quantity.getText().toString().equals("")){
                            et_quantity.setError("Required");
                        }else {
                            setValues();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        dialog.show();

                /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getContext());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setTitle("Add Item");
                alertDialogBuilder.setView(promptsView);

                EditText et_item_name = (EditText)promptsView.findViewById(R.id.et_item_name);
                EditText et_rate = (EditText)promptsView.findViewById(R.id.et_rate);
                EditText et_quantity = (EditText)promptsView.findViewById(R.id.et_quantity);
                TextView tv_rate_scale = (TextView) promptsView.findViewById(R.id.tv_rate_scale);
                TextView tv_quantity_scale = (TextView) promptsView.findViewById(R.id.tv_quantity_scale);
                Spinner spinner_scale = (Spinner) promptsView.findViewById(R.id.spinner_scale);

                spinner_scale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        // your code here
                        Log.d("item",spinner_scale.getSelectedItem().toString());
                        if(spinner_scale.getSelectedItem().toString().equals("₹/kg")){
                            Log.d("item","kg");
                            tv_rate_scale.setText(" ₹/kg");
                            tv_quantity_scale.setText(" kg");
                        }else if(spinner_scale.getSelectedItem().toString().equals("₹/dozen")){
                            Log.d("item","dozen");
                            tv_rate_scale.setText(" ₹/dozen");
                            tv_quantity_scale.setText(" dozen");
                        }else if(spinner_scale.getSelectedItem().toString().equals("₹/litre")){
                            Log.d("item","litre");
                            tv_rate_scale.setText(" ₹/litre");
                            tv_quantity_scale.setText("litre");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        myRef.child("items").child(et_item_name.getText().toString()).child("rate")
                                                .setValue(et_rate.getText().toString()+ tv_rate_scale.getText().toString());
                                        myRef.child("items").child(et_item_name.getText().toString()).child("quantity")
                                                .setValue(et_quantity.getText().toString()+ tv_quantity_scale.getText().toString());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // set dialog message


                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();*/
    }

    private void setValues() {
        myRef.child("items").child(et_item_name.getText().toString()).child("rate")
                .setValue(et_rate.getText().toString()+ tv_rate_scale.getText().toString());
        myRef.child("items").child(et_item_name.getText().toString()).child("quantity")
                .setValue(et_quantity.getText().toString()+ tv_quantity_scale.getText().toString());
    }

    private void prepareVendorsListFruits() {
        // Read from the database
        progressBar.setVisibility(View.VISIBLE);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild("items")) {
                    myRef.child("items").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            vendorListListItemsSelling.clear();
                            String[] item_name = new String[20],item_rate = new String[20],
                                    item_quantity = new String[20];
                            int i = 0;
                            for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                item_name[i] = dsp.getKey().toString();
                                if(dsp.hasChild("rate")){
                                    item_rate[i] = dsp.child("rate").getValue().toString();
                                }else{
                                    item_rate[i] = null;
                                }
                                if(dsp.hasChild("quantity")){
                                    item_quantity[i] = dsp.child("quantity").getValue().toString();
                                }else{
                                    item_quantity[i] = null;
                                }
                                i++;
                            }
                            int j=0;
                            for(j=0;j<i;j++) {
                                vendorsListItemsSelling = new VendorsAddItemView(item_name[j], item_rate[j], item_quantity[j]);
                                vendorListListItemsSelling.add(vendorsListItemsSelling);
                            }
                            Collections.sort(vendorListListItemsSelling,
                                    (l2, l1) -> l2.getItem_name().toLowerCase().compareTo(l1.getItem_name().toLowerCase()));
                            progressBar.setVisibility(View.INVISIBLE);
                            mAdapterItemsSelling.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("value", "Failed to read value.", error.toException());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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