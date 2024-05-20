package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hair_cut_application.DAO.UsersDAO;
import com.example.hair_cut_application.DTO.UserDTO;

public class home extends AppCompatActivity{
    LinearLayout lnLS, lnLH;
    TextView txtUserName;
    UsersDAO usersDAO;
    ImageView imgSearch;
    public static UsersDAO uDao;
    LinearLayout linear1, linear2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addControll();
        xuLySkClick();
        addMenu();
    }

    private void xuLySkClick() {
        lnLH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, lichSu.class);
                startActivity(intent);
            }
        });
        lnLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, lichSu2.class);
                startActivity(intent);
            }
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this, "Tính năng đang được cập nhật", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void xuLySKDatLich(View view) {
        Intent intent = new Intent(home.this, shops.class);
        startActivity(intent);
    }

    private void addControll() {
        lnLS = findViewById(R.id.lnLS);
        lnLH = findViewById(R.id.lnLH);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear1.setBackgroundResource(R.drawable.border_menu);
        linear2.setBackgroundResource(R.drawable.border_menu);
        imgSearch = findViewById(R.id.imgSearch);
        txtUserName = findViewById(R.id.txtUserName);
        usersDAO = new UsersDAO(this);
        uDao = new UsersDAO(this);
        UserDTO userDTO = usersDAO.layDuLieu(login.sdt);
        txtUserName.setText(userDTO.getUsername());
    }

    private void addMenu() {
        LinearLayout linearHome = findViewById(R.id.linearHome);
        LinearLayout linearStylist = findViewById(R.id.linearStylist);
        LinearLayout linearProfile = findViewById(R.id.linearProfile);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        LinearLayout btnLogOut = findViewById(R.id.btnLogOut);
        linearLayout.setBackgroundResource(R.drawable.border_menu);

        //Xử lý chuyển trang
        linearHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, home.class);
                startActivity(intent);
            }
        });
        linearStylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, choose_stylist2.class);
                startActivity(intent);
            }
        });
        linearProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, profile.class);
                startActivity(intent);
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, shops2.class);
                startActivity(intent);
            }
        });
    }
}
    