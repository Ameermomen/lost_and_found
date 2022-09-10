package com.ameermomen.lostandfind.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ameermomen.lostandfind.R;
import com.ameermomen.lostandfind.Utils.Database;
import com.ameermomen.lostandfind.Utils.User;
import com.ameermomen.lostandfind.interfaces.AuthCallBack;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {
    private TextInputLayout signup_TIL_fullName;
    private TextInputLayout signup_TIL_email;
    private TextInputLayout signup_TIL_phone;
    private TextInputLayout signup_TIL_password;
    private TextInputLayout signup_TIL_confirmPassword;
    private MaterialButton signup_BTN_signup;
    private TextView signup_TV_msg;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViews();
        initVars();
    }

    private void initVars() {
        database = new Database();
        database.setAuthCallBack(new AuthCallBack() {
            @Override
            public void onCreateAccountComplete(boolean status, String msg) {
                if(status){
                    User user = getUser();
                    user.setUid(database.getCurrentUser().getUid());
                    database.updateUserInfo(user);
                }else{
                    Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void updateUserInfoComplete(boolean status, String msg) {
                if(status){
                    database.logout();
                    Toast.makeText(SignupActivity.this,
                            "your account has been created successfully ",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLoginComplete(boolean status, String msg) {

            }

            @Override
            public void fetchUserInfoComplete(User user) {

            }
        });

        signup_BTN_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkInputs()) return;
                User user = getUser();
                database.createNewUser(user);
            }
        });
    }

    private void findViews() {
        signup_TIL_fullName = findViewById(R.id.signup_TIL_fullName);
        signup_TIL_email = findViewById(R.id.signup_TIL_email);
        signup_TIL_phone = findViewById(R.id.signup_TIL_phone);
        signup_TIL_password = findViewById(R.id.signup_TIL_password);
        signup_TIL_confirmPassword = findViewById(R.id.signup_TIL_confirmPassword);
        signup_BTN_signup = findViewById(R.id.signup_BTN_signup);
        signup_TV_msg = findViewById(R.id.signup_TV_msg);

    }

    private boolean checkInputs(){
        return true;
    }

    private User getUser(){
        return  new User()
                .setName(signup_TIL_fullName.getEditText().getText().toString())
                .setEmail(signup_TIL_email.getEditText().getText().toString())
                .setPassword(signup_TIL_password.getEditText().getText().toString())
                .setPhone(signup_TIL_phone.getEditText().getText().toString());
    }


}