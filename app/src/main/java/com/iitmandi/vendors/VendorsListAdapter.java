package com.iitmandi.vendors;

/**
 * Created by paresh on 11/12/17.
 */

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorsListAdapter extends RecyclerView.Adapter<VendorsListAdapter.MyViewHolder> {
    private int yellow = Color.parseColor("#ffa700");
    private int red = Color.parseColor("#d62d20");
    private int green = Color.parseColor("#008744");
    private List<VendorsListView> vendorsLists;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView vendor_name, vendor_phone_number, vendor_rating;
        public ImageView iv_vendor_profile_image;
        public MyViewHolder(View view) {
            super(view);
            vendor_name = (TextView) view.findViewById(R.id.tv_name);
            vendor_rating = (TextView) view.findViewById(R.id.tv_rating);
            vendor_phone_number = (TextView) view.findViewById(R.id.tv_phone_number);
            iv_vendor_profile_image = (ImageView) view.findViewById(R.id.iv_vendor_profile_image);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("rating",String.valueOf(Float.valueOf(vendor_rating.getText().toString())));

                }
            });
        }
    }


    public VendorsListAdapter(List<VendorsListView> vendorsLists) {
        this.vendorsLists = vendorsLists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vendors_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        VendorsListView scheduleList = vendorsLists.get(position);
        holder.vendor_name.setText(scheduleList.getVendor_name());
        holder.vendor_rating.setText(scheduleList.getVendor_rating());
        holder.vendor_phone_number.setText(scheduleList.getVendor_phone_number());
        if(Float.valueOf(holder.vendor_rating.getText().toString())<=4.0 && Float.valueOf(holder.vendor_rating.getText().toString())>2.0){
            holder.vendor_rating.setBackgroundColor(yellow);
        }else if(Float.valueOf(holder.vendor_rating.getText().toString())<=2.0){
            holder.vendor_rating.setBackgroundColor(red);
        }else {
            holder.vendor_rating.setBackgroundColor(green);
        }
        if(!scheduleList.getVendor_profile_url().toString().equals(null)) {
            Picasso.get().load(scheduleList.getVendor_profile_url().toString()).into(holder.iv_vendor_profile_image);
        }
    }

    @Override
    public int getItemCount() {
        return vendorsLists.size();
    }
}
