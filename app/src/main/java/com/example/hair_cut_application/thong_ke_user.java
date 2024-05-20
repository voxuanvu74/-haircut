package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hair_cut_application.DAO.LichSuDAO;

import java.util.ArrayList;

public class thong_ke_user extends AppCompatActivity {
    ListView lvKetQua;
    Button btnTimKiem;
    ArrayList<String> list;
    ArrayAdapter adapter;
    EditText tvSDT;
    LichSuDAO lichSuDAO;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_user);

        addcontrol();
        xuLySKClick();
    }
    private void xuLySKClick() {

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key = String.valueOf(tvSDT.getText());
                list = lichSuDAO.getHoanThanh(key);
                adapter = new ArrayAdapter(thong_ke_user.this, android.R.layout.simple_list_item_1, list);
                lvKetQua.setAdapter(adapter);
            }
        });

    }
    private void addcontrol(){
            lichSuDAO = new LichSuDAO(this);
            lvKetQua = findViewById(R.id.lvKetQua);
            btnTimKiem = findViewById(R.id.btnTimKiem);
            tvSDT = findViewById(R.id.tvSDT);

    }



}