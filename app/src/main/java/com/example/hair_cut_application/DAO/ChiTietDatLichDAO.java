package com.example.hair_cut_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hair_cut_application.DTO.ChiTietDatLichDTO;
import com.example.hair_cut_application.Database.CreateDatabase;

public class ChiTietDatLichDAO {
    private SQLiteDatabase database;
    private CreateDatabase createDatabase;

    public ChiTietDatLichDAO(Context context){
        this.createDatabase = new CreateDatabase(context);
    }

    public void themChiTietDatLich(ChiTietDatLichDTO chiTietDatLichDTO){
        this.database = createDatabase.getWritableDatabasee();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CHITIETDATLICH_DLID, chiTietDatLichDTO.getDatLichID());
        contentValues.put(CreateDatabase.TB_CHITIETDATLICH_DVID, chiTietDatLichDTO.getDichVuID());
        contentValues.put(CreateDatabase.TB_CHITIETDATLICH_TONGTIEN, chiTietDatLichDTO.getTongTien());
        contentValues.put(CreateDatabase.TB_CHITIETDATLICH_GIAMGIA, chiTietDatLichDTO.getGiamGia());

        this.database.insert(CreateDatabase.TB_CHITIETDATLICH, null,contentValues);

    }
}
