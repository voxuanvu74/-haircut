package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hair_cut_application.DAO.AdminDAO;

public class adminLogin extends AppCompatActivity {
    EditText eTxtPassword, eTxtPhoneNumberLogin;
    ImageView imgShowPass;
    Button btnLogin;
    AdminDAO adminDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        addConTrol();
        xuLySKClick();
    }

    private void xuLySKClick() {
        imgShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTxtPassword.setTransformationMethod(null);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumberLogin = eTxtPhoneNumberLogin.getText().toString();
                String password = eTxtPassword.getText().toString();
                Boolean kiemTra = adminDAO.kiemTraUser(phoneNumberLogin,password);

                if(phoneNumberLogin.equals("") || password.equals("")) {
                    Toast.makeText(adminLogin.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if (phoneNumberLogin.equals("Admin") && password.equals("123")) {
                        Toast.makeText(adminLogin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(adminLogin.this, adminHome.class);
                        startActivity(intent);
                    } else {
                        if (kiemTra == true) {
                            Toast.makeText(adminLogin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(adminLogin.this, adminHome.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(adminLogin.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void addConTrol() {
        eTxtPassword = findViewById(R.id.eTxtPassword);
        eTxtPhoneNumberLogin = findViewById(R.id.eTxtPhoneNumberLogin);
        imgShowPass = findViewById(R.id.imgShowPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setBackgroundResource(R.drawable.border_button_login);
        adminDAO = new AdminDAO(this);
    }
}