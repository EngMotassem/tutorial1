package com.example.tutorial1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tutorial1.helper.LoadingHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kaopiz.kprogresshud.KProgressHUD;

public class login extends AppCompatActivity {

    EditText email,passord;
    Button signUp,login;
    private FirebaseAuth mAuth;
    KProgressHUD hud;

    private ProgressBar simpleProgressBar;

    private ProgressDialog progressDialog;

    LoadingHelper loadingHelper=new LoadingHelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intiViews();

        mAuth= FirebaseAuth.getInstance();
        //LoadingHelper loadingHelper=new LoadingHelper();


    }

    private void showProgress(){

        loadingHelper.showDialg(this,"please wait","check details");

    }

    private void hideProgress(){
       loadingHelper.dismiss();

    }


    private void intiViews() {

        email=findViewById(R.id.emailText3);
        passord=findViewById(R.id.passet3);

        signUp=findViewById(R.id.signUp);

        simpleProgressBar=findViewById(R.id.progressBar);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpAC=new Intent(getApplicationContext(),MainActivity.class);

                startActivity(signUpAC);
            }
        });

        login=findViewById(R.id.loginBt);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInputs()){

                    checkUserlogindetails();
                }
            }
        });
    }

    private void checkUserlogindetails() {

        showProgress();

        mAuth.signInWithEmailAndPassword(email.getText().toString(),passord.getText().toString())
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"logged",Toast.LENGTH_LONG).show();

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference().child("users"+"/"+mAuth.getCurrentUser().getUid());

                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.child("level").exists()){

                                        String userLevel=dataSnapshot.child("level").getValue().toString();

                                        Toast.makeText(getApplicationContext(),userLevel,Toast.LENGTH_LONG).show();

                                        if(TextUtils.equals(userLevel,"customer")){
                                            Intent signUpAC=new Intent(getApplicationContext(),CustomerDashboard.class);

                                            startActivity(signUpAC);




                                        }



                                    }
                                    else{

                                        gotoMain();

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                        else{

                          Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
                        }
                        hideProgress();

                    }
                });


    }

    private void gotoMain() {

        Intent signUpAC=new Intent(getApplicationContext(),Main2Activity.class);

        startActivity(signUpAC);


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
