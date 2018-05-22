package com.iitmandi.vendors;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorInfo extends AppCompatActivity {

    private CircleImageView add_image, profile_image;
    private int image=0;
    private EditText et_name, et_phone;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Button bt_save;
    private Uri photo_uri;
    private Spinner spinner_category;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference storageImageRef;
    private UploadTask uploadTask;
    private ProgressBar progressBar;
    private ImageView log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        add_image = (CircleImageView)findViewById(R.id.add_image);
        profile_image = (CircleImageView)findViewById(R.id.profile_image);
        et_name = (EditText)findViewById(R.id.et_name);
        et_phone = (EditText)findViewById(R.id.et_phone);
        bt_save = (Button)findViewById(R.id.bt_save);
        spinner_category = (Spinner)findViewById(R.id.spinner_category);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        log_out = (ImageView)findViewById(R.id.log_out);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        et_phone.setText(currentUser.getPhoneNumber().toString());

        database = FirebaseDatabase.getInstance();

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        // Create a reference to 'images/sample.jpg'
        storageImageRef = storageRef.child("vendor_profile/"+currentUser.getPhoneNumber().toString()+".jpg");

        // While the file names are the same, the references point to different files
        storageImageRef.getName().equals(storageImageRef.getName());    // true
        storageImageRef.getPath().equals(storageImageRef.getPath());    // false

        /*et_name.setText(currentUser.getDisplayName());*/

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectImageClick(getWindow().getDecorView());
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (image==1 && !et_name.getText().toString().equals("")){
                    progressBar.setVisibility(View.VISIBLE);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    /*UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(et_name.getText().toString())
                            .setPhotoUri(photo_uri)
                            .build();

                    currentUser.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("profile_image", "User profile updated.");
                                    }
                                }
                            });

                    spinner_category.getSelectedItem().toString();*/
                    myRef = database.getReference(spinner_category.getSelectedItem().toString());
                    myRef = myRef.child(currentUser.getPhoneNumber().toString());
                    myRef.child("name").setValue(et_name.getText().toString());
                    myRef.child("phone").setValue(et_phone.getText().toString());
                    myRef.child("rating").setValue("0");
                    myRef.child("users").setValue("0");

                    uploadTask = storageImageRef.putFile(photo_uri);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            //Log.d("uplaod","unsuccessful");
                            Snackbar.make(getWindow().getDecorView(), "Could not upload your profile please check your internet connection.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            progressBar.setVisibility(View.GONE);
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // Got the download URL for 'users/me/profile.png'
                                    myRef.child("url").setValue(uri.toString());
                                    Snackbar.make(getWindow().getDecorView(), "Your profile is updated.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(spinner_category.getSelectedItem().toString())
                                            .build();

                                    currentUser.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.d("user", "User profile updated.");
                                                    }
                                                }
                                            });
                                    progressBar.setVisibility(View.GONE);
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    startActivity(new Intent(VendorInfo.this, VendorMain.class));
                                    finish();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                    Snackbar.make(getWindow().getDecorView(), "Could not upload your profile please check your internet connection.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                    progressBar.setVisibility(View.GONE);
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                }
                            });
                        }
                    });




                }else if(et_name.getText().toString().equals("")){
                    et_name.setError("Required");
                }else{
                    Snackbar.make(getWindow().getDecorView(), "Upload your photo.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void signOut() {
        mAuth.signOut();
        startActivity(new Intent(VendorInfo.this, VendorSignIn.class));
        finish();
    }

    public void onSelectImageClick(View view) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setActivityTitle("Crop")
                .setCropShape(CropImageView.CropShape.OVAL)
                .setCropMenuCropButtonTitle("Done")
                .setRequestedSize(400, 400)
                .setAspectRatio(1,1)
                .setCropMenuCropButtonIcon(R.drawable.ic_done)
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                photo_uri = result.getUri();
                profile_image.setPadding(0,0,0,0);
                profile_image.setImageURI(result.getUri());
                image = 1;
                /*//upload cropped image to firebase storage
                Snackbar.make(getWindow().getDecorView(), "Uploading...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                uploadTask = storageImageRef.putFile(result.getUri());
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        //Log.d("uplaod","unsuccessful");
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Snackbar.make(getWindow().getDecorView(), "Your photo has been uploaded.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });*/

                //Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                //Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
                Snackbar.make(getWindow().getDecorView(), "Your photo could not be uploaded.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

    @Override
    protected void onStart() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser.getDisplayName().toString().equals("Fruits") || currentUser.getDisplayName().toString().equals("Vegetables") || currentUser.getDisplayName().toString().equals("Milk")){
            startActivity(new Intent(VendorInfo.this, VendorMain.class));
            finish();
        }
        super.onStart();
    }
}
