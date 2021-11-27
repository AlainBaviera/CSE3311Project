package com.example.cse3311project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText   textEmail, textPassword, textUsername;
    private FirebaseAuth rAuth;
    private final FirebaseDatabase rDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = rDatabase.getReference("Users");
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textEmail = findViewById(R.id.emailRegister);
        textPassword = findViewById(R.id.passwordRegister);
        textUsername = findViewById(R.id.usernameRegister);
        Button registerButton = findViewById(R.id.registerButton);

        rAuth = FirebaseAuth.getInstance();

       registerButton.setOnClickListener(v -> {
           String email = textEmail.getText().toString();
           String password = textPassword.getText().toString();
           String username = textUsername.getText().toString();

           if(email.isEmpty()){
               textEmail.setError("Email must be entered!");
               return;
           }
           if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
               textEmail.setError("Invalid Email Address!");
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
           if(!(username.matches("[a-zA-Z0-9]*"))){
               textUsername.setError("Password can only contain letters and numbers!");
               return;
           }

           registerUser(email, password, username);
       });
    }

    private void registerUser(String email, String password, String username) {
        rAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(Register.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                User user = new User(email, password, username);

                databaseReference.child(username).setValue(user);
                startActivity(new Intent(Register.this, Login.class));
            }else
                Toast.makeText(Register.this, "SRegistration failed! Try again", Toast.LENGTH_SHORT).show();
        });
    }
}