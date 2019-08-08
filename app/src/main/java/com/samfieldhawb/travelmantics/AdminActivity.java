package com.samfieldhawb.travelmantics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AdminActivity extends AppCompatActivity {
    EditText mTitle, mPrice, mLocation;
    Button mSelectImageButton;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mTitle = findViewById(R.id.title);
        mPrice = findViewById(R.id.price);
        mLocation = findViewById(R.id.location);
        mSelectImageButton = findViewById(R.id.select_image_btn);
        mImageView = findViewById(R.id.selected_image);
    }
}
