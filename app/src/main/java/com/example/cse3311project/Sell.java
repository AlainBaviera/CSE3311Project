package com.example.cse3311project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sell extends AppCompatActivity {

    private final FirebaseDatabase rDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = rDatabase.getReference("Items");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        Button Submit = findViewById(R.id.SubmitButton);
        EditText ItemNameInput = findViewById(R.id.ItemName);
        EditText ItemPrice = findViewById(R.id.ItemPrice);
        EditText ItemCategories = findViewById(R.id.ItemCategory);

        Submit.setOnClickListener(view -> {

            String itemName = ItemNameInput.getText().toString();
            String itemPrice = ItemPrice.getText().toString();
            float price = Float.parseFloat(itemPrice);
            String category = ItemCategories.getText().toString();

            if(itemName.isEmpty() && itemName.matches("[a-zA-Z0-9 #/()]*")){
                ItemNameInput.setError("Valid Item Name must be entered!");
                return;
            }
            if(itemPrice.isEmpty()  && category.matches("[0-9.]*")){
                ItemPrice.setError("Valid Price must be entered!");
                return;
            }
            if(category.isEmpty() && category.matches("[a-zA-Z.]*")){
                ItemCategories.setError("Valid Category must be entered!");
                return;
            }

            postItemSale(itemName, price, category);


        });
    }

    private void postItemSale(String itemName, Float price, String category) {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        String username = sp.getString("Username","");
        Item item = new Item(itemName, category, username, price);

        databaseReference.child(itemName).setValue(item);
        Toast.makeText(Sell.this, "Item successfully registered", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Sell.this, HomePage.class));
    }
}