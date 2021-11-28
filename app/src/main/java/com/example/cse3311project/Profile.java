package com.example.cse3311project;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.*;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity
{

    private FirebaseDatabase database;
    private DatabaseReference reference, mreference;
    private FirebaseUser user;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        String userID = sp.getString("Username","");
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        mreference = FirebaseDatabase.getInstance().getReference("Items");

        final TextView userNameTextView = findViewById(R.id.userName);
        final TextView professionTextView = findViewById(R.id.userProfession);
        final TextView utaidTextView = findViewById(R.id.userUtaId);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot) {
                User userprofile = dataSnapshot.getValue(User.class);
                if(userprofile != null)
                {
                    String utaid = userprofile.getUtaid();
                    String profession = userprofile.getProfession();
                    String username = userprofile.getUsername();

                    professionTextView.setText(profession);
                    utaidTextView.setText(utaid);
                    userNameTextView.setText(username);
                }

            }
            @Override
            public void onCancelled( @NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this, "something wrong happened", Toast.LENGTH_LONG).show();

            }
        });


        PieChart pieChart = findViewById(R.id.pieChart);
        ArrayList<PieEntry> garage = new ArrayList<>();

        BuyTrade b = new BuyTrade();
        Sell s = new Sell();

        garage.add(new PieEntry(3, "Sold"));
        garage.add(new PieEntry(BuyTrade.counter, "Bought"));
        garage.add(new PieEntry(2, "Traded"));
        garage.add(new PieEntry(15, "$ Made"));
        garage.add(new PieEntry(5, "$ Paid"));

        PieDataSet pieDataSet = new PieDataSet(garage, "garage");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Garage Sale");
        pieChart.animate();


    }
}

