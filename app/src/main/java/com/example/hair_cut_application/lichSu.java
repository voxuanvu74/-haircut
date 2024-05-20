package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hair_cut_application.DAO.DatLichDAO;
import com.example.hair_cut_application.DTO.DatLichDTO;

import java.util.ArrayList;


public class lichSu extends AppCompatActivity {
    ListView lvLichSu;
    ArrayList<String> list;
    DatLichDAO datLichDAO;
    private static int iddatlich ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);

        addControl();
        xuLySKHienGV();
    }
    private void addControl() {
        lvLichSu= findViewById(R.id.lvLichSu);
        datLichDAO = new DatLichDAO(this);
    }

    private void xuLySKHienGV() {
        list = datLichDAO.getLichSu();
        ArrayAdapter adapter = new ArrayAdapter(lichSu.this, android.R.layout.simple_list_item_1, list);
        lvLichSu.setAdapter(adapter);
        lvLichSu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                iddatlich = i;

                Intent intent = new Intent(lichSu.this, chiTietLichSu.class);
                startActivity(intent);
            }
        });
    }

    public static int getIddatlich(){
        return iddatlich;
    }
}