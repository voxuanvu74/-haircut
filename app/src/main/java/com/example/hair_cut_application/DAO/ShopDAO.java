package com.example.hair_cut_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hair_cut_application.DTO.ShopDTO;
import com.example.hair_cut_application.DTO.UserDTO;
import com.example.hair_cut_application.Database.CreateDatabase;

import java.util.ArrayList;

public class ShopDAO {
    private static final String TAG = "ShopDAO";
    private SQLiteDatabase database;
    private CreateDatabase createDatabase;
    ArrayList myList;
    ArrayList<ShopDTO> myList2;

    public ShopDAO(Context context ){
        this.createDatabase = new CreateDatabase(context);
    }

    //Thêm dữ liệu vào các trường của bảng Shops
    public void themShop(ShopDTO shopDTO){
        this.database = createDatabase.getWritableDatabasee();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_SHOPS_MASHOP,shopDTO.getMaShop());
        contentValues.put(CreateDatabase.TB_SHOPS_DIACHI,shopDTO.getDiaChi());
        contentValues.put(CreateDatabase.TB_SHOPS_NAME, shopDTO.getName());
        contentValues.put(CreateDatabase.TB_SHOPS_HOTLINE,shopDTO.getHotline());
        this.database.insert(CreateDatabase.TB_SHOPS, null, contentValues);

        Log.e(TAG, "Insert Successfully");
    }

    //Xóa dữ liệu
    public void xoaShop(String maShop){
        int n = database.delete(CreateDatabase.TB_SHOPS,"mashop = ?", new String[]{maShop});
        String msg;
        if (n == 0){
            msg = "Xóa không thành công";
        }else {
            msg = n + " Xóa thành công";
        }
        Log.e(TAG, msg);
    }

    //Sửa dữ liệu
    public void suaShop(ShopDTO shopDTO){
        ContentValues myValue = new ContentValues();
        myValue.put(CreateDatabase.TB_SHOPS_DIACHI,shopDTO.getDiaChi());
        myValue.put(CreateDatabase.TB_SHOPS_NAME, shopDTO.getName());
        myValue.put(CreateDatabase.TB_SHOPS_HOTLINE,shopDTO.getHotline());

        int n = database.update(CreateDatabase.TB_SHOPS, myValue,"mashop = ?", new String[]{shopDTO.getMaShop()});
        String msg;
        if (n == 0){
            msg = "Cập nhật không thành công";
        }else {
            msg = n + " cập nhật thành công";
        }
        Log.e(TAG, msg);
    }

    //Kiểm tra xem bảng shop đã có dữ liệu hay chưa
    public void ktShop(){
        this.database = createDatabase.getReadableDatabasee();
        Cursor c =database.rawQuery("Select * from "+ CreateDatabase.TB_SHOPS, null, null);
        if(c.getCount() == 0)
            themDuLieuCoSan();
    }
    public ArrayList fetchData2(){
        myList = new ArrayList();
        this.database = createDatabase.getReadableDatabasee();
        Cursor c =database.rawQuery("Select * from "+ createDatabase.TB_SHOPS, null, null);
        c.moveToNext();
        while(c.isAfterLast()==false){
            String id = c.getString(0);
            String name = c.getString(2);
            String address = c.getString(1);
            c.moveToNext();
            myList.add(new ShopDTO(id, address, name));
        }
        c.close();
        return myList;
    }

    //Lấy shop theo id
    public ShopDTO getAllByID(String id) {
        this.database = createDatabase.getReadableDatabasee();
        String query = "select * from " + CreateDatabase.TB_SHOPS + " where " + CreateDatabase.TB_SHOPS_MASHOP + " = '"
                + id + "'";
        Cursor c = database.rawQuery(query, null);

        c.moveToNext();

        ShopDTO shopDTO = null;
        while (c.isAfterLast() == false) {
            String shopID = c.getString(0);
            String name = c.getString(2);
            String address = c.getString(1);
            c.moveToNext();
           shopDTO = new ShopDTO(shopID, address, name);

            c.moveToNext();
        }
        c.close();
        return shopDTO;
    }

    //Truy xuất cơ sở dữ liệu
    public ArrayList hienDuLieu(){
        myList = new ArrayList();
        this.database = createDatabase.getReadableDatabasee();

        String query = "Select * from "+ createDatabase.TB_SHOPS;
        Cursor c = database.rawQuery(query, null);
        c.moveToNext();
        String data;
        while(c.isAfterLast()==false){
            String ma = c.getString(0);
            String ten = c.getString(1);
            String dc = c.getString(2);
            String hl = c.getString(3);

            data = String.format("%s - %s - %s - %s", ma, ten, dc, hl);
            c.moveToNext();
            myList.add(data);
        }
        c.close();
        return myList;
    }

    //Kiểm tra xem MaShop có hay chưa
    public boolean kiemTraMaShop(String maShop){
        this.database = createDatabase.getReadableDatabasee();
        String query = "select * from " + CreateDatabase.TB_SHOPS + " where " + CreateDatabase.TB_SHOPS_MASHOP + " = '"
                + maShop+"'";
        Cursor curor = database.rawQuery(query, null);
        if(curor.getCount() != 0){
            return true;
        }else
            return false;
    }
    //Thêm dữ liệu có sẵn
    public void themDuLieuCoSan(){
        this.database = createDatabase.getWritableDatabasee();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CreateDatabase.TB_SHOPS_MASHOP,"1");
        contentValues.put(CreateDatabase.TB_SHOPS_DIACHI," 262C Điện Biên Phủ, Phường Võ Thị Sáu, Quận 3");
        contentValues.put(CreateDatabase.TB_SHOPS_NAME," 4RAU QUẬN 3 ");
        contentValues.put(CreateDatabase.TB_SHOPS_HOTLINE, " 012 345 678");
        this.database.insert(CreateDatabase.TB_SHOPS, null, contentValues);

        contentValues.put(CreateDatabase.TB_SHOPS_MASHOP,"2");
        contentValues.put(CreateDatabase.TB_SHOPS_DIACHI," 843 Phan Văn Trị, Phường 7, Quận Gò Vấp ");
        contentValues.put(CreateDatabase.TB_SHOPS_NAME," 4RAU GÒ VẤP  ");
        contentValues.put(CreateDatabase.TB_SHOPS_HOTLINE, " 012 345 678");
        this.database.insert(CreateDatabase.TB_SHOPS, null, contentValues);

        contentValues.put(CreateDatabase.TB_SHOPS_MASHOP,"3");
        contentValues.put(CreateDatabase.TB_SHOPS_DIACHI," 37 Xuân Thủy, Phường Thảo Điền, Quận 2 ");
        contentValues.put(CreateDatabase.TB_SHOPS_NAME," 4RAU Quận 2 ");
        contentValues.put(CreateDatabase.TB_SHOPS_HOTLINE, " 012 345 678");
        this.database.insert(CreateDatabase.TB_SHOPS, null, contentValues);

        contentValues.put(CreateDatabase.TB_SHOPS_MASHOP,"4");
        contentValues.put(CreateDatabase.TB_SHOPS_DIACHI," 100 Bùi Đình Túy, Phường 12, Quận Bình Thạnh  ");
        contentValues.put(CreateDatabase.TB_SHOPS_NAME," 4RAU BÌNH THẠNH   ");
        contentValues.put(CreateDatabase.TB_SHOPS_HOTLINE, " 012 345 678");
        this.database.insert(CreateDatabase.TB_SHOPS, null, contentValues);

        contentValues.put(CreateDatabase.TB_SHOPS_MASHOP,"5");
        contentValues.put(CreateDatabase.TB_SHOPS_DIACHI," 634 Điện Biên Phủ, Phường 11, Quận 10 ");
        contentValues.put(CreateDatabase.TB_SHOPS_NAME," 4RAU QUẬN 10 ");
        contentValues.put(CreateDatabase.TB_SHOPS_HOTLINE, " 012 345 678");
        this.database.insert(CreateDatabase.TB_SHOPS, null, contentValues);

        Log.e(TAG, "Thêm dữ liệu Shop thành công");
    }
}
