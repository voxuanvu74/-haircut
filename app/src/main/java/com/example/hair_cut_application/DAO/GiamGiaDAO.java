package com.example.hair_cut_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hair_cut_application.Database.CreateDatabase;

public class GiamGiaDAO {
    private SQLiteDatabase database;
    private CreateDatabase createDatabase;


    public GiamGiaDAO(Context context){
        this.createDatabase = new CreateDatabase(context);
    }

    public void ktGiamGia(){
        this.database = createDatabase.getReadableDatabasee();
        Cursor c =database.rawQuery("Select * from "+ CreateDatabase.TB_GIAMGIA, null, null);
        if(c.getCount() == 0)
            themDuLieuCoSan();
    }

    public void themDuLieuCoSan(){
        this.database = createDatabase.getWritableDatabasee();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CreateDatabase.TB_GIAMGIA_ID,"1");
        contentValues.put(CreateDatabase.TB_GIAMGIA_MA,"DIS10");
        contentValues.put(CreateDatabase.TB_GIAMGIA_NOIDUNG,"10");
        this.database.insert(CreateDatabase.TB_GIAMGIA, null, contentValues);

        contentValues.put(CreateDatabase.TB_GIAMGIA_ID,"2");
        contentValues.put(CreateDatabase.TB_GIAMGIA_MA,"DIS20");
        contentValues.put(CreateDatabase.TB_GIAMGIA_NOIDUNG,"20");
        this.database.insert(CreateDatabase.TB_GIAMGIA, null, contentValues);

    }

    public int kiemTraGiamGia(String ma) {
        this.database = createDatabase.getReadableDatabasee();
        String query = "select " + CreateDatabase.TB_GIAMGIA_NOIDUNG + " from " + CreateDatabase.TB_GIAMGIA + " where " + CreateDatabase.TB_GIAMGIA_MA + " = '" + ma + "'";
        Cursor c = database.rawQuery(query, null);

        c.moveToFirst();

            int x = 0;
            while (c.isFirst() == true) {
                x = c.getInt(0);
                c.moveToNext();
            }
            c.close();
            return x;
    }




}
