package com.example.cse3311project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLOutput;

public class Sell extends AppCompatActivity {

    String itemName, category, username;
    double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        Button Submit = findViewById(R.id.SubmitButton);
        EditText ItemNameInput = findViewById(R.id.ItemName);
        EditText ItemPrice = findViewById(R.id.ItemPrice);
        EditText ItemCategories = findViewById(R.id.ItemCategory);
        EditText ItemUsername = findViewById(R.id.ItemUsername);


        Submit.setOnClickListener(view -> {

            itemName = ItemNameInput.getText().toString();
            price = Integer.parseInt(ItemPrice.getText().toString());
            category = ItemCategories.getText().toString();
            username = ItemUsername.getText().toString();

        });
    }
}