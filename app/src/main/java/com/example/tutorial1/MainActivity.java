package com.example.tutorial1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorial1.helper.LoadingHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText email,passord,username;
    Button  signUp,login;
    private FirebaseAuth mAuth;


    private String uid;
    LoadingHelper loadingHelper=new LoadingHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiViews();

        mAuth = FirebaseAuth.getInstance();


    }

    private void intiViews() {

         email=findViewById(R.id.emailText);
         username=findViewById(R.id.usernameText);
         passord=findViewById(R.id.passet);
         signUp=findViewById(R.id.button);
         login=findViewById(R.id.login);




         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent logIN=new Intent(getApplicationContext(), login.class);
                 startActivity(logIN);
             }
         });





         signUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if(validateInputs())

                 {


                     AddNewUser();















                 }
             }
         });






    }

    private void getuserUI() {

        uid =mAuth.getCurrentUser().getUid();




    }

    private void showProgress(){

        loadingHelper.showDialg(this,"please wait","adding new user ");

    }

    private void hideProgress(){
        loadingHelper.dismiss();

    }

    private void saveToFirebase(){



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("users"+"/"+uid);

        HashMap<String, String> UserData = new HashMap<String, String>();

        UserData.put("userName",username.getText().toString());

        UserData.put("uid",uid);

        UserData.put("level","customer");


        myRef.setValue(UserData);


    }

    private void AddNewUser() {

        showProgress();


        mAuth.createUserWithEmailAndPassword(email.getText().toString(),passord.getText().toString())
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                           // Toast.makeText(MainActivity.this,"add successfully",Toast.LENGTH_LONG).show();
                            uid=task.getResult().getUser().getUid();

                            saveToFirebase();


                           // Toast.makeText(MainActivity.this,uid,Toast.LENGTH_LONG).show();

                            hideProgress();

                            senUserToMainTabs();




                        }
                        else{

                            Toast.makeText(MainActivity.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                            hideProgress();


                        }





                    }
                });


        //FirebaseUser firebaseUser=mAuth.getCurrentUser();
        //uid=firebaseUser.getUid();


    }

    private void senUserToMainTabs() {

        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }

    private boolean validateInputs() {


        if(TextUtils.isEmpty(email.getText())){

            Toast.makeText(this,"please add your email",Toast.LENGTH_LONG).show();
            return false;
        }

        else if(TextUtils.isEmpty(passord.getText())){

            Toast.makeText(this,"please add pass",Toast.LENGTH_LONG).show();
            return false;
        }
        else return true;





    }
}
