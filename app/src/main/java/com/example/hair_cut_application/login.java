package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.text.method.PasswordTransformationMethod;
import com.example.hair_cut_application.DAO.DichVuDAO;
import com.example.hair_cut_application.DAO.GiamGiaDAO;
import com.example.hair_cut_application.DAO.ShopDAO;
import com.example.hair_cut_application.DAO.StylistDAO;
import com.example.hair_cut_application.DAO.UsersDAO;

public class login extends AppCompatActivity {
    Button btnRegister2, btnLogin, btnAdminLogin;
    EditText eTxtPassword, eTxtPhoneNumberLogin;
    UsersDAO userDAO;
    public static String sdt;
    ImageView imgShowPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addControl();
        themDuLieuCoSan();
        xuLySKClick();
    }

    private void xuLySKClick() {
        imgShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTxtPassword.setTransformationMethod(null);
            }
        });
        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, adminLogin.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumberLogin = eTxtPhoneNumberLogin.getText().toString();
                sdt = phoneNumberLogin;
                String password = eTxtPassword.getText().toString();
                Boolean kiemTra = userDAO.kiemTraUser(phoneNumberLogin,password);

                if(phoneNumberLogin.equals("") || password.equals("")) {
                    Toast.makeText(login.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if (kiemTra == true) {
                        Toast.makeText(login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }

    private void addControl() {
        btnLogin =(Button) findViewById(R.id.btnLogin);
        btnLogin.setBackgroundResource(R.drawable.border_button_login);
        btnRegister2 =(Button) findViewById(R.id.btnRegister2);
        eTxtPassword =(EditText) findViewById(R.id.eTxtPassword);
        imgShowPass = findViewById(R.id.imgShowPass);
        eTxtPhoneNumberLogin =(EditText) findViewById(R.id.eTxtPhoneNumberLogin);
        btnAdminLogin = findViewById(R.id.btnAdmin);
        userDAO = new UsersDAO(this);

    }

    private void themDuLieuCoSan() {
        StylistDAO stylistDAO = new StylistDAO(this);
        stylistDAO.ktStylist();
        DichVuDAO dichVuDAO = new DichVuDAO(this);
        dichVuDAO.ktDV();
        ShopDAO shopDAO = new ShopDAO(this);
        shopDAO.ktShop();
        GiamGiaDAO giamGiaDAO = new GiamGiaDAO(this);
        giamGiaDAO.ktGiamGia();
    }
}