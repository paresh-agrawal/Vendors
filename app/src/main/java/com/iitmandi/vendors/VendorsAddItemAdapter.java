package com.iitmandi.vendors;

/**
 * Created by paresh on 11/12/17.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class VendorsAddItemAdapter extends RecyclerView.Adapter<VendorsAddItemAdapter.MyViewHolder> {
    private List<VendorsAddItemView> addItemLists;

    public EditText et_rate,et_quantity;
    public TextView tv_rate_scale,tv_quantity_scale;
    public Spinner spinner_scale;
    private FirebaseDatabase editDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myEditRef;
    private FirebaseAuth mEditAuth;
    private FirebaseUser currentEditUser;
    private String item_edit_name;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name, item_rate, item_quantity;
        public ImageView iv_edit, iv_delete;
        public FirebaseDatabase database = FirebaseDatabase.getInstance();
        public DatabaseReference myRef;
        public FirebaseAuth mAuth;
        public FirebaseUser currentUser;


        public MyViewHolder(View view) {
            super(view);
            item_name = (TextView) view.findViewById(R.id.item_name);
            item_quantity = (TextView) view.findViewById(R.id.item_quantity);
            item_rate = (TextView) view.findViewById(R.id.item_rate);
            iv_edit = (ImageView)view.findViewById(R.id.iv_edit);
            iv_delete = (ImageView)view.findViewById(R.id.iv_delete);
            mAuth = FirebaseAuth.getInstance();
            currentUser = mAuth.getCurrentUser();
            myRef = database.getReference(currentUser.getDisplayName().toString()).child(currentUser.getPhoneNumber()).child("items");
        }
    }


    public VendorsAddItemAdapter(List<VendorsAddItemView> addItemLists) {
        this.addItemLists = addItemLists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_selling_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        VendorsAddItemView scheduleList = addItemLists.get(position);
        holder.item_name.setText(scheduleList.getItem_name());
        holder.item_quantity.setText(scheduleList.getItem_quantity());
        holder.item_rate.setText(scheduleList.getItem_rate());
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(holder.item_name.getContext());
                alertDialogBuilder.setMessage("Are you sure, you want to delete this item?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        holder.myRef.child(holder.item_name.getText().toString()).removeValue();
                    }
                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(holder.item_name.getContext());
                View promptsView = li.inflate(R.layout.dialog_edit_item, null);

                final AlertDialog dialog = new AlertDialog.Builder(holder.item_name.getContext())
                        .setView(promptsView)
                        .setTitle("Edit : " + holder.item_name.getText().toString())
                        .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                        .setNegativeButton(android.R.string.cancel, null)
                        .create();

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {

                    @Override
                    public void onShow(DialogInterface dialogInterface) {
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
                                if (et_rate.getText().toString().equals("")){
                                    et_rate.setError("Required");
                                }else if (et_quantity.getText().toString().equals("")){
                                    et_quantity.setError("Required");
                                }else {
                                    item_edit_name = holder.item_name.getText().toString();
                                    setValues();
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
    }

    private void setValues() {
        mEditAuth = FirebaseAuth.getInstance();
        currentEditUser = mEditAuth.getCurrentUser();
        myEditRef = editDatabase.getReference(currentEditUser.getDisplayName().toString()).child(currentEditUser.getPhoneNumber());
        myEditRef.child("items").child(item_edit_name).child("rate")
                .setValue(et_rate.getText().toString()+ tv_rate_scale.getText().toString());
        myEditRef.child("items").child(item_edit_name).child("quantity")
                .setValue(et_quantity.getText().toString()+ tv_quantity_scale.getText().toString());
    }

    @Override
    public int getItemCount() {
        return addItemLists.size();
    }
}
