package com.example.hair_cut_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.hair_cut_application.DTO.UserDTO;
import com.example.hair_cut_application.Database.CreateDatabase;

import java.util.ArrayList;

public class UsersDAO {
    private static final String TAG = "UsersDAO";
    private SQLiteDatabase database;
    private CreateDatabase createDatabase;
    private ArrayList myList;

    public UsersDAO(Context context ){
        this.createDatabase = new CreateDatabase(context);
    }

    //Thêm dữ liệu vào các trường của bảng Users
    public void themUser(UserDTO userDTO){
        this.database = createDatabase.getWritableDatabasee();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_USERS_USERNAME, userDTO.getUsername());
        contentValues.put(CreateDatabase.TB_USERS_PhoneNumber, userDTO.getPhoneNumber());
        contentValues.put(CreateDatabase.TB_USERS_PASSWORD, userDTO.getPassword());

        this.database.insert(CreateDatabase.TB_USERS, null, contentValues);
        Log.e(TAG, "Insert Successfully");
    }

    //Sửa dữ liệu bảng Users
    public void suaUser(UserDTO userDTO){
        ContentValues myValue = new ContentValues();
        myValue.put(CreateDatabase.TB_USERS_USERNAME, userDTO.getUsername());
        myValue.put(CreateDatabase.TB_USERS_PASSWORD, userDTO.getPassword());

        int n = database.update(CreateDatabase.TB_USERS, myValue,"phoneNumber = ?", new String[]{userDTO.getPhoneNumber()});
        String msg;
        if (n == 0){
            msg = "Cập nhật không thành công";
        }else {
            msg = n + " cập nhật thành công";
        }
        Log.e(TAG, msg);
    }
    //Xóa dữ liệu trong bảng users
    public void xoaUser(UserDTO userDTO){
        int n = database.delete(CreateDatabase.TB_USERS,"phoneNumber = ?", new String[]{userDTO.getPhoneNumber()});
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

        String query = "Select * from "+ CreateDatabase.TB_USERS;
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
    public UserDTO layDuLieu(String phoneNumber) {
        this.database = createDatabase.getReadableDatabasee();
        String query = "select * from " + CreateDatabase.TB_USERS + " where " + CreateDatabase.TB_USERS_PhoneNumber + " = '"
                + phoneNumber + "'";
        Cursor c = database.rawQuery(query, null);

        c.moveToNext();

        UserDTO userDTO = null;
        while (c.isAfterLast() == false) {
            String name = c.getString(0);
            String phone = c.getString(1);
            String pass = c.getString(2);
            userDTO = new UserDTO(name, phone, pass);

            c.moveToNext();
        }
        c.close();
        return userDTO;
    }

//    Kiểm tra SDT và MK có tồn tại hay không
    public boolean kiemTraUser(String phoneNumberLogin, String password){
        this.database = createDatabase.getReadableDatabasee();
        String query = "SELECT * FROM " + CreateDatabase.TB_USERS +
                " WHERE " + CreateDatabase.TB_USERS_PhoneNumber + " = ? AND " +
                CreateDatabase.TB_USERS_PASSWORD + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{phoneNumberLogin, password});
        if(cursor.getCount() != 0){
            return true;
        }else
            return false;
    }

    //Kiểm tra xem số điện thoại có hay chưa
    public boolean kiemTraSDT(String PhoneNumber){
        this.database = createDatabase.getReadableDatabasee();
        String query = "select * from " + CreateDatabase.TB_USERS + " where " + CreateDatabase.TB_USERS_PhoneNumber + " = '"
                + PhoneNumber+"'";
        Cursor curor = database.rawQuery(query, null);
        if(curor.getCount() != 0){
            return true;
        }else
            return false;
    }

}
