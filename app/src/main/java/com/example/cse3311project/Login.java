package com.example.cse3311project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Login extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = findViewById(R.id.ETextPersonName);
        Password = findViewById(R.id.ETextPassword);
        Info = findViewById(R.id.ETextInfo);
        Login = findViewById(R.id.LoginButton);


        Login.setOnClickListener(view -> validate(Name.getText().toString(),Password.getText().toString()));

    }
    @SuppressLint("SetTextI18n")
    private void validate(String userName, String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("123"))) {
            Intent loginUser = new Intent(com.example.cse3311project.Login.this, HomePage.class);
            startActivity(loginUser);
        }else{
            counter--;

            Info.setText("Incorrect Password or Username");
            Info.setTextColor(Color.RED);
            if(counter == 0){
                Login.setEnabled(false);
            }
        }
    }

}