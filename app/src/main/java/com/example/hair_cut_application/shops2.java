package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hair_cut_application.DAO.ShopDAO;
import com.example.hair_cut_application.DTO.ShopDTO;

import java.util.ArrayList;

public class shops2 extends AppCompatActivity {
    ListView lv;
    ShopDAO shopDAO;
    ShopAdapter adapter;
    ArrayList<ShopDTO> myList;
    ArrayList<ShopDTO> myList2;
    private static String tenCuaHang;
    private static String diaChiCuaHang;
    private static String cuaHangID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops2);

        addcontrol();
    }

    private void addcontrol() {
        lv = findViewById(R.id.lvStylist);
        shopDAO = new ShopDAO(this);
        myList = new ArrayList<>();
        myList2 = new ArrayList<>();

        myList2 = shopDAO.fetchData2();
        adapter = new ShopAdapter(shops2.this, myList2);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(shops2.this, shopDetail2.class);

                myList2 = shopDAO.fetchData2();
                ShopDTO shop = myList2.get(position);
                tenCuaHang = shop.getName();
                diaChiCuaHang = shop.getDiaChi();
                cuaHangID = shop.getId();
                startActivity(intent);
            }
        });
    }
    public static String getTenCuaHang(){
        return tenCuaHang;
    }
    public static String getDiaChiCuaHang(){
        return diaChiCuaHang;
    }
    public static String getCuaHangID(){
        return cuaHangID;
    }
}