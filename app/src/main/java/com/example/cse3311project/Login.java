package com.example.cse3311project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Login extends AppCompatActivity {
    private EditText Email, Password;
    private FirebaseAuth Auth;
    final Query userQuery = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("email");
    SharedPreferences sp;
    ArrayList<String> arrSharedPrefs = new ArrayList<>();

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

        sp = getSharedPreferences("User", Context.MODE_PRIVATE);

        login.setOnClickListener(v -> {
            String email = Email.getText().toString();
            String password = Password.getText().toString();

            if(email.isEmpty()){
                Email.setError("Email must be entered!");
                return;
            }
            if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
                Email.setError("Invalid Email Address!");
                return;
            }
            if(password.isEmpty()){
                Password.setError("Password must be entered!");
                return;
            }

            Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Successful Login", Toast.LENGTH_LONG).show();
                    userQuery.equalTo(email).addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                        // result
                                        arrSharedPrefs.add(Objects.requireNonNull(snapshot.getValue()).toString());
                                        String key = snapshot.getKey();
                                        String email = (String) snapshot.child("email").getValue();
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putString("Username",key);
                                        editor.putString("Email",email);
                                        editor.apply();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            }
                    );

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