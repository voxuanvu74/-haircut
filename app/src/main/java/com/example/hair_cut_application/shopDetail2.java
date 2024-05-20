package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class shopDetail2 extends AppCompatActivity {
    ArrayList<DanhGiaDTO> list;
    ArrayList<DanhGiaDTO> list2;
    DichVuAdapter adapter;
    DGShopAdapter adapter2;
    DichVuDAO dichVuDAO;
    DanhGiaDAO danhGiaDAO;
    ListView lvDGList;
    TextView txtTenShop, txtHotline, txtDiaChiShop;
    private int dem = DichVuAdapter.getDem();
    private static RelativeLayout relativeLayout1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail2);

        addConTrol();
        xyLySKTabHost();
    }

    private void xyLySKTabHost() {
        //Tạo tabHost
        TabHost tabHost = findViewById(R.id.tabHost);
        //Cài đặt tabHost
        tabHost.setup();

        //Tạo các tab KHÁM PHÁ
        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        //tạo nhãn
        tab2.setIndicator("Khám Phá");
        tab2.setContent(R.id.tabKhamPha);
        tabHost.addTab(tab2);

        //Tạo các tab ĐÁNH GIÁ
        TabHost.TabSpec tab3 = tabHost.newTabSpec("t3");
        //tạo nhãn
        tab3.setIndicator("Đánh Giá");
        tab3.setContent(R.id.tabDanhGia);
        tabHost.addTab(tab3);

    }

    private void addConTrol() {
        dichVuDAO = new DichVuDAO(this);
        danhGiaDAO = new DanhGiaDAO(this);
        list2 = danhGiaDAO.getDanhGiaHaveUser(shops2.getCuaHangID());
        adapter2 = new DGShopAdapter(shopDetail2.this, list2);
        lvDGList = findViewById(R.id.lvDGList);
        lvDGList.setAdapter(adapter2);

        txtHotline = findViewById(R.id.txtHotline);
        txtHotline.setText("1900 4407");
        txtTenShop = findViewById(R.id.txtTenShop);
        txtTenShop.setText(shops2.getTenCuaHang());
        txtDiaChiShop = findViewById(R.id.txtDiaChiShop);
        txtDiaChiShop.setText(shops2.getDiaChiCuaHang());
    }
}