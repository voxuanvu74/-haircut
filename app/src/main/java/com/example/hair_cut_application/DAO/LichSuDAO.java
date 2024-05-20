package com.example.hair_cut_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hair_cut_application.DTO.DatLichDTO;
import com.example.hair_cut_application.DTO.LichSuDTO;
import com.example.hair_cut_application.Database.CreateDatabase;
import com.example.hair_cut_application.login;

import java.util.ArrayList;

public class LichSuDAO {
    private SQLiteDatabase database;
    private CreateDatabase createDatabase;
    private static CreateDatabase createDatabase1;
    private static SQLiteDatabase database1;


    public LichSuDAO(Context context ){
        this.createDatabase = new CreateDatabase(context);
    }
        public void themLichSuHT(LichSuDTO lichSuDTO){
            database = createDatabase.getWritableDatabasee();

            ContentValues contentValues = new ContentValues();
            contentValues.put(CreateDatabase.TB_LICHSU_ID, lichSuDTO.getId());
            contentValues.put(CreateDatabase.TB_LICHSU_CUAHANGID, lichSuDTO.getCuaHangID());
            contentValues.put(CreateDatabase.TB_LICHSU_SDTKH, lichSuDTO.getSdt());
            contentValues.put(CreateDatabase.TB_LICHSU_STYLIST, lichSuDTO.getTho());
            contentValues.put(CreateDatabase.TB_LICHSU_NGAY, lichSuDTO.getNgay());
            contentValues.put(CreateDatabase.TB_LICHSU_GIO, lichSuDTO.getGio());
            contentValues.put(CreateDatabase.TB_LICHSU_TONG, lichSuDTO.getTong());
            contentValues.put(CreateDatabase.TB_LICHSU_TGIANDAT, lichSuDTO.getThoiGianDat());
            contentValues.put(CreateDatabase.TB_LICHSU_TT, "Hoan Thanh");

            database.insert(CreateDatabase.TB_LICHSU, null,contentValues);
        }
    public void themLichSuHUY(LichSuDTO lichSuDTO){
        database = createDatabase.getWritableDatabasee();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_LICHSU_ID, lichSuDTO.getId());
        contentValues.put(CreateDatabase.TB_LICHSU_CUAHANGID, lichSuDTO.getCuaHangID());
        contentValues.put(CreateDatabase.TB_LICHSU_SDTKH, lichSuDTO.getSdt());
        contentValues.put(CreateDatabase.TB_LICHSU_STYLIST, lichSuDTO.getTho());
        contentValues.put(CreateDatabase.TB_LICHSU_NGAY, lichSuDTO.getNgay());
        contentValues.put(CreateDatabase.TB_LICHSU_GIO, lichSuDTO.getGio());
        contentValues.put(CreateDatabase.TB_LICHSU_TONG, lichSuDTO.getTong());
        contentValues.put(CreateDatabase.TB_LICHSU_TGIANDAT, lichSuDTO.getThoiGianDat());
        contentValues.put(CreateDatabase.TB_LICHSU_TT, "Da Huy");

        database.insert(CreateDatabase.TB_LICHSU, null,contentValues);
    }

    public ArrayList<String> getLSHuy(){
        String userID = login.sdt;
        this.database = createDatabase.getReadableDatabasee();
        String query = "Select * from "+ CreateDatabase.TB_LICHSU +" where trangThai = 'Da Huy' and userID = '"+userID+"'";
        Cursor c = database.rawQuery(query, null, null);
        c.moveToFirst();

        String data;
        ArrayList<String> ds = new ArrayList<>();
        while(!c.isAfterLast()){
            data ="   "+ c.getString(6) + "  lúc: "+ c.getString(5)+"-"+ c.getString(4)+ "     Đã Hủy";
            ds.add(data);
            c.moveToNext();
        }
        c.close();
        return ds;
    }

    public ArrayList<String> getLSHoanThanh(){
        String userID = login.sdt;
        this.database = createDatabase.getReadableDatabasee();
        String query = "Select * from "+ CreateDatabase.TB_LICHSU +" where trangThai = 'Hoan Thanh' and userID = '"+userID+"'";
        Cursor c = database.rawQuery(query, null, null);
        c.moveToFirst();

        String data;
        ArrayList<String> ds = new ArrayList<>();
        while(!c.isAfterLast()){
            data ="   "+ c.getString(6) + "  lúc: "+ c.getString(5)+"-"+ c.getString(4)+ "     Hoàn Thành";
            ds.add(data);
            c.moveToNext();
        }
        c.close();
        return ds;
    }

    public ArrayList<LichSuDTO> getLSHoanThanh2(){
        ArrayList<LichSuDTO> list = new ArrayList<>();
        String userID = login.sdt;
        this.database = createDatabase.getReadableDatabasee();
//        String query = "Select * from "+ CreateDatabase.TB_LICHSU +" where trangThai = 'Hoan Thanh' and userID = "+ userID;

        String query = "Select * from "+ CreateDatabase.TB_LICHSU +" join "+ CreateDatabase.TB_SHOPS +" on "+ CreateDatabase.TB_LICHSU_CUAHANGID+" = "+CreateDatabase.TB_SHOPS_MASHOP+" where trangThai = 'Hoan Thanh' and userID = '"+userID+"'";

        Cursor c = database.rawQuery(query, null, null);
        c.moveToFirst();
        ArrayList<String> ds = new ArrayList<>();
        while(!c.isAfterLast()){
            int id = c.getInt(0);
            String shopId = c.getString(10);
            String userId = c.getString(2);
            String stlistId = c.getString(3);
            String ngay = c.getString(4);
            String gio = c.getString(5);
            String tong = c.getString(6);
            String tg = c.getString(7);

            LichSuDTO lichSuDTO = new LichSuDTO(id, shopId, userId, stlistId, ngay, gio, tong, tg);
            list.add(lichSuDTO);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    public Float thongKeThang(String thang){
        this.database = createDatabase.getReadableDatabasee();
        String a = "%"+thang+"/2023%";
        String query = "select sum("+ CreateDatabase.TB_LICHSU_TONG +") as 'tien' from " + CreateDatabase.TB_LICHSU + " where " + CreateDatabase.TB_LICHSU_NGAY+ " like '"+a+"' and "+CreateDatabase.TB_LICHSU_TT+" like 'Hoan Thanh'";
        Cursor c = database.rawQuery(query, null);
        c.moveToFirst();
        String x = null;
        while (c.isFirst() == true) {
            x = c.getString(0);
            c.moveToNext();
        }
        c.close();
        if(x == null)
            return Float.parseFloat("1");
        else
            return Float.parseFloat(x)/1000;
    }

    public ArrayList<String> getHoanThanh(String sdt){
        this.database = createDatabase.getReadableDatabasee();
        String query = "Select * from "+ CreateDatabase.TB_LICHSU +" where trangThai = 'Hoan Thanh' and userID = '"+sdt+"'";
        Cursor c = database.rawQuery(query, null, null);
        c.moveToFirst();

        String data;
        ArrayList<String> ds = new ArrayList<>();
        while(!c.isAfterLast()){
            data =" Ngày: "+ c.getString(4) + " lúc: "+ c.getString(5)+" - "+ c.getString(6)+ "Hoàn Thành";
            ds.add(data);
            c.moveToNext();
        }
        c.close();
        return ds;
    }
}
