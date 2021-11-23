package com.example.cse3311project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import java.util.*;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> garage = new ArrayList<>();

        garage.add(new PieEntry(508, "sold"));
        garage.add(new PieEntry(600, "Bought"));
        garage.add(new PieEntry(400, "Traded"));
        garage.add(new PieEntry(200, "Money made"));
        garage.add(new PieEntry(300, "Money Paid"));

        PieDataSet pieDataSet = new PieDataSet(garage, "garage");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("garage");
        pieChart.animate();


    }
}