package com.example.cse3311project;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    //private TextView userNameTextView;
    //private TextView userProfession1;
    //private TextView userId1;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;
    //private FirebaseAuth Auth;
    private String userID;
    //private Button logout;

    private TextView userNameTextView, professionTextView, utaIDTextView;
    private final String TAG = this.getClass().getName().toUpperCase();

    private Map<String, String> userMap;
    private String email;
    private static final String USERS = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

            //receive data from login screen
            Intent intent = getIntent();
            email = intent.getStringExtra("email");

            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference userRef = rootRef.child(USERS);
            Log.v("Users", userRef.getKey());

            userNameTextView = findViewById(R.id.userName);
            professionTextView = findViewById(R.id.userProfession);
            utaIDTextView = findViewById(R.id.userUtaId);


            // Read from the database
            userRef.addValueEventListener(new ValueEventListener() {
                String username, profession, id;
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot keyId: dataSnapshot.getChildren()) {
                        if (keyId.child("email").getValue().equals(email)) {
                            username = keyId.child("username").getValue(String.class);
                            profession = keyId.child("email").getValue(String.class);
                            id = keyId.child("password").getValue(String.class);
                            break;
                        }
                    }
                    userNameTextView.setText(username);
                    professionTextView.setText(profession);
                    utaIDTextView.setText(id);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });






        /*
        logout = (Button) findViewById(R.id.signOut);
        logout.setOnClickListener(new view.onClickListener(){
            @Override
            public void onClick(View v){
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, HomePage.class));
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        //userName1 = findViewById(R.id.userName);

        final TextView userNameTextView = (TextView) findViewById(R.id.userName);
        final TextView professionTextView = (TextView) findViewById(R.id.userProfession);
        final TextView utaidTextView = (TextView) findViewById(R.id.userUtaId);


        //userNameTextView.setText("Unnikuttan");
        //Toast.makeText(Profile.this, userID, Toast.LENGTH_LONG).show();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot) {
                User userprofile = dataSnapshot.getValue(User.class);
                if(userprofile != null)
                {
                    String utaid = userprofile.getEmail();
                    String profession = userprofile.getPassword();
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
        */

        PieChart pieChart = findViewById(R.id.pieChart);
        ArrayList<PieEntry> garage = new ArrayList<>();

        garage.add(new PieEntry(508, "Sold"));
        garage.add(new PieEntry(600, "Bought"));
        garage.add(new PieEntry(400, "Traded"));
        garage.add(new PieEntry(200, "$ Made"));
        garage.add(new PieEntry(300, "$ Paid"));

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

