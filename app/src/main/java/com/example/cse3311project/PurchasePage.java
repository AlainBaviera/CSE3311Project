package com.example.cse3311project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PurchasePage extends AppCompatActivity {

    private static final String TAG = "Purchase Page";
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_page);

        int position = 0;

        Intent intent = getIntent();
        if(getIntent().hasExtra("Selected Item")){
            Item item = intent.getParcelableExtra("Selected Item");
            Log.d(TAG, "onCreate: "+item.toString());
            String itemName = item.getItemName();
            float itemPrice = item.getItemPrice();
            String itemCategory = item.getItemCategory();
            String itemUser = item.getItemUser();
            intent.getIntExtra(BuyTrade.position, position);

            TextView name = findViewById(R.id.purchasePageName);
            TextView price = findViewById(R.id.purchasePagePrice);
            TextView category = findViewById(R.id.purchasePageCategory);
            TextView user = findViewById(R.id.purchasePageUser);
            Button buy = findViewById(R.id.buyButton);

            name.setText(itemName);
            price.setText(Float.toString(itemPrice));
            category.setText(itemCategory);
            user.setText(itemUser);

            buy.setOnClickListener(v -> {
                DatabaseReference mRef = mDatabase.getReference("Items");
                mRef.child(itemName).removeValue();
                startActivity(new Intent(PurchasePage.this, BuyTrade.class));
            });
        }else
            Toast.makeText(this, "Item Failed to Load", Toast.LENGTH_SHORT).show();
    }
}