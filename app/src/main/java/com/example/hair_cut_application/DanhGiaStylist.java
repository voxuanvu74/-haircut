package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.hair_cut_application.DAO.DanhGiaDAO;
import com.example.hair_cut_application.DAO.DichVuDAO;
import com.example.hair_cut_application.DTO.DanhGiaDTO;

import java.util.ArrayList;

public class DanhGiaStylist extends AppCompatActivity {
    ArrayList<DanhGiaDTO> list;
    ListView lvList;
    DanhGiaDAO danhGiaDAO;
    DGUserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia_stylist);

        addControl();
    }

    private void addControl(){
        danhGiaDAO = new DanhGiaDAO(this);
        list = danhGiaDAO.getDanhGiaStylist(choose_stylist2.getId());
        adapter = new DGUserAdapter(DanhGiaStylist.this, list);
        lvList = findViewById(R.id.lvDGStylist);
        lvList.setAdapter(adapter);
    }
}