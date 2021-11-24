package com.example.cse3311project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText   textEmail, textPassword;
    private FirebaseAuth rAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textEmail = findViewById(R.id.emailRegister);
        textPassword = findViewById(R.id.passwordRegister);
        Button registerButton = findViewById(R.id.registerButton);

        rAuth = FirebaseAuth.getInstance();

       registerButton.setOnClickListener(v -> {
           String email = textEmail.getText().toString();
           String password = textPassword.getText().toString();

           if(email.isEmpty()){
               textEmail.setError("Email must be entered!");
               return;
           }
           if(password.isEmpty()){
               textPassword.setError("Password must be entered!");
               return;
           }
           if(password.length() < 6){
               textPassword.setError("Password must be longer than 6 characters!");
               return;
           }
           registerUser(email, password);
       });
    }

    private void registerUser(String email, String password) {
        rAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(Register.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Register.this, Login.class));
            }else
                Toast.makeText(Register.this, "SRegistration failed! Try again", Toast.LENGTH_SHORT).show();
        });
    }
}