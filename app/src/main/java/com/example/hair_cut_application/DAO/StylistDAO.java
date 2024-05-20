package com.example.hair_cut_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hair_cut_application.DTO.ShopDTO;
import com.example.hair_cut_application.DTO.StylistDTO;
import com.example.hair_cut_application.DTO.UserDTO;
import com.example.hair_cut_application.Database.CreateDatabase;

import java.util.ArrayList;

public class StylistDAO {
    private static final String TAG = "StylistDAO";
    private SQLiteDatabase database;
    private CreateDatabase createDatabase;
    private ArrayList myList;

    public StylistDAO(Context context ){
        this.createDatabase = new CreateDatabase(context);
    }

    //Thêm dữ liệu vào bảng
    public void themStylist(StylistDTO stylistDTO){
        this.database = createDatabase.getWritableDatabasee();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_STYLIST_ID, stylistDTO.getId()) ;
        contentValues.put(CreateDatabase.TB_STYLIST_TEN, stylistDTO.getTen()) ;
        contentValues.put(CreateDatabase.TB_STYLIST_SDT, stylistDTO.getSdt()) ;
        contentValues.put(CreateDatabase.TB_STYLIST_GIOITINH, stylistDTO.getGioiTinh()) ;
        contentValues.put(CreateDatabase.TB_STYLIST_EMAIL, stylistDTO.getEmail()) ;
        contentValues.put(CreateDatabase.TB_STYLIST_MOTA, stylistDTO.getMoTa()) ;

        this.database.insert(CreateDatabase.TB_STYLIST, null, contentValues);
        Log.e(TAG, "Insert Successfully");
    }

    //Sửa dữ liệu
    public void suaStylist(StylistDTO stylistDTO){
        ContentValues myValue = new ContentValues();
        myValue.put(CreateDatabase.TB_STYLIST_TEN, stylistDTO.getTen()) ;
        myValue.put(CreateDatabase.TB_STYLIST_SDT, stylistDTO.getSdt()) ;
        myValue.put(CreateDatabase.TB_STYLIST_GIOITINH, stylistDTO.getGioiTinh()) ;
        myValue.put(CreateDatabase.TB_STYLIST_EMAIL, stylistDTO.getEmail()) ;
        myValue.put(CreateDatabase.TB_STYLIST_MOTA, stylistDTO.getMoTa()) ;

        int n = database.update(CreateDatabase.TB_STYLIST, myValue,"id = ?", new String[]{stylistDTO.getId()});
        String msg;
        if (n == 0){
            msg = "Cập nhật không thành công";
        }else {
            msg = n + " cập nhật thành công";
        }
        Log.e(TAG, msg);
    }

    //Xóa dữ liệu
    public void xoaStylist(String maSty){
        int n = database.delete(CreateDatabase.TB_STYLIST,"id = ?", new String[]{maSty});
        String msg;
        if (n == 0){
            msg = "Xóa không thành công";
        }else {
            msg = n + " Xóa thành công";
        }
        Log.e(TAG, msg);
    }

    //Truy xuất cơ sở dữ liệu
    public ArrayList hienDuLieu(){
        myList = new ArrayList();
        this.database = createDatabase.getReadableDatabasee();

        String query = "Select * from "+ createDatabase.TB_STYLIST;
        Cursor c = database.rawQuery(query, null);
        c.moveToNext();
        String data;
        while(c.isAfterLast()==false){
            String ma = c.getString(0);
            String ten = c.getString(1);
            String sdt = c.getString(2);
            String gt = c.getString(3);
            String email = c.getString(4);
            String mt = c.getString(5);

            data = String.format("%s - %s - %s - %s - %s - %s", ma, ten, sdt, gt, email, mt);
            c.moveToNext();
            myList.add(data);
        }
        c.close();
        return myList;
    }

    public ArrayList getNameSex(){
        myList = new ArrayList();
        this.database = createDatabase.getReadableDatabasee();

        String query = "Select * from "+ createDatabase.TB_STYLIST;
        Cursor c = database.rawQuery(query, null);
        c.moveToNext();
        String data;
        while(c.isAfterLast()==false){
            String ten = c.getString(1);
            String sdt = c.getString(2);
            String mt = c.getString(5);

            data = String.format("%s - %s ",ten, sdt);
            c.moveToNext();
            myList.add(data);
        }
        c.close();
        return myList;
    }
    public ArrayList<StylistDTO> getAll(){
        ArrayList<StylistDTO> myList = new ArrayList<>();
        this.database = createDatabase.getReadableDatabasee();

        String query = "Select * from "+ createDatabase.TB_STYLIST;
        Cursor c = database.rawQuery(query, null);
        c.moveToNext();
        String data;
        while(c.isAfterLast()==false){
            String ma = c.getString(0);
            String ten = c.getString(1);
            String sdt = c.getString(2);
            String gt = c.getString(3);
            String email = c.getString(4);
            String mt = c.getString(5);

            StylistDTO stylistDTO = new StylistDTO(ma, ten, sdt, gt, email, mt);
            myList.add(stylistDTO);
            c.moveToNext();
        }
        c.close();
        return myList;
    }

    //Lấy stylist theo id
    public StylistDTO getAllByID(String id) {
        this.database = createDatabase.getReadableDatabasee();
        String query = "select * from " + CreateDatabase.TB_STYLIST + " where " + CreateDatabase.TB_STYLIST_ID + " = '"
                + id + "'";
        Cursor c = database.rawQuery(query, null);

        c.moveToNext();

        StylistDTO stylistDTO = null;
        while (c.isAfterLast() == false) {
            String ma = c.getString(0);
            String ten = c.getString(1);
            String sdt = c.getString(2);
            String gt = c.getString(3);
            String email = c.getString(4);
            String mt = c.getString(5);

            stylistDTO = new StylistDTO(ma, ten, sdt, gt, email, mt);
            c.moveToNext();
        }
        c.close();
        return stylistDTO;
    }

    //Kiểm tra xem mã Stylist có hay chưa
    public boolean kiemTraStylist(String maSty){
        this.database = createDatabase.getReadableDatabasee();
        String query = "select * from " + CreateDatabase.TB_STYLIST + " where " + CreateDatabase.TB_STYLIST_ID + " = '"
                + maSty+"'";
        Cursor curor = database.rawQuery(query, null);
        if(curor.getCount() != 0){
            return true;
        }else
            return false;
    }

    //Kiểm tra xem có dữ liệu trong table hay chưa, nếu chưa thì thêm dữ liệu
    //Có rồi thì không thêm
    public void ktStylist(){
        this.database = createDatabase.getReadableDatabasee();
        Cursor c =database.rawQuery("Select * from "+ CreateDatabase.TB_STYLIST, null, null);
        if(c.getCount() == 0)
            themDuLieuCoSan();
    }
    //Dữ liệu có sẵn
    public void themDuLieuCoSan() {
        this.database = createDatabase.getWritableDatabasee();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CreateDatabase.TB_STYLIST_ID, "1") ;
        contentValues.put(CreateDatabase.TB_STYLIST_TEN, "Nguyễn Hồng Khang") ;
        contentValues.put(CreateDatabase.TB_STYLIST_SDT, "0123 456 789") ;
        contentValues.put(CreateDatabase.TB_STYLIST_GIOITINH, "Nam") ;
        contentValues.put(CreateDatabase.TB_STYLIST_EMAIL, "KhangNguyen@gmail.com") ;
        contentValues.put(CreateDatabase.TB_STYLIST_MOTA, "Vip Stylist") ;
        this.database.insert(CreateDatabase.TB_STYLIST, null, contentValues);

        contentValues.put(CreateDatabase.TB_STYLIST_ID, "2") ;
        contentValues.put(CreateDatabase.TB_STYLIST_TEN, "Bùi Xuân Dũng") ;
        contentValues.put(CreateDatabase.TB_STYLIST_SDT, "0987 654 321") ;
        contentValues.put(CreateDatabase.TB_STYLIST_GIOITINH, "Nam") ;
        contentValues.put(CreateDatabase.TB_STYLIST_EMAIL, "XuanDung@gmail.com") ;
        contentValues.put(CreateDatabase.TB_STYLIST_MOTA, "Stylist cắt rất đẹp") ;
        this.database.insert(CreateDatabase.TB_STYLIST, null, contentValues);

        contentValues.put(CreateDatabase.TB_STYLIST_ID, "3") ;
        contentValues.put(CreateDatabase.TB_STYLIST_TEN, "Võ Xuân Vũ") ;
        contentValues.put(CreateDatabase.TB_STYLIST_SDT, "0999 888 777") ;
        contentValues.put(CreateDatabase.TB_STYLIST_GIOITINH, "Nam") ;
        contentValues.put(CreateDatabase.TB_STYLIST_EMAIL, "XuanVu@gmail.com") ;
        contentValues.put(CreateDatabase.TB_STYLIST_MOTA, "Stylist cắt rất đẹp") ;
        this.database.insert(CreateDatabase.TB_STYLIST, null, contentValues);

        Log.e("StylistDAO", "Thêm dữ liệu Stylist thành công!!");
    }
}
