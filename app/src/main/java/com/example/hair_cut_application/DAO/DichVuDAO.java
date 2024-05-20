package com.example.hair_cut_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hair_cut_application.DTO.DichVuDTO;
import com.example.hair_cut_application.Database.CreateDatabase;

import java.util.ArrayList;


public class DichVuDAO {
    private SQLiteDatabase database;
    private CreateDatabase createDatabase;
    private ArrayList myList;
    public DichVuDAO(Context context){
        this.createDatabase = new CreateDatabase(context);
    }

    public void themDichVu(DichVuDTO dichVuDTO){
        this.database = createDatabase.getWritableDatabasee();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_DICHVU_TEN, dichVuDTO.getTen());
        contentValues.put(CreateDatabase.TB_DICHVU_MOTA, dichVuDTO.getMoTa());
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, dichVuDTO.getGia());

        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

    }
    public void ktDV(){
        this.database = createDatabase.getReadableDatabasee();
        Cursor c =database.rawQuery("Select * from "+ CreateDatabase.TB_DICHVU, null, null);
        if(c.getCount() == 0)
            themDuLieuCoSan();
    }

    public ArrayList<DichVuDTO>  getAll(){
        ArrayList<DichVuDTO> ds = new ArrayList<>();
        this.database = createDatabase.getReadableDatabasee();
        Cursor c =database.rawQuery("Select * from "+ CreateDatabase.TB_DICHVU, null, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String mota= c.getString(2);
            String price = c.getString(3);
            String imgName = c.getString(4);

            DichVuDTO d = new DichVuDTO(id , name, mota, price, imgName);

            ds.add(d);
            c.moveToNext();
        }
        c.close();
        return ds;
    }

    public ArrayList fetchData(){
        myList = new ArrayList();
        this.database = createDatabase.getReadableDatabasee();
        Cursor c =database.rawQuery("Select * from "+ CreateDatabase.TB_DICHVU, null, null);
        c.moveToNext();
        String data;
        while(c.isAfterLast()==false){
            data = c.getString(1) + " - "+ c.getString(3);
            c.moveToNext();
            myList.add(data);
        }
        c.close();
        return myList;
    }
    public void themDuLieuCoSan() {
        //Dữ liệu có sẵn
        this.database = createDatabase.getWritableDatabasee();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Nhuộm đen, phủ bạc");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "180000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc3");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Nhuộm thời trang, không tẩy");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "189000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc2");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Nhuộm thời trang cần tẩy");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "200000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc7");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Nhuộm nâu cao cấp");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "150000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc6");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "uốn cao cấp");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "349000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc4");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Uốn tiêu chuẩn");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "260000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc5");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Hấp dưỡng Oliu");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "119000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc6");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Phục hồi Amino Matrix");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "159000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc7");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Cắt xả tạo kiểu 5 bước");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "70000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc1");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Mặt nạ tẩy trắng");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "40000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc2");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Lột, nặn mụn");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "40000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc5");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);

        contentValues.put(CreateDatabase.TB_DICHVU_TEN, "Tẩy tế bào chết");
        contentValues.put(CreateDatabase.TB_DICHVU_GIA, "30000");
        contentValues.put(CreateDatabase.TB_DICHVU_IMAGE, "toc3");
        this.database.insert(CreateDatabase.TB_DICHVU, null,contentValues);
        Log.e("DichVuDAO", "Thêm dữ liệu Dịch Vụ thành công!!");
    }
}
