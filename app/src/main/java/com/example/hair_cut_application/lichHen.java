package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hair_cut_application.DAO.DatLichDAO;
import com.example.hair_cut_application.DTO.DichVuDTO;

import java.util.ArrayList;

public class lichHen extends AppCompatActivity {
    TextView txtNgayHen, txtGioHen, txtTongTien, txtDiaChiShop;
    Button btnHuyLich, btnHome;
    ListView lvTongDVDaChon;
    ArrayList<DichVuDTO> myList;
    MyArrayAdapter myArrayAdapter;
    DatLichDAO datLichDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_hen);

        addControl();
        xuLySKHienLenListView();
        xuLySK();
    }

    private void xuLySK() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lichHen.this, home.class);
                startActivity(intent);
            }
        });
        btnHuyLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datLichDAO.xoaLichDat(chiTietDatLich.datLichId);
                Toast.makeText(lichHen.this, "Hủy thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(lichHen.this, home.class);
                startActivity(intent);
            }
        });
    }

    private void xuLySKHienLenListView() {
        myList = new ArrayList<>(); //Tạo mới mảng rỗng

        myList = DichVuAdapter.getListDVDTO();
        myArrayAdapter = new MyArrayAdapter(lichHen.this, R.layout.layout_dvdachon, myList);

        lvTongDVDaChon.setAdapter(myArrayAdapter);

//        Hiện tổng tiền lên TextView
        String tong = chiTietDatLich.tongTien;
        txtTongTien.setText(tong);
    }
    private void addControl() {
        datLichDAO = new DatLichDAO(this);
        txtNgayHen = findViewById(R.id.txtNgayHen);
        txtNgayHen.setText(chiTietDatLich.ngayChon);
        txtGioHen = findViewById(R.id.txtGioHen);
        txtDiaChiShop = findViewById(R.id.txtDiaChiShop);
        txtDiaChiShop.setText(shops.getTenCuaHang());
        txtGioHen.setText(chiTietDatLich.gioChon);
        lvTongDVDaChon = findViewById(R.id.lvTongDVDaChon);
        txtTongTien = findViewById(R.id.txtTongTien);
        btnHome = findViewById(R.id.btnHome);
        btnHuyLich = findViewById(R.id.btnHuyLich);
    }
}