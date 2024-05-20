package com.example.hair_cut_application.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {
    //Khai báo tên các Table
    public static final String TB_USERS = "USERS";
    public static final String TB_SHOPS = "SHOPS";
    public static final String TB_DICHVU = "DICHVU";
    public static final String TB_ADMIN = "ADMIN";
    public static final String TB_STYLIST = "STYLIST";
    public static final String TB_DATLICH = "DATLICH";
    public static final String TB_CHITIETDATLICH = "CHITIETDATLICH";
    public static final String TB_LICHSU = "LICHSU";
    public static final String TB_DANHGIA= "DANHGIA";
    public static final String TB_GIAMGIA = "GIAMGIA";


    //Khai báo các trường của Table users
    public static final String TB_USERS_USERNAME = "username";
    public static final String TB_USERS_PhoneNumber = "PhoneNumber";
    public static final String TB_USERS_PASSWORD = "password";

    //Khai báo các trường của các Table shops
    public static final String TB_SHOPS_MASHOP= "mashop";
    public static final String TB_SHOPS_DIACHI= "diachi";
    public static final String TB_SHOPS_NAME= "ten";
    public static final String TB_SHOPS_HOTLINE= "hotline";

    //Khai báo các trường của các Table dich vu
    public static final String TB_DICHVU_MADICHVU= "madichvu";
    public static final String TB_DICHVU_TEN= "ten";
    public static final String TB_DICHVU_MOTA = "mota";
    public static final String TB_DICHVU_GIA = "gia";
    public static final String TB_DICHVU_IMAGE = "image";

    //Khai báo các trường của Table Admin
    public static final String TB_ADMIN_USERNAME = "username";
    public static final String TB_ADMIN_PhoneNumber = "PhoneNumber";
    public static final String TB_ADMIN_PASSWORD = "password";

    //Khai báo các trường của Table STYLIST
    public static final String TB_STYLIST_ID = "id";
    public static final String TB_STYLIST_TEN = "ten";
    public static final String TB_STYLIST_GIOITINH = "gioiTinh";
    public static final String TB_STYLIST_SDT = "sdt";
    public static final String TB_STYLIST_EMAIL = "email";
    public static final String TB_STYLIST_MOTA = "moTa";


    //Khai báo các trường của Table Dat Lich
    public static final String TB_DATLICH_ID = "id";
    public static final String TB_DATLICH_CUAHANGID = "shopID";
    public static final String TB_DATLICH_SDTKH = "userID";
    public static final String TB_DATLICH_STYLIST = "stylistID";
    public static final String TB_DATLICH_NGAY = "ngayDat";
    public static final String TB_DATLICH_GIO = "gioDat";
    public static final String TB_DATLICH_TONG = "tongTien";
    public static final String TB_DATLICH_TGIANDAT = "thoiGianDat";

    //Khai báo các trường của Table Chi tiet dat lich
    public static final String TB_CHITIETDATLICH_ID = "id";
    public static final String TB_CHITIETDATLICH_DLID = "datLichID";
    public static final String TB_CHITIETDATLICH_DVID = "dichVuID";
    public static final String TB_CHITIETDATLICH_TONGTIEN = "tongTien";
    public static final String TB_CHITIETDATLICH_GIAMGIA= "giamGia";

    //Khai báo các trường của Table Lich Su
    public static final String TB_LICHSU_ID = "id";
    public static final String TB_LICHSU_CUAHANGID = "shopID";
    public static final String TB_LICHSU_SDTKH = "userID";
    public static final String TB_LICHSU_STYLIST = "stylistID";
    public static final String TB_LICHSU_NGAY = "ngayDat";
    public static final String TB_LICHSU_GIO = "gioDat";
    public static final String TB_LICHSU_TONG = "tongTien";
    public static final String TB_LICHSU_TGIANDAT = "thoiGianDat";
    public static final String TB_LICHSU_TT = "trangThai";

    //Khai báo các trường của Table Danh gia
    public static final String TB_DANHGIA_ID = "id";
    public static final String TB_DANHGIA_LSID = "lichSuID";
    public static final String TB_DANHGIA_SHOPRATING= "shopRating";
    public static final String TB_DANHGIA_SHOPREVIEW = "shopReview";
    public static final String TB_DANHGIA_STYLISTRATING = "stylistRating";
    public static final String TB_DANHGIA_STYLISTREVIEW = "stylistReview";

    //Khai báo các trường của Table Giam Gia
    public static final String TB_GIAMGIA_ID = "id";
    public static final String TB_GIAMGIA_MA = "ma";
    public static final String TB_GIAMGIA_NOIDUNG = "noidung";


    public CreateDatabase(Context context) {

        super(context, "HairCut.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbUsers = "CREATE TABLE "+TB_USERS+ "( " +
                TB_USERS_USERNAME +" TEXT , " +
                TB_USERS_PhoneNumber + " TEXT PRIMARY KEY, " +
                TB_USERS_PASSWORD + " TEXT)";

        String tbShops = "CREATE TABLE "+TB_SHOPS+ "( " +
                TB_SHOPS_MASHOP + " TEXT PRIMARY KEY, " +
                TB_SHOPS_DIACHI + " TEXT, " +
                TB_SHOPS_NAME + " TEXT, " +
                TB_SHOPS_HOTLINE + " TEXT)";

        String tbDichVu = "CREATE TABLE "+TB_DICHVU+ "( " +
                TB_DICHVU_MADICHVU + " INTEGER PRIMARY KEY autoincrement, " +
                TB_DICHVU_TEN + " TEXT, " +
                TB_DICHVU_MOTA + " TEXT, " +
                TB_DICHVU_GIA + " TEXT ," +
                TB_DICHVU_IMAGE + " TEXT )";

        String tbAdmin = "CREATE TABLE "+TB_ADMIN+ "( " +
                TB_USERS_USERNAME +" TEXT , " +
                TB_USERS_PhoneNumber + " TEXT PRIMARY KEY, " +
                TB_USERS_PASSWORD + " TEXT)";

        String tbStylist =  "CREATE TABLE "+TB_STYLIST+ "( " +
                TB_STYLIST_ID +" TEXT PRIMARY KEY, " +
                TB_STYLIST_TEN + " TEXT, " +
                TB_STYLIST_GIOITINH + " TEXT, " +
                TB_STYLIST_SDT + " TEXT, " +
                TB_STYLIST_EMAIL + " TEXT, " +
                TB_STYLIST_MOTA + " TEXT)";

        String tbDatLich = "CREATE TABLE "+ TB_DATLICH + "( " +
                TB_DATLICH_ID +" INTEGER PRIMARY KEY autoincrement, " +
                TB_DATLICH_CUAHANGID + " TEXT, " +
                TB_DATLICH_SDTKH + " TEXT, " +
                TB_DATLICH_STYLIST + " TEXT, "+
                TB_DATLICH_NGAY + " TEXT, " +
                TB_DATLICH_GIO + " TEXT, " +
                TB_DATLICH_TONG + " TEXT, "+
                TB_DATLICH_TGIANDAT + " TEXT )";

        String tbChiTietDatLich = "CREATE TABLE "+ TB_CHITIETDATLICH + "( " +
                TB_CHITIETDATLICH_ID +" INTEGER PRIMARY KEY autoincrement, " +
                TB_CHITIETDATLICH_DLID +" INTEGER , " +
                TB_CHITIETDATLICH_DVID + " INTEGER , "+
                TB_CHITIETDATLICH_TONGTIEN + " text, "+
                TB_CHITIETDATLICH_GIAMGIA + " integer )";

        String tbLichSu = "CREATE TABLE "+ TB_LICHSU + "( " +
                TB_LICHSU_ID +" INTEGER PRIMARY KEY, " +
                TB_LICHSU_CUAHANGID + " TEXT, " +
                TB_LICHSU_SDTKH + " TEXT, " +
                TB_LICHSU_STYLIST + " TEXT, "+
                TB_LICHSU_NGAY + " TEXT, " +
                TB_LICHSU_GIO + " TEXT, " +
                TB_LICHSU_TONG + " TEXT, "+
                TB_LICHSU_TGIANDAT + " TEXT, "+
                TB_LICHSU_TT + " TEXT )";

        String tbDanhGia = "CREATE TABLE "+ TB_DANHGIA + "( " +
                TB_DANHGIA_ID +" INTEGER PRIMARY KEY autoincrement, " +
                TB_DANHGIA_LSID + " INTEGER, " +
                TB_DANHGIA_SHOPRATING + " REAL, " +
                TB_DANHGIA_SHOPREVIEW + " TEXT, " +
                TB_DANHGIA_STYLISTRATING + " REAL, " +
                TB_DANHGIA_STYLISTREVIEW + " TEXT)";

        String tbGiamGia = "CREATE TABLE "+TB_GIAMGIA+ "( " +
                TB_GIAMGIA_ID +" INTEGER PRIMARY KEY, " +
                TB_GIAMGIA_MA + " TEXT , " +
                TB_GIAMGIA_NOIDUNG + " INTEGER)";

        db.execSQL(tbGiamGia);
        db.execSQL(tbUsers);
        db.execSQL(tbShops);
        db.execSQL(tbDichVu);
        db.execSQL(tbAdmin);
        db.execSQL(tbStylist);
        db.execSQL(tbDatLich);
        db.execSQL(tbChiTietDatLich);
        db.execSQL(tbLichSu);
        db.execSQL(tbDanhGia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getWritableDatabasee() {
        return getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabasee() {
        return getReadableDatabase();
    }
}
