package com.ameermomen.lostandfind.Utils;

import androidx.annotation.NonNull;

import com.ameermomen.lostandfind.interfaces.AuthCallBack;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Database {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;
    private AuthCallBack authCallBack;

    public Database(){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://lost-and-found-6e8ce-default-rtdb.europe-west1.firebasedatabase.app");
        mStorage = FirebaseStorage.getInstance();
    }

    public void setAuthCallBack(AuthCallBack authCallBack){
        this.authCallBack = authCallBack;
    }

    public void logout() {
        mAuth.signOut();
    }

    public void login(User user){
       mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()) {
                   authCallBack.onLoginComplete(true, "");
               }else{
                   authCallBack.onLoginComplete(false, task.getException().getMessage());
               }
           }
       }) ;
    }

    public void createNewUser(User user){
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            authCallBack.onCreateAccountComplete(true, "");
                        }else{
                            authCallBack.onCreateAccountComplete(false,
                                    task.getException().getMessage());
                        }
                    }
                });
    }

    public void updateUserInfo(User user) {
        mDatabase.getReference("Users").child(user.getUid()).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            authCallBack.updateUserInfoComplete(true, "");
                        }else{
                            authCallBack.updateUserInfoComplete(false, task.getException().getMessage());
                        }
                    }
                });
    }

    public FirebaseUser getCurrentUser(){
        return this.mAuth.getCurrentUser();
    }
}
