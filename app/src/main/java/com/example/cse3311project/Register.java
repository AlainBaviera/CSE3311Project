package com.example.cse3311project;

import android.content.Intent;
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

    private EditText   textEmail, textPassword, textUsername, textProfession, textUtaId;
    private FirebaseAuth rAuth;
    private final FirebaseDatabase rDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = rDatabase.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textEmail = findViewById(R.id.emailRegister);
        textPassword = findViewById(R.id.passwordRegister);
        textUsername = findViewById(R.id.usernameRegister);
        textProfession = findViewById(R.id.professionRegister);
        textUtaId = findViewById(R.id.utaidRegister);
        Button registerButton = findViewById(R.id.registerButton);

        rAuth = FirebaseAuth.getInstance();

       registerButton.setOnClickListener(v -> {
           String email = textEmail.getText().toString();
           String password = textPassword.getText().toString();
           String username = textUsername.getText().toString();
           String profession = textProfession.getText().toString();
           String utaid = textUtaId.getText().toString();

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
               textUsername.setError("Username can only contain letters and numbers!");
               return;
           }
           if(username.isEmpty()){
               textUsername.setError("Username must be entered!");
               return;
           }
           if(profession.isEmpty()){
               textProfession.setError("Profession must be entered!");
               return;
           }
           if(!(profession.matches("[a-zA-Z]*"))){
               textProfession.setError("Username can only contain letters!");
               return;
           }
           if(utaid.isEmpty()){
               textUtaId.setError("UTA ID must be entered!");
               return;
           }
           if(!(utaid.matches("[0-9]*"))){
               textUtaId.setError("UTA ID can only contain numbers!");
               return;
           }

           registerUser(email, password, username, profession, utaid);
       });
    }

    private void registerUser(String email, String password, String username, String profession, String utaid) {
        rAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(Register.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                User user = new User(email, password, username, profession, utaid);

                databaseReference.child(username).setValue(user);
                startActivity(new Intent(Register.this, Login.class));
            }else
                Toast.makeText(Register.this, "SRegistration failed! Try again", Toast.LENGTH_SHORT).show();
        });
    }
}