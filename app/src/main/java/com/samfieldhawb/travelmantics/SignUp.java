package com.samfieldhawb.travelmantics;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText mEmailField, mNameField, mPasswordField;
    Button mButton;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailField = findViewById(R.id.email);
        mNameField = findViewById(R.id.name);

        mPasswordField = findViewById(R.id.password);
        mButton = findViewById(R.id.save_btn);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuth = FirebaseAuth.getInstance();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegistration();
            }
        });

    }

    public void doRegistration(){
        String email = mEmailField.getText().toString().trim();
        final String name = mNameField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String userId = mAuth.getCurrentUser().getUid();
                    DatabaseReference currentUserDb = databaseReference.push().child(userId);
                    currentUserDb.child("Name").setValue(name);
                    Intent mainIntent = new Intent(getApplicationContext(),UserActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);
                }
            }
        });
    }
}
