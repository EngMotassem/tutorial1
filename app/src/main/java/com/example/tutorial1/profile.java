package com.example.tutorial1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorial1.helper.LoadingHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference mRef;

    EditText username,email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        mRef=database.getReference("users");


        initviews();

        getUserdata();

    }

    private void initviews() {

        username=findViewById(R.id.usernameText2);
        email=findViewById(R.id.emailText2);
        pass=findViewById(R.id.passet2);
    }

    private void getUserdata() {

        final LoadingHelper loadingHelper=new LoadingHelper();

        loadingHelper.showDialg(this,"please wait","getting your profile data");

        mRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("userName").exists()){

                    String userN= (String) dataSnapshot.child("userName").getValue();

                    username.setText(userN);
                    email.setText(mAuth.getCurrentUser().getEmail());
                   // pass.setText(mAuth.getCurrentUser().);



                }
                loadingHelper.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
