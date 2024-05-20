package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hair_cut_application.DAO.DatLichDAO;
import com.example.hair_cut_application.DAO.LichSuDAO;
import com.example.hair_cut_application.DTO.DatLichDTO;

import java.util.ArrayList;

public class adminQuanLyDH extends AppCompatActivity {
    ListView lvQLDH;
    DatLichDAO datLichDAO;
    QLDHAdapter adapter;
    ArrayList<DatLichDTO> list;
    public static LichSuDAO lsDAO;
    public static DatLichDAO dlDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_quan_ly_dh);

        addControl();
    }

    private void addControl() {
        datLichDAO = new DatLichDAO(this);
        lsDAO = new LichSuDAO(this);
        dlDAO = new DatLichDAO(this);
        list = datLichDAO.getAll();
        adapter = new QLDHAdapter(adminQuanLyDH.this, list);
        lvQLDH = findViewById(R.id.lvQLDH);
        lvQLDH.setAdapter(adapter);
    }
    public void backHomeAdmin(){
        addControl();
        Toast.makeText(adminQuanLyDH.this, "thành công", Toast.LENGTH_SHORT).show();

    }
}