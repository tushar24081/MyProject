package com.example.payalkirana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

        TextView fullNameLabel,usernameLabel;
        TextInputLayout fullName,email,phoneNo,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //Hooks
        fullName = (TextInputLayout) findViewById(R.id.l1);
        email = (TextInputLayout) findViewById(R.id.l2);
        phoneNo =(TextInputLayout) findViewById(R.id.l3);
        password =(TextInputLayout) findViewById(R.id.l4);
        fullNameLabel =findViewById(R.id.main);
        usernameLabel = findViewById(R.id.sub);
        showAllUserData();
    }


        private void showAllUserData() {
            Intent intent = getIntent();
            String user_username = intent.getStringExtra("username");
            String user_name = intent.getStringExtra("name");
            String user_email = intent.getStringExtra("email");
            String user_phoneNo = intent.getStringExtra("phoneNo");
            String user_password = intent.getStringExtra("password");


            fullNameLabel.setText(user_name);
            usernameLabel.setText(user_username);
            fullName.getEditText().setText(user_name);
            email.getEditText().setText(user_email);
            phoneNo.getEditText().setText(user_phoneNo);
            password.getEditText().setText(user_password);

        }
}
