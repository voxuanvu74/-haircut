package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hair_cut_application.DAO.LichSuDAO;
import com.example.hair_cut_application.DAO.StylistDAO;
import com.example.hair_cut_application.DTO.LichSuDTO;
import com.example.hair_cut_application.DTO.StylistDTO;

import java.util.ArrayList;

public class chiTietLichSu2 extends AppCompatActivity {
    Button btnDanhGia;
    TextView txtTien1, txtDV1, txtNgay1, txtGio1, txtStylist1, txtShop1;
    ArrayList<String> list;
    LichSuDTO lichsuDTO;
    StylistDAO stylistDAO;
    StylistDTO stylistDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_lich_su2);

        addControl();
        xuLySKHien();
        xuLySKClick();
    }
    private void xuLySKHien() {

        //Gán text
        txtShop1.setText(lichsuDTO.getCuaHangID());
        txtGio1.setText(lichsuDTO.getGio());
        txtNgay1.setText(lichsuDTO.getNgay());
        txtTien1.setText(lichsuDTO.getTong());
        txtDV1.setText("uốn cao cấp; Nhuộm đen, phủ bạc");
        txtStylist1.setText(stylistDTO.getTen());
    }


    private void xuLySKClick() {
        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chiTietLichSu2.this, danhGia.class);
                startActivity(intent);
            }
        });
    }
    private void addControl() {
        txtTien1 = findViewById(R.id.txtTien1);
        txtDV1 = findViewById(R.id.txtDV1);
        txtNgay1 = findViewById(R.id.txtNgay1);
        txtGio1 = findViewById(R.id.txtGio1);
        txtShop1 = findViewById(R.id.txtShop1);
        txtStylist1 = findViewById(R.id.txtStylist1);
        lichsuDTO = lichSu2.lichSuDTO2;
        btnDanhGia = findViewById(R.id.btnDanhGia);
        stylistDAO = new StylistDAO(this);
        String a = lichsuDTO.getTho();
        stylistDTO = stylistDAO.getAllByID(a);
    }
}