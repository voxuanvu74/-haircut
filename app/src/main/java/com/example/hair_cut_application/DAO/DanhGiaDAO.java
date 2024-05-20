package com.example.hair_cut_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hair_cut_application.DTO.DanhGiaDTO;
import com.example.hair_cut_application.DTO.LichSuDTO;
import com.example.hair_cut_application.Database.CreateDatabase;

import java.util.ArrayList;

public class DanhGiaDAO {
    private SQLiteDatabase database;
    private CreateDatabase createDatabase;

    public DanhGiaDAO(Context context ){
        this.createDatabase = new CreateDatabase(context);
    }

    public void themDanhGia(DanhGiaDTO danhGiaDTO){
        this.database = createDatabase.getWritableDatabasee();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_DANHGIA_LSID,danhGiaDTO.getLichSuID());
        contentValues.put(CreateDatabase.TB_DANHGIA_SHOPRATING,danhGiaDTO.getShopRating());
        contentValues.put(CreateDatabase.TB_DANHGIA_SHOPREVIEW,danhGiaDTO.getShopReview());
        contentValues.put(CreateDatabase.TB_DANHGIA_STYLISTRATING,danhGiaDTO.getStylistRating());
        contentValues.put(CreateDatabase.TB_DANHGIA_STYLISTREVIEW,danhGiaDTO.getStylistReview());

        this.database.insert(CreateDatabase.TB_DANHGIA, null, contentValues);
        Log.e("DanhGiaDAO", "Đã thêm đánh giá mới");
    }

    public ArrayList<DanhGiaDTO> getDanhGiaHaveUser(String shopID){
        ArrayList<DanhGiaDTO> ds = new ArrayList<>();
        this.database = createDatabase.getReadableDatabasee();
        String query = "select LICHSU.userID, DANHGIA.shopRating, DANHGIA.shopReview " +
                " from DANHGIA join LICHSU on DANHGIA.lichSuID = LICHSU.id" +
                " WHERE LICHSU.shopID = "+ "'" + shopID +"'";
        Cursor c = database.rawQuery(query, null, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            String userId = c.getString(0);
            float ratingShop = c.getFloat(1);
            String reviewShop = c.getString(2);
            LichSuDTO lichSuDTO = new LichSuDTO(userId);
            DanhGiaDTO danhGiaDTO = new DanhGiaDTO(lichSuDTO, ratingShop, reviewShop, 0.0f, null);
            ds.add(danhGiaDTO);
            c.moveToNext();
        }
        c.close();
        return ds;
    }

    public ArrayList<DanhGiaDTO> getDanhGiaStylist(String stylistID){
        ArrayList<DanhGiaDTO> ds = new ArrayList<>();
        this.database = createDatabase.getReadableDatabasee();
        String query = "select LICHSU.userID, DANHGIA.stylistRating, DANHGIA.stylistReview " +
                " from DANHGIA join LICHSU on DANHGIA.lichSuID = LICHSU.id" +
                " WHERE LICHSU.stylistID = "+ "'" + stylistID +"'";
        Cursor c = database.rawQuery(query, null, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            String userId = c.getString(0);
            float ratingSty = c.getFloat(1);
            String reviewSty = c.getString(2);
            LichSuDTO lichSuDTO = new LichSuDTO(userId);
            DanhGiaDTO danhGiaDTO = new DanhGiaDTO(lichSuDTO,0.0F ,null, ratingSty, reviewSty);
            ds.add(danhGiaDTO);
            c.moveToNext();
        }
        c.close();
        return ds;
    }
}
