package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hair_cut_application.DAO.UsersDAO;
import com.example.hair_cut_application.DTO.UserDTO;

public class register extends AppCompatActivity {
    EditText eTxtUsername, eTxtPassworld, eTxtPhoneNumber, eTxtPasswor2;
    Button btnRegister;
    UsersDAO usersDAO;
    ImageView imgShowPass, imgShowPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addControl();
        xuLySKClick();
    }

    private void xuLySKClick() {
        imgShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTxtPassworld.setTransformationMethod(new PasswordTransformationMethod());
            }
        });
        imgShowPass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTxtPassworld.setTransformationMethod(new PasswordTransformationMethod());
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = eTxtUsername.getText().toString();
                String phoneNumber = eTxtPhoneNumber.getText().toString();
                String matKhau = eTxtPassworld.getText().toString();
                String matKhau2 = eTxtPasswor2.getText().toString();
                if(username.isEmpty()|| phoneNumber.isEmpty()||matKhau.isEmpty()||matKhau2.isEmpty()){
                    Toast.makeText(register.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if (!isValidPhoneNumber(phoneNumber)) {
                        // Số điện thoại không hợp lệ
                        Toast.makeText(register.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    } else {
                        if(!matKhau.equals(matKhau2)){
                            Toast.makeText(register.this, "Mật khẩu xác thực không khớp!", Toast.LENGTH_SHORT).show();
                        }else{
                            // Số điện thoại hợp lệ
                            if (usersDAO.kiemTraSDT(phoneNumber))
                                Toast.makeText(register.this, "Số điện thoại đã tồn tại!", Toast.LENGTH_SHORT).show();
                            else{
                                UserDTO userDTO = new UserDTO(username, phoneNumber, matKhau);
                                usersDAO.themUser(userDTO);
                                Toast.makeText(register.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(register.this, login.class);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        });
    }

    private void addControl() {
        eTxtUsername = findViewById(R.id.eTxtPhoneNumberLogin);
        eTxtPhoneNumber = findViewById(R.id.eTxtPhoneNumber);
        eTxtPassworld = findViewById(R.id.eTxtPassword);
        eTxtPasswor2 = findViewById(R.id.eTxtPasswor2);
        imgShowPass = findViewById(R.id.imgShowPass);
        imgShowPass2 = findViewById(R.id.imgShowPass2);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setBackgroundResource(R.drawable.border_button_login);
        usersDAO = new UsersDAO(this);
    }

    //  kiểm tra định dạng số điện thoại
    private boolean isValidPhoneNumber(String phoneNumber) {
        String phonePattern = "^[+]?[0-9]{10,13}$";
        return phoneNumber.matches(phonePattern);
    }
}