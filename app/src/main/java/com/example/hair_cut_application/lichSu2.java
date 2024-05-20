package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.hair_cut_application.DAO.DatLichDAO;
import com.example.hair_cut_application.DAO.LichSuDAO;
import com.example.hair_cut_application.DTO.DatLichDTO;
import com.example.hair_cut_application.DTO.LichSuDTO;

import java.util.ArrayList;


public class lichSu2 extends AppCompatActivity {
    ListView lvLSHuy, lvLSHoanThanh;
    LichSuDAO lichSuDAO;
    ArrayList<String> list, list2;
    ArrayAdapter adapter, adapter2;
    private static int lichSuID;
    public static LichSuDTO lichSuDTO2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su2);

        addControl();
        xyLySKTabHost();
        xuLyListView();
    }

    private void xuLyListView() {
        list = lichSuDAO.getLSHuy();
        list2 = lichSuDAO.getLSHoanThanh();
        adapter = new ArrayAdapter(lichSu2.this, android.R.layout.simple_list_item_1, list);
        adapter2 = new ArrayAdapter(lichSu2.this, android.R.layout.simple_list_item_1, list2);
        lvLSHuy.setAdapter(adapter);
        lvLSHoanThanh.setAdapter(adapter2);
        lvLSHoanThanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                ArrayList<LichSuDTO> list = lichSuDAO.getLSHoanThanh2();
                LichSuDTO lichSuDTO = list.get(position);
                lichSuDTO2 = lichSuDTO;
                lichSuID = lichSuDTO.getId();
                Intent intent = new Intent(lichSu2.this, chiTietLichSu2.class);
                startActivity(intent);
            }
        });
    }

    private void xyLySKTabHost() {
        //Tạo tabHost
        TabHost tabHost = findViewById(R.id.tabHost);
        //Cài đặt tabHost
        tabHost.setup();
        //Tạo các tab QL Shop
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        //tạo nhãn
        tab1.setIndicator("Đã hoàn thành");
        tab1.setContent(R.id.tabHT);
        tabHost.addTab(tab1);

        //Tạo các tab QL Stylist
        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        //tạo nhãn
        tab2.setIndicator("Đã hủy");
        tab2.setContent(R.id.tabHuy);
        tabHost.addTab(tab2);
    }

    private void addControl() {
        lichSuDAO = new LichSuDAO(this);
        lvLSHuy = findViewById(R.id.lvLSHuy);
        lvLSHoanThanh = findViewById(R.id.lvLSHoanThanh);
    }
    public static int getLichSuID(){
        return lichSuID;
    }
}