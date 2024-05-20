package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hair_cut_application.DAO.DatLichDAO;
import com.example.hair_cut_application.DAO.LichSuDAO;
import com.example.hair_cut_application.DAO.ShopDAO;
import com.example.hair_cut_application.DAO.StylistDAO;
import com.example.hair_cut_application.DTO.DatLichDTO;
import com.example.hair_cut_application.DTO.LichSuDTO;
import com.example.hair_cut_application.DTO.ShopDTO;
import com.example.hair_cut_application.DTO.StylistDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class chiTietLichSu extends AppCompatActivity {

    DatLichDAO datLichDAO;
    LichSuDAO lichSuDAO;
    Button btnQLDHTrangChu, btnQLDHHuyLich;
    TextView txtTien, txtDV, txtNgay, txtGio, txtStylist, txtShopp;
    ArrayList<DatLichDTO> ds;
    DatLichDTO datLichDTO;
    ShopDAO shopDAO;
    ShopDTO shopDTO;
    StylistDAO stylistDAO;
    StylistDTO stylistDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_lich_su);

        addControl();
        xuLySKClick();
        xuLySKHien();
    }

    private void xuLySKHien() {

        //Gán text
        txtGio.setText(datLichDTO.getGio());
        txtShopp.setText(shopDTO.getName());
        txtNgay.setText(datLichDTO.getNgay());
        txtTien.setText(datLichDTO.getTong());
        txtDV.setText("uốn cao cấp; Nhuộm đen, phủ bạc");
        txtStylist.setText(stylistDTO.getTen());


    }

    private void xuLySKClick(){
        btnQLDHTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chiTietLichSu.this, home.class);
                startActivity(intent);
            }
        });
        btnQLDHHuyLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = datLichDTO.getId();
                String shopID = datLichDTO.getCuaHangID();
                String userID = datLichDTO.getSdt();
                String stylistID = datLichDTO.getTho();
                String ngay = datLichDTO.getNgay();
                String gio = datLichDTO.getGio();
                String tong = datLichDTO.getTong();
                String orderDate = datLichDTO.getThoiGianDat();
                LichSuDTO lichSuDTO = new LichSuDTO(id, shopID, userID, stylistID, ngay, gio, tong, orderDate);

                try {
                    String nowDate = getCurrentDateTime();
                    String dateOrder = datLichDTO.getThoiGianDat();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    Date firstDate = dateFormat.parse(dateOrder);
                    Date secondDate = dateFormat.parse(nowDate);

                    long differenceInMillis = secondDate.getTime() - firstDate.getTime();

                    long second = differenceInMillis / 1000;
                    if (second < 20) {
                        lichSuDAO.themLichSuHUY(lichSuDTO);
                        datLichDAO.xoaLichDat(id);
                        Toast.makeText(chiTietLichSu.this, "Đã hủy lịch hẹn", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(chiTietLichSu.this, home.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(chiTietLichSu.this, "Quá thời gian được hủy", Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addControl() {
        datLichDAO = new DatLichDAO(this);
        lichSuDAO = new LichSuDAO(this);
        shopDAO = new ShopDAO(this);
        stylistDAO = new StylistDAO(this);
        txtTien = findViewById(R.id.txtTien);
        txtDV = findViewById(R.id.txtDV);
        txtNgay = findViewById(R.id.txtNgay);
        txtGio = findViewById(R.id.txtGio);
        txtStylist = findViewById(R.id.txtStylist);
        txtShopp = findViewById(R.id.txtShopp);
        btnQLDHHuyLich = findViewById(R.id.btnQLDHHuyLich);
        btnQLDHTrangChu = findViewById(R.id.btnQLDHTrangChu);
        ds = datLichDAO.getAll();
        datLichDTO = ds.get(lichSu.getIddatlich());
        shopDTO = shopDAO.getAllByID(datLichDTO.getCuaHangID());
        stylistDTO = stylistDAO.getAllByID(datLichDTO.getTho());
    }

    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}