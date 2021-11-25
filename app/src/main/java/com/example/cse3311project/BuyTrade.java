package com.example.cse3311project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class BuyTrade extends AppCompatActivity implements MyAdapter.OnItemListener {

    private static final String TAG = "Buy/Trade Page";
    public static String position;
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_trade);

        recyclerView = findViewById(R.id.list_item);
        database = FirebaseDatabase.getInstance().getReference().child("Items");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, list, this);
        recyclerView.setAdapter(myAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Item item = dataSnapshot.getValue(Item.class);
                    list.add(item);
                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void OnItemClick(int position) {
        Log.d(TAG, "OnItemClick: Clicked"+position);
        Intent intent = new Intent(this, PurchasePage.class);
        intent.putExtra("Selected Item", list.get(position));
        intent.putExtra("Position",position);
        startActivity(intent);
    }
}