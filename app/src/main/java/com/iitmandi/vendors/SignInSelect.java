package com.iitmandi.vendors;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInSelect extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_select);
        Log.d("activity","signinselect");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth = FirebaseAuth.getInstance();
        TextView tv_vendor_sign_in = (TextView)findViewById(R.id.tv_vendor_sign_in);
        TextView tv_buyer_sign_in = (TextView)findViewById(R.id.tv_buyer_sign_in);

        tv_vendor_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInSelect.this, VendorSignIn.class));
                finish();
            }
        });
        tv_buyer_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInSelect.this, BuyerSignIn.class));
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d("onstart", "select");
        if(currentUser==null){
            return;
        }else{
            if(currentUser.getProviders().toString().equals("[google.com]")){
                Log.d("google",currentUser.getProviders().toString());
                startActivity(new Intent(SignInSelect.this, BuyerSignIn.class));
                finish();
            }else if(currentUser.getProviders().toString().equals("[phone]")){
                Log.d("google",currentUser.getProviders().toString());
                startActivity(new Intent(SignInSelect.this, VendorSignIn.class));
                finish();
            }
        }
    }
}
