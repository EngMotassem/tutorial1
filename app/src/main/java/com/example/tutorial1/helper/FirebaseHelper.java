package com.example.tutorial1.helper;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseHelper <T> {


   public String getKey(String node){


       FirebaseDatabase database=FirebaseDatabase.getInstance();
       DatabaseReference mRef=database.getReference(node);
       return  mRef.push().getKey();
   }

   public Task<Void> saveData(String node,String key,T data){
       FirebaseDatabase database=FirebaseDatabase.getInstance();
       DatabaseReference mRef=database.getReference(node+"/"+key);
       return mRef.setValue(data);
   }

   public Task<Void> deletData(String node,String key){
       FirebaseDatabase database=FirebaseDatabase.getInstance();
       DatabaseReference mRef=database.getReference(node+"/"+key);
       return mRef.removeValue();

   }

    public void getAll(String node, ValueEventListener eventListener){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(node);
        myRef.addValueEventListener(eventListener);
    }




  //  FirebaseAuth pumauth;

}
