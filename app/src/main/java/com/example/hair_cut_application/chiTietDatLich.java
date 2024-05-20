package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hair_cut_application.DAO.ChiTietDatLichDAO;
import com.example.hair_cut_application.DAO.DatLichDAO;
import com.example.hair_cut_application.DAO.GiamGiaDAO;
import com.example.hair_cut_application.DAO.ShopDAO;
import com.example.hair_cut_application.DTO.ChiTietDatLichDTO;
import com.example.hair_cut_application.DTO.DatLichDTO;
import com.example.hair_cut_application.DTO.DichVuDTO;
import com.example.hair_cut_application.Database.CreateDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class chiTietDatLich extends AppCompatActivity {
    TextView txtTenSalon, txtDiaChiSalon, txtTongTien, txtShowStylist;
    EditText eTxtmaGiamGia;
    ListView lvTongDVDaChon;
    GridView gvGio;
    Button btnChonNgay, btnDatLich, btnChooseStylist;

    ArrayList<DichVuDTO> myList;
    MyArrayAdapter myArrayAdapter;
    private String today;
    public static String ngayChon, gioChon;
    public static int datLichId;
    Calendar calendar = Calendar.getInstance();
    private int date, month, year, CHID;
    DatLichDAO datLichDAO;
    ChiTietDatLichDAO chiTietDatLichDAO;
    public static String ma, tongTien;
    GiamGiaDAO giamGiaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dat_lich);

        addControl();
        xuLySKShowOnScrenn();
        xuLySKHienLenListView();
        xuLySKChonNgayThang();
        xuLyButtonDatLich();
        xuLyButtonChooseStylist();
    }

    private void xuLyButtonChooseStylist() {
        btnChooseStylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chiTietDatLich.this, ChooseStylist.class);
                startActivity(intent);
            }
        });
    }

    private void xuLyButtonDatLich() {
        btnDatLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gioChon == null){
                    Toast.makeText(chiTietDatLich.this, "Vui lòng chọn giờ", Toast.LENGTH_SHORT).show();
                }else{
                    if (datLichDAO.kiemNgayGio(ngayChon, gioChon) == true){
                        Toast.makeText(chiTietDatLich.this, "Đã trùng thời gian đặt, vui lòng chọn lại", Toast.LENGTH_SHORT).show();
                    }else{
                        tinhTien();
                        themLichDat();
                        themChiTietDatLich();

                        Intent intent = new Intent(chiTietDatLich.this, lichHen.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    private void xuLySKChonNgayThang() {
        String[] arrayGio = {"8:00","8:45","9:30","10:15","11:00","13:00","13:45","14:30","15:15","16:00","16:45","17:30","18:15","19:00","19:45"} ;
        ArrayAdapter myArrayAdapter = new ArrayAdapter(chiTietDatLich.this, android.R.layout.simple_list_item_1, arrayGio);
        gvGio.setAdapter(myArrayAdapter);
        gvGio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gioChon = arrayGio[position];
            }
        });

        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(chiTietDatLich.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String show = String.format("%d/%d/%d", dayOfMonth, month +1, year);
                            ngayChon = show;
                            btnChonNgay.setText(show);
                    }
                },year, month-1, date);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
    }
    private void xuLySKHienLenListView() {
        myList = DichVuAdapter.getListDVDTO();
        myArrayAdapter = new MyArrayAdapter(chiTietDatLich.this, R.layout.layout_dvdachon, myList);

        lvTongDVDaChon.setAdapter(myArrayAdapter);
    }

    private void xuLySKShowOnScrenn() {
        String name = shops.getTenCuaHang();
        txtTenSalon.setText(name);
        String diaChi = shops.getDiaChiCuaHang();
        txtDiaChiSalon.setText(diaChi);
        String tong = ""+DichVuAdapter.getTong();
        txtTongTien.setText(tong);
    }

    private void addControl() {
        txtTenSalon = findViewById(R.id.txtTenSalon);
        txtDiaChiSalon = findViewById(R.id.txtDiaChiSalon);
        lvTongDVDaChon = findViewById(R.id.lvTongDVDaChon);
        gvGio = findViewById(R.id.gvGio);
        txtTongTien = findViewById(R.id.txtTongTien);
        txtShowStylist = findViewById(R.id.txtShowStylist);
        txtShowStylist.setText(ChooseStylist.getName());
        btnChonNgay = findViewById(R.id.btnChonNgay);
        btnChooseStylist  = findViewById(R.id.btnChooseStylist );
        date = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH)+1;
        year = calendar.get(Calendar.YEAR);
        today = date+"/"+month+"/"+year+ " (Hôm nay)";
        btnChonNgay.setText(today);
        btnDatLich = findViewById(R.id.btnDatLich);
        datLichDAO = new DatLichDAO(this);
        giamGiaDAO = new GiamGiaDAO(this);
        chiTietDatLichDAO = new ChiTietDatLichDAO(this);
        myList = new ArrayList<>();
        CHID = 0;
        ngayChon = String.format("%d/%d/%d", date, month, year);
        eTxtmaGiamGia = findViewById(R.id.eTxtmaGiamGia);
    }

    public void themLichDat(){
        String cuaHangID = shops.getCuaHangID();
        String SDTKH = login.sdt;
        String ngay = ngayChon;
        String gio = gioChon;
        String thoID = ChooseStylist.getId();
        String tgDat = getCurrentDateTime();
        DatLichDTO datLichDTO = new DatLichDTO(cuaHangID, SDTKH, thoID, ngay, gio, tongTien, tgDat);
        datLichDAO.themLichDat(datLichDTO);

        CHID = datLichDAO.getDatLichID(datLichDTO );
        datLichId = CHID;
    }
    public void themChiTietDatLich(){
        myList = DichVuAdapter.getListDVDTO();
        for (DichVuDTO i: myList) {
            int datLichID = CHID;
            int dichVUID = i.getId();
            int giamGia = 0;
            ChiTietDatLichDTO chiTietDatLichDTO= new ChiTietDatLichDTO(datLichID, dichVUID, tongTien, giamGia);
            chiTietDatLichDAO.themChiTietDatLich(chiTietDatLichDTO);
        }
    }
    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void tinhTien(){
        ma = eTxtmaGiamGia.getText().toString();
        int a = DichVuAdapter.getTong();
        int b = giamGiaDAO.kiemTraGiamGia(chiTietDatLich.ma);
        double kq = a-(a*(b*1.0/100));
        tongTien = ""+kq;
    }
}