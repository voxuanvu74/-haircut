package com.example.hair_cut_application;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hair_cut_application.DTO.DichVuDTO;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<DichVuDTO> {
    Activity context;
    int idLayout;
    ArrayList<DichVuDTO> myList;

    public MyArrayAdapter(Activity context1, int idLayout, ArrayList<DichVuDTO> myList) {
        super(context1, idLayout, myList);
        this.context = context1;
        this.idLayout = idLayout;
        this.myList = myList;
    }

    //Gọi hàm getView để tiến hành sắp xếp dữ liệu

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Tạo để chứa layout
        LayoutInflater myFlater = context.getLayoutInflater();
        //Đặt layout lên để tạo thành dữ liệu
        convertView = myFlater.inflate(idLayout, null);
        //Lấy một phần tử trong mảng
        DichVuDTO dichVuDTO= myList.get(position);

        //Khai báo, tham chiếu Id và hiển thị tên lên textView
        TextView txtDVdaChon = convertView.findViewById(R.id.txtDVDaChon);
        txtDVdaChon.setText(dichVuDTO.getTen());
        //Khai báo, tham chiếu Id và hiển thị tên lên textView
        TextView txtGiaDaChon = convertView.findViewById(R.id.txtGiaDaChon);
        txtGiaDaChon.setText(dichVuDTO.getGia());

        return convertView;

    }
}
