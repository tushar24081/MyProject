package com.example.payalkirana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button btn_send, btn_home;
    TextInputLayout regName,passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         regName = (TextInputLayout) findViewById(R.id.username);
         passWord = (TextInputLayout) findViewById(R.id.pass);
        btn_send = (Button) findViewById(R.id.btn_start);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    //Validate Login Info
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
                finish();
            }
        });

    }
    public void sendData(View view){
        if (!validateName() | !validatePass()) {
            return;
        }
        else
        {
            isUser();
        }
    }

    private void isUser() {
        final String UserEnteredUsername = regName.getEditText().getText().toString().trim();
        final String UserEnteredPassword = passWord.getEditText().getText().toString().trim();
         DatabaseReference reference= FirebaseDatabase.getInstance().getReference("user");
        Query checkUser= reference.orderByChild("username").equalTo(UserEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {

                    regName.setError(null);
                    regName.setErrorEnabled(false);
                    String passwordFromDB = dataSnapshot.child(UserEnteredUsername).child("password").getValue(String.class);


                    if(passwordFromDB!=null && passwordFromDB.equals(UserEnteredPassword))
                    {
                        passWord.setError(null);
                        passWord.setErrorEnabled(false);

                        String nameFromDB = dataSnapshot.child(UserEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(UserEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(UserEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(UserEnteredUsername).child("email").getValue(String.class);

                        Intent intent=new Intent(getApplicationContext(),UserProfile.class);
                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("phoneNo",phoneNoFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("password",passwordFromDB);
                        startActivity(intent);

                    }
                    else
                    {
                        passWord.setError("Your Password is Wrong :(");
                        passWord.requestFocus();

                    }
                }
                else
                {
                    regName.setError("No User Available");
                    regName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("Please Enter Your Name ");
            return false;

        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePass() {
        String val = passWord.getEditText().getText().toString();
        if (val.isEmpty()) {
            passWord.setError("Please Enter Your Password ");
            return false;

        } else {
            passWord.setError(null);
            passWord.setErrorEnabled(false);
            return true;
        }
    }
}
