package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminHome extends AppCompatActivity {
    Button btnQLUsers, btnQLDanhMuc,btnQLDonDat, btnLogout, btnThongKe, btnThongKeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        addConTroll();
        xuLySKClick();
    }

    private void xuLySKClick() {
        btnQLUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHome.this, quanLyUsers.class);
                startActivity(intent);
            }
        });

        btnQLDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHome.this, quanLyDanhMuc.class);
                startActivity(intent);
            }
        });
        btnQLDonDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHome.this, adminQuanLyDH.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHome.this, login.class);
                startActivity(intent);
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHome.this, ThongKeActivity.class);
                startActivity(intent);
            }
        });
        btnThongKeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHome.this,thong_ke_user.class);
                startActivity(intent);
            }
        });
    }

    private void addConTroll() {
        btnQLUsers = findViewById(R.id.btnQLUsers);
        btnQLDanhMuc= findViewById(R.id.btnQLDanhMuc);
        btnQLDonDat= findViewById(R.id.btnQLDonDat);
        btnLogout= findViewById(R.id.btnLogout);
        btnThongKe= findViewById(R.id.btnThongKe);
        btnThongKeUser= findViewById(R.id.btnThongKeUser);

    }
}