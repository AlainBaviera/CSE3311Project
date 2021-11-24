package com.example.cse3311project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText Email, Password;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setTitle("CSE 3311 Project");

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);
        Auth = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> {
            String email = Email.getText().toString();
            String password = Password.getText().toString();

            if(email.isEmpty()){
                Email.setError("Email must be entered!");
                return;
            }
            if(password.isEmpty()){
                Password.setError("Password must be entered!");
                return;
            }

            Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Successful Login", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Login.this, HomePage.class));
                    finish();
                } else
                    Toast.makeText(Login.this, "Password or Email is Incorrect. Try again", Toast.LENGTH_LONG).show();
            });
        });
        register.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, Register.class));
            finish();
        });


    }

}