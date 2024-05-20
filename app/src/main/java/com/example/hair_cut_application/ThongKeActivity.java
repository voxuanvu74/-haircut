package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.hair_cut_application.DAO.LichSuDAO;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ThongKeActivity extends AppCompatActivity {

    LichSuDAO lichSuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        lichSuDAO = new LichSuDAO(this);
        hienThiChart();

    }

    public void hienThiChart(){
        BarChart barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> tien = new ArrayList<>();

        tien.add(new BarEntry(1, lichSuDAO.thongKeThang("1")));
        tien.add(new BarEntry(2, lichSuDAO.thongKeThang("2")));
        tien.add(new BarEntry(3, lichSuDAO.thongKeThang("3")));
        tien.add(new BarEntry(4, lichSuDAO.thongKeThang("4")));
        tien.add(new BarEntry(5, lichSuDAO.thongKeThang("5")));
        tien.add(new BarEntry(6, lichSuDAO.thongKeThang("6")));
        tien.add(new BarEntry(7, lichSuDAO.thongKeThang("7")));
        tien.add(new BarEntry(8, lichSuDAO.thongKeThang("8")));
        tien.add(new BarEntry(9, lichSuDAO.thongKeThang("9")));
        tien.add(new BarEntry(10, lichSuDAO.thongKeThang("10")));
        tien.add(new BarEntry(11, lichSuDAO.thongKeThang("11")));
        tien.add(new BarEntry(12, lichSuDAO.thongKeThang("12")));



        BarDataSet barDataSet = new BarDataSet(tien, "Tháng");
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(13f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.getDescription().setTextSize(13f);
        barChart.setData(barData);
        barChart.getDescription().setText("Thống kê theo tháng (Đơn vị 1.000đ)");
        barChart.animateY(2800);




    }
}