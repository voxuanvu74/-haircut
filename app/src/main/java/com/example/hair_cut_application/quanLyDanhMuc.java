package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.hair_cut_application.DAO.ShopDAO;
import com.example.hair_cut_application.DAO.StylistDAO;
import com.example.hair_cut_application.DTO.ShopDTO;
import com.example.hair_cut_application.DTO.StylistDTO;

import java.util.ArrayList;

public class quanLyDanhMuc extends AppCompatActivity {
    EditText eTxtTenStylist, eTxtGioiTinhStylist, eTxtIdStylist, eTxtSDTStylist, eTxtEmailStylist, eTxtMotaStylist;
    EditText eTxtIdShop, eTxtTenShop, eTxtDCShop, eTxtHotlineShop;
    Button btnThemShop, btnXoaShop, btnSuaShop, btnXuatDLShop, btnThemStylist, btnXoaStylist, btnSuaStylist, btnXuatDLStylist;
    ListView lvShop, lvStylist;
    ShopDTO shopDTO;
    ShopDAO shopDAO;
    StylistDAO stylistDAO;
    StylistDTO stylistDTO;
    ArrayList myList;
    ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_danh_muc);

        addConTrol();
        xyLySKTabHost();
        xuLySKClick();
    }

    private void xuLySKClick() {
        //them du lieu vao shop_table
        btnThemShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maShop = eTxtIdShop.getText().toString();
                String tenShop = eTxtTenShop.getText().toString();
                String dcShop = eTxtDCShop.getText().toString();
                String hlShop = eTxtHotlineShop.getText().toString();

                if(maShop.equals("") && tenShop.equals("") && dcShop.equals("") && hlShop.equals("")){
                    Toast.makeText(quanLyDanhMuc.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if(shopDAO.kiemTraMaShop(maShop)){
                        Toast.makeText(quanLyDanhMuc.this, "Shop đã tồn tại", Toast.LENGTH_SHORT).show();
                    }else{
                        shopDTO = new ShopDTO(maShop, dcShop, tenShop, hlShop);
                        shopDAO.themShop(shopDTO);
                        Toast.makeText(quanLyDanhMuc.this, "Thêm shop thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Xuất dữ liệu trong shop_table
        btnXuatDLShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList = new ArrayList();
                myList = shopDAO.hienDuLieu();
                myAdapter = new ArrayAdapter(quanLyDanhMuc.this, android.R.layout.simple_list_item_1, myList);
                lvShop.setAdapter(myAdapter);
            }
        });

        //Xóa dữ liệu shop_table
        btnXoaShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maShop = eTxtIdShop.getText().toString();
                if(maShop.equals("")){
                    Toast.makeText(quanLyDanhMuc.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if(shopDAO.kiemTraMaShop(maShop)){
                        shopDAO.xoaShop(maShop);
                        Toast.makeText(quanLyDanhMuc.this, "Xóa shop thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(quanLyDanhMuc.this, "Shop chưa tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Sửa dữ liệu shop_table
        btnSuaShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maShop = eTxtIdShop.getText().toString();
                String tenShop = eTxtTenShop.getText().toString();
                String dcShop = eTxtDCShop.getText().toString();
                String hlShop = eTxtHotlineShop.getText().toString();

                if(maShop.equals("") && tenShop.equals("") && dcShop.equals("") && hlShop.equals("")){
                    Toast.makeText(quanLyDanhMuc.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    shopDTO = new ShopDTO(maShop, dcShop, tenShop, hlShop);
                    if(shopDAO.kiemTraMaShop(maShop)){
                        shopDAO.suaShop(shopDTO);
                        Toast.makeText(quanLyDanhMuc.this, "Sửa shop thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(quanLyDanhMuc.this, "Shop chưa tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnThemStylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSty = eTxtIdStylist.getText().toString();
                String tenSty = eTxtTenStylist.getText().toString();
                String gtSty = eTxtGioiTinhStylist.getText().toString();
                String sdtSty = eTxtSDTStylist.getText().toString();
                String mailSty = eTxtEmailStylist.getText().toString();
                String motaSty = eTxtMotaStylist.getText().toString();

                if(maSty.equals("") && tenSty.equals("") && gtSty.equals("") && sdtSty.equals("") &&
                        mailSty.equals("") && motaSty.equals("")){
                    Toast.makeText(quanLyDanhMuc.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if(stylistDAO.kiemTraStylist(maSty)){
                        Toast.makeText(quanLyDanhMuc.this, "Stylist đã tồn tại", Toast.LENGTH_SHORT).show();
                    }else{
                        stylistDTO = new StylistDTO(maSty, tenSty, gtSty, sdtSty, mailSty, motaSty);
                        stylistDAO.themStylist(stylistDTO);
                        Toast.makeText(quanLyDanhMuc.this, "Thêm stylist thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnXuatDLStylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList = new ArrayList();
                myList = stylistDAO.hienDuLieu();
                myAdapter = new ArrayAdapter(quanLyDanhMuc.this, android.R.layout.simple_list_item_1, myList);
                lvStylist.setAdapter(myAdapter);
            }
        });

        btnSuaStylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSty = eTxtIdStylist.getText().toString();
                String tenSty = eTxtTenStylist.getText().toString();
                String gtSty = eTxtGioiTinhStylist.getText().toString();
                String sdtSty = eTxtSDTStylist.getText().toString();
                String mailSty = eTxtEmailStylist.getText().toString();
                String motaSty = eTxtMotaStylist.getText().toString();

                if(maSty.equals("") && tenSty.equals("") && gtSty.equals("") && sdtSty.equals("") &&
                        mailSty.equals("") && motaSty.equals("")){
                    Toast.makeText(quanLyDanhMuc.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    stylistDTO = new StylistDTO(maSty, tenSty, gtSty, sdtSty, mailSty, motaSty);
                    if(stylistDAO.kiemTraStylist(maSty)){
                        stylistDAO.suaStylist(stylistDTO);
                        Toast.makeText(quanLyDanhMuc.this, "Sửa Stylist thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(quanLyDanhMuc.this, "Stylist chưa tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnXoaStylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSty = eTxtIdStylist.getText().toString();
                if(maSty.equals("")){
                    Toast.makeText(quanLyDanhMuc.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if(stylistDAO.kiemTraStylist(maSty)){
                        stylistDAO.xoaStylist(maSty);
                        Toast.makeText(quanLyDanhMuc.this, "Xóa styist thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(quanLyDanhMuc.this, "Stylist chưa tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
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
        tab1.setIndicator("Shop");
        tab1.setContent(R.id.tabShop);
        tabHost.addTab(tab1);

        //Tạo các tab QL Stylist
        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        //tạo nhãn
        tab2.setIndicator("Stylist");
        tab2.setContent(R.id.tabStylist);
        tabHost.addTab(tab2);

    }

    private void addConTrol() {
        eTxtTenStylist = findViewById(R.id.eTxtTenStylist);
        eTxtIdStylist = findViewById(R.id.eTxtIdStylist);
        eTxtSDTStylist = findViewById(R.id.eTxtSDTStylist);
        eTxtGioiTinhStylist = findViewById(R.id.eTxtGioiTinhStylist);
        eTxtEmailStylist = findViewById(R.id.eTxtEmailStylist);
        eTxtMotaStylist = findViewById(R.id.eTxtMotaStylist);
        eTxtIdShop = findViewById(R.id.eTxtIdShop);
        eTxtTenShop = findViewById(R.id.eTxtTenShop);
        eTxtDCShop = findViewById(R.id.eTxtDCShop);
        eTxtHotlineShop = findViewById(R.id.eTxtHotlineShop);
        btnThemStylist = findViewById(R.id.btnThemStylist);
        btnXoaStylist = findViewById(R.id.btnXoaStylist);
        btnSuaStylist = findViewById(R.id.btnSuaStylist);
        btnXuatDLStylist = findViewById(R.id.btnXuatDLStylist);
        btnThemShop = findViewById(R.id.btnThemShop);
        btnXoaShop = findViewById(R.id.btnXoaShop);
        btnSuaShop = findViewById(R.id.btnSuaShop);
        btnXuatDLShop = findViewById(R.id.btnXuatDLShop);
        lvShop = findViewById(R.id.lvShop);
        lvStylist = findViewById(R.id.lvStylist);
        shopDAO = new ShopDAO(this);
        stylistDAO = new StylistDAO(this);
    }
}
