package com.example.cse3311project;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button profile = findViewById(R.id.ProfileButton);
        Button buyTrade = findViewById(R.id.BuyTradeButton);
        Button messaging = findViewById(R.id.MessagingButton);
        Button search = findViewById(R.id.SearchButton);
        Button sell = findViewById(R.id.SellButton);


        profile.setOnClickListener(view -> {
            Intent goProfile = new Intent(HomePage.this, Profile.class);
            startActivity(goProfile);
        });

        buyTrade.setOnClickListener(view -> {
            Intent goBuySell = new Intent(HomePage.this, BuyTrade.class);
            startActivity(goBuySell);
        });

        messaging.setOnClickListener(view -> {
            Intent goMessaging = new Intent(HomePage.this, Messaging.class);
            startActivity(goMessaging);
        });

        search.setOnClickListener(view -> {
            Intent goSearch = new Intent(HomePage.this, Search.class);
            startActivity(goSearch);
        });

        sell.setOnClickListener(view -> {
            Intent goSell = new Intent(HomePage.this, Sell.class);
            startActivity(goSell);
        });
    }
}