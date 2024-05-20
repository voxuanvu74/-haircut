package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hair_cut_application.DAO.UsersDAO;
import com.example.hair_cut_application.DTO.UserDTO;

import java.util.ArrayList;

public class quanLyUsers extends AppCompatActivity {
    EditText eTxtHoTen, eTxtSDT, eTxtMatKhau;
    Button btnThem, btnXoa, btnSua, btnCTV, btnXuatDL;
    ListView lv;
    UsersDAO usersDAO;
    UserDTO userDTO;
    ArrayList myList;
    ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_users);

        addControl();
        xuLySuKienClick();
    }

    private void xuLySuKienClick() {

        //thêm dữ liệu
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = eTxtHoTen.getText().toString();
                String sdt = eTxtSDT.getText().toString();
                String matKhau = eTxtMatKhau.getText().toString();

                if(hoTen.equals("") || sdt.equals("") || matKhau.equals("")){
                    Toast.makeText(quanLyUsers.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (usersDAO.kiemTraSDT(sdt)){
                        Toast.makeText(quanLyUsers.this, "Số điện thoại đã tồn tại", Toast.LENGTH_SHORT).show();

                    } else{
                        userDTO = new UserDTO(hoTen,sdt, matKhau);
                        usersDAO.themUser(userDTO);
                        Toast.makeText(quanLyUsers.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Sửa dữ liệu
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = eTxtHoTen.getText().toString();
                String sdt = eTxtSDT.getText().toString();
                String matKhau = eTxtMatKhau.getText().toString();

                if(hoTen.equals("") || sdt.equals("") || matKhau.equals("")){
                    Toast.makeText(quanLyUsers.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    userDTO = new UserDTO(hoTen, sdt, matKhau);
                    if (usersDAO.kiemTraSDT(sdt)) {
                        usersDAO.suaUser(userDTO);
                        Toast.makeText(quanLyUsers.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(quanLyUsers.this, "Số điện thoại chưa tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Xóa dữ liệu
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = eTxtHoTen.getText().toString();
                String sdt = eTxtSDT.getText().toString();
                String matKhau = eTxtMatKhau.getText().toString();

                if(sdt.equals("")){
                    Toast.makeText(quanLyUsers.this, "Vui lòng điền số điện thoại muốn xóa", Toast.LENGTH_SHORT).show();
                }else {
                    userDTO = new UserDTO(hoTen, sdt, matKhau);
                    if (usersDAO.kiemTraSDT(sdt)) {
                        usersDAO.xoaUser(userDTO);
                        Toast.makeText(quanLyUsers.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(quanLyUsers.this, "Số điện thoại chưa tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Xuất dữ liệu ra listView
        btnXuatDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList = new ArrayList();
                myList = usersDAO.hienDuLieu();
                myAdapter = new ArrayAdapter(quanLyUsers.this, android.R.layout.simple_list_item_1, myList);
                lv.setAdapter(myAdapter);
            }
        });

        //xử lý nút btnCVT
        btnCTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quanLyUsers.this, chuyenVaiTro.class);
                startActivity(intent);
            }
        });
    }

    private void addControl() {
        eTxtHoTen = findViewById(R.id.eTxtHoTen);
        eTxtSDT = findViewById(R.id.eTxtSDT);
        eTxtMatKhau = findViewById(R.id.eTxtMatKhau);
        btnThem = findViewById(R.id.btnThemStylist);
        btnXoa = findViewById(R.id.btnXoaStylist);
        btnSua = findViewById(R.id.btnSuaStylist);
        btnCTV = findViewById(R.id.btnCVT);
        btnXuatDL = findViewById(R.id.btnXuatDLStylist);
        lv = findViewById(R.id.lvStylist);
        usersDAO = new UsersDAO(this);
    }
}