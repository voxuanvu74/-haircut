package com.example.hair_cut_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hair_cut_application.DTO.DatLichDTO;
import com.example.hair_cut_application.Database.CreateDatabase;
import com.example.hair_cut_application.lichSu;
import com.example.hair_cut_application.login;
import com.example.hair_cut_application.lichSu2;

import java.util.ArrayList;

public class DatLichDAO {

    private SQLiteDatabase database;
    private CreateDatabase createDatabase;
    private int datLichID;
    ArrayList myList, myList2, myList3;
    private static String a;
    private  static  int b;


    public DatLichDAO(Context context ){
        this.createDatabase = new CreateDatabase(context);
    }

    public void themLichDat(DatLichDTO datLichDTO){
        this.database = createDatabase.getWritableDatabasee();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_DATLICH_CUAHANGID, datLichDTO.getCuaHangID());
        contentValues.put(CreateDatabase.TB_DATLICH_SDTKH, datLichDTO.getSdt());
        contentValues.put(CreateDatabase.TB_DATLICH_STYLIST, datLichDTO.getTho());
        contentValues.put(CreateDatabase.TB_DATLICH_NGAY, datLichDTO.getNgay());
        contentValues.put(CreateDatabase.TB_DATLICH_GIO, datLichDTO.getGio());
        contentValues.put(CreateDatabase.TB_DATLICH_TONG, datLichDTO.getTong());
        contentValues.put(CreateDatabase.TB_DATLICH_TGIANDAT, datLichDTO.getThoiGianDat());

        this.database.insert(CreateDatabase.TB_DATLICH, null,contentValues);
    }

    public void xoaLichDat(int id){
        this.database = createDatabase.getWritableDatabasee();

        int n = this.database.delete(CreateDatabase.TB_DATLICH, "id = "+ id, null);
        this.database.close();

        String msg;
        if (n > 0) {
            msg = " Xóa thành công";
        } else {
            msg = "Xóa không thành công";
        }
        Log.e("DATLICHDAO", msg);
    }

    public int getDatLichID(DatLichDTO datLichDTO){
        String query = "select * from DATLICH where shopID = "+ "'"+datLichDTO.getCuaHangID()+ "'" +" and userID = "
                + "'"+ datLichDTO.getSdt() + "'" + " and ngayDat = "+ "'"+ datLichDTO.getNgay()+ "'"+" and gioDat = "+ "'"+datLichDTO.getGio()+ "'";
        Cursor c = database.rawQuery(query, null);
        c.moveToNext();
        while (c.isAfterLast() == false) {
            datLichID = c.getInt(0);
            c.moveToNext();
        }
        c.close();

        return datLichID;
    }

    public ArrayList getLichSu() {
        myList = new ArrayList();
        a = login.sdt;
        this.database = createDatabase.getReadableDatabasee();

        String query = "Select "+ CreateDatabase.TB_SHOPS_NAME +","+ CreateDatabase.TB_DATLICH_NGAY +","+ CreateDatabase.TB_DATLICH_GIO +","+ CreateDatabase.TB_DATLICH_TONG +
                " from " + CreateDatabase.TB_DATLICH  + " join " + CreateDatabase.TB_SHOPS +"" +
                "  on "+ CreateDatabase.TB_DATLICH_CUAHANGID +" = "+ CreateDatabase.TB_SHOPS_MASHOP+" where "+CreateDatabase.TB_DATLICH_SDTKH+" = '"+a+"'";
        Cursor c = database.rawQuery(query, null, null);
        c.moveToFirst();
        String data;
        while (c.isAfterLast() == false) {
            data = c.getString(0) + " - "+ c.getString(2)+"-"+ c.getString(1)+ " - "+c.getString(3);
            c.moveToNext();
            myList.add(data);
        }
        c.close();
        return myList;
    }

    public ArrayList getLichSu2() {
        myList2 = new ArrayList();
        a = login.sdt;
        this.database = createDatabase.getReadableDatabasee();

        String query = "Select * from " + CreateDatabase.TB_DATLICH  + " join " + CreateDatabase.TB_SHOPS +"" +
                "  on "+ CreateDatabase.TB_DATLICH_CUAHANGID +" = "+ CreateDatabase.TB_SHOPS_MASHOP+" where "+CreateDatabase.TB_DATLICH_SDTKH+" = '"+a+"'";
        Cursor c = database.rawQuery(query, null, null);
        c.moveToNext();
        while (c.isAfterLast() == false) {
            String idshop = c.getString(1);
            String iduser = c.getString(2);
            String idsty = c.getString(3);
            String ngay = c.getString(4);
            String gio = c.getString(5);

            c.moveToNext();
            myList2.add(new DatLichDTO(idshop,iduser,idsty,ngay,gio));
        }
        c.close();
        return myList2;
    }

    public ArrayList getChiTietLichSu() {
        myList3 = new ArrayList();
        b = lichSu.getIddatlich();
        this.database = createDatabase.getReadableDatabasee();

        String query = "Select "+ CreateDatabase.TB_DICHVU_TEN +","+ CreateDatabase.TB_CHITIETDATLICH_TONGTIEN+" from " + CreateDatabase.TB_CHITIETDATLICH  + " join " + CreateDatabase.TB_DICHVU +"" +
                "  on "+ CreateDatabase.TB_DATLICH_ID +" = "+ CreateDatabase.TB_CHITIETDATLICH_DLID+" where "+CreateDatabase.TB_CHITIETDATLICH_DLID+" = "+b+"";
        Cursor c = database.rawQuery(query, null, null);
        c.moveToFirst();
        String data;
        while (c.isAfterLast() == false) {
            data = c.getString(0) + " - "+ c.getString(1);
            c.moveToNext();
            myList3.add(data);
        }
        c.close();
        return myList3;
    }

    public ArrayList<DatLichDTO>  getAll(){
        ArrayList<DatLichDTO> ds = new ArrayList<>();
        this.database = createDatabase.getReadableDatabasee();
        Cursor c =database.rawQuery("Select * from "+ CreateDatabase.TB_DATLICH, null, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String sdt = c.getString(2);
            String tho = c.getString(3);
            String ngay = c.getString(4);
            String gio = c.getString(5);
            String tong = c.getString(6);
            String ngayDat = c.getString(7);

            DatLichDTO d = new DatLichDTO(id , name, sdt, tho, ngay, gio, tong, ngayDat);

            ds.add(d);
            c.moveToNext();
        }
        c.close();
        return ds;
    }
    public boolean kiemNgayGio(String ngay, String gio){
        this.database = createDatabase.getReadableDatabasee();
        String query = "select * from "+ CreateDatabase.TB_DATLICH +
                " where ngayDat like "+ "'"+ ngay +"'"  +" and gioDat like "+ "'" + gio +"'";
        Cursor curor = database.rawQuery(query, null);
        if(curor.getCount() != 0){
            return true;
        }else
            return false;
    }
}
