package com.example.hair_cut_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hair_cut_application.DTO.AdminDTO;
import com.example.hair_cut_application.DTO.UserDTO;
import com.example.hair_cut_application.Database.CreateDatabase;

import java.util.ArrayList;

public class AdminDAO {
    private static final String TAG = "AdminDAO";
    private SQLiteDatabase database;
    private CreateDatabase createDatabase;
    private ArrayList myList;


    public AdminDAO(Context context ){
        this.createDatabase = new CreateDatabase(context);
    }

    //Thêm dữ liệu vào các trường của bảng Admin
    public void themUser(AdminDTO adminDTO){
        this.database = createDatabase.getWritableDatabasee();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_ADMIN_USERNAME, adminDTO.getUsername());
        contentValues.put(CreateDatabase.TB_ADMIN_PhoneNumber, adminDTO.getPhoneNumber());
        contentValues.put(CreateDatabase.TB_ADMIN_PASSWORD, adminDTO.getPassword());

        this.database.insert(CreateDatabase.TB_ADMIN, null, contentValues);
        Log.e(TAG, "Insert Successfully");
    }

    //Xóa dữ liệu trong bảng Admin
    public void xoaUser(AdminDTO adminDTO){
        int n = database.delete(CreateDatabase.TB_ADMIN,"phoneNumber = ?", new String[]{adminDTO.getPhoneNumber()});
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

        String query = "Select * from "+ CreateDatabase.TB_ADMIN;
        Cursor c = database.rawQuery(query, null);
        c.moveToNext();
        String data;
        while(c.isAfterLast()==false){
            String name = c.getString(0);
            String phone = c.getString(1);
            String pass = c.getString(2);

            data = String.format("%s - %s - %s", name, phone, pass);
            c.moveToNext();
            myList.add(data);
        }
        c.close();
        return myList;
    }

    //Truy xuất cơ sở dữ liệu có DL truyền vào là SDT
    public AdminDTO layDuLieu(String phoneNumber) {
        this.database = createDatabase.getReadableDatabasee();
        String query = "select * from " + CreateDatabase.TB_ADMIN + " where " + CreateDatabase.TB_ADMIN_PhoneNumber + " = '"
                + phoneNumber + "'";
        Cursor c = database.rawQuery(query, null);

        c.moveToNext();
        String data;

        AdminDTO adminDTO = null;
        while (c.isAfterLast() == false) {
            String name = c.getString(0);
            String phone = c.getString(1);
            String pass = c.getString(2);
            adminDTO = new AdminDTO(name, phone, pass);

            c.moveToNext();
        }
        c.close();
        return adminDTO;
    }

    //Kiểm tra xem số điện thoại có hay chưa
    public boolean kiemTraSDT(String PhoneNumber){
        this.database = createDatabase.getReadableDatabasee();
        String query = "select * from " + CreateDatabase.TB_ADMIN + " where " + CreateDatabase.TB_ADMIN_PhoneNumber + " = '"
                + PhoneNumber+"'";
        Cursor curor = database.rawQuery(query, null);
        if(curor.getCount() != 0){
            return true;
        }else
            return false;
    }

    //    Kiểm tra SDT và MK có tồn tại hay không
    public boolean kiemTraUser(String PhoneNumberLogin, String password){
        this.database = createDatabase.getReadableDatabasee();
        String query = "select * from " + CreateDatabase.TB_ADMIN + " where " + CreateDatabase.TB_ADMIN_PhoneNumber + " = '"
                + PhoneNumberLogin + "' and "+ CreateDatabase.TB_ADMIN_PASSWORD + " = '" + password + "'";
        Cursor curor = database.rawQuery(query, null);
        if(curor.getCount() != 0){
            return true;
        }else
            return false;
    }
}
