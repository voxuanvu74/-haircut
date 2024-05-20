package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.hair_cut_application.DAO.DanhGiaDAO;
import com.example.hair_cut_application.DAO.DichVuDAO;
import com.example.hair_cut_application.DTO.DanhGiaDTO;
import com.example.hair_cut_application.DTO.DichVuDTO;

import java.util.ArrayList;

public class shopDetail extends AppCompatActivity {
    ArrayList<DichVuDTO> list;
    DichVuAdapter adapter;
    DichVuDAO dichVuDAO;
    DanhGiaDAO danhGiaDAO;
    GridView gvDichVu;
    TextView txtTenShop, txtHotline, txtDiaChiShop;
    private int dem = DichVuAdapter.getDem();
    private static Button btnDaChon;

    private static RelativeLayout relativeLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        addConTrol();
        xyLySKTabHost();
        xyLySKClick();
    }

    private void xyLySKClick() {
        btnDaChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(shopDetail.this, chiTietDatLich.class);
                startActivity(intent);
            }
        });
    }

    private void xyLySKTabHost() {
        //Tạo tabHost
        TabHost tabHost = findViewById(R.id.tabHost);
        //Cài đặt tabHost
        tabHost.setup();
        //Tạo các tab DỊCH VỤ
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        //tạo nhãn
        tab1.setIndicator("Dịch Vụ");
        tab1.setContent(R.id.tabDichVu);
        tabHost.addTab(tab1);
    }

    private void addConTrol() {
        relativeLayout1 = findViewById(R.id.RelativeLayout1);
        relativeLayout1.setVisibility(View.GONE);
        btnDaChon = findViewById(R.id.btnDaChon);
        dichVuDAO = new DichVuDAO(this);
        danhGiaDAO = new DanhGiaDAO(this);
        list = dichVuDAO.getAll();
        adapter = new DichVuAdapter(shopDetail.this, list);
        gvDichVu = findViewById(R.id.gvDichVu);
        gvDichVu.setAdapter(adapter);

        txtHotline = findViewById(R.id.txtHotline);
        txtHotline.setText("1900 4407");
        txtTenShop = findViewById(R.id.txtTenShop);
        txtTenShop.setText(shops.getTenCuaHang());
        txtDiaChiShop = findViewById(R.id.txtDiaChiShop);
        txtDiaChiShop.setText(shops.getDiaChiCuaHang());
    }
    public static void setTenButton(int dem){
        btnDaChon.setText(String.format("Đã chon %d dịch vụ", dem));
    }
    public static void xuLySKAnHienLayout(int dem) {
        if(dem > 0){
            relativeLayout1.setVisibility(View.VISIBLE);
        }else
            relativeLayout1.setVisibility(View.GONE);
    }
}