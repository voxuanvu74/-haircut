package com.example.hair_cut_application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hair_cut_application.DAO.DatLichDAO;
import com.example.hair_cut_application.DAO.LichSuDAO;
import com.example.hair_cut_application.DTO.DatLichDTO;
import com.example.hair_cut_application.DTO.LichSuDTO;

import java.util.ArrayList;

public class QLDHAdapter extends BaseAdapter {
    Context context;
    public static Context context2;
    ArrayList<DatLichDTO> ds;
    private int datLichID;
    adminQuanLyDH adminQuanLy;
    private String id, shopID, userID, stylistID, ngay, gio, tong, orderTime;


    public QLDHAdapter(Context context,ArrayList<DatLichDTO> ds){
        this.context = context;
        this.ds = ds;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_qldh, null);

            holder.txtQLDHNumberPhone = convertView.findViewById(R.id.txtQLDHNumberPhone);
            holder.txtQLDHShop = convertView.findViewById(R.id.txtQLDHShop);
            holder.txtQLDHStylist = convertView.findViewById(R.id.txtQLDHStylist);
            holder.txtQLDHDay = convertView.findViewById(R.id.txtQLDHDay);
            holder.txtQLDHHour = convertView.findViewById(R.id.txtQLDHHour);
            holder.btnQLDHHoanThanh = convertView.findViewById(R.id.btnQLDHHoanThanh);
            holder.btnQLDHHuyLich = convertView.findViewById(R.id.btnQLDHHuyLich);
            holder.txtQLDHTong = convertView.findViewById(R.id.txtQLDHTong);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        DatLichDTO datLichDTO = ds.get(position);

        holder.txtQLDHNumberPhone.setText(datLichDTO.getSdt());
        holder.txtQLDHStylist.setText("Khang Nguyen");
        holder.txtQLDHDay.setText(datLichDTO.getNgay());
        holder.txtQLDHHour.setText(datLichDTO.getGio());
        holder.txtQLDHTong.setText(datLichDTO.getTong());

        if(datLichDTO.getCuaHangID().equals("1")){
            holder.txtQLDHShop.setText(" 4RAU QUẬN 3");
        }else{
            if(datLichDTO.getCuaHangID().equals("2")){
                holder.txtQLDHShop.setText(" 4RAU GÒ VẤP");
            }else{
                if(datLichDTO.getCuaHangID().equals("3")){
                    holder.txtQLDHShop.setText("  4RAU Quận 2");
                }else{
                    if(datLichDTO.getCuaHangID().equals("4")){
                        holder.txtQLDHShop.setText(" 4RAU BÌNH THẠNH");
                    }else{
                        holder.txtQLDHShop.setText("  4RAU QUẬN 10 ");
                    }
                }
            }
        }

        //xử lý sự kiện nhấn nút hoàn thành
        holder.btnQLDHHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datLichID = datLichDTO.getId();
                shopID = datLichDTO.getCuaHangID();
                userID = datLichDTO.getSdt();
                stylistID = datLichDTO.getTho();
                ngay = datLichDTO.getNgay();
                gio = datLichDTO.getGio();
                tong = datLichDTO.getTong();
                orderTime = datLichDTO.getThoiGianDat();
                LichSuDTO lichSuDTO = new LichSuDTO(datLichID, shopID, userID, stylistID, ngay, gio, tong, orderTime);

                //thêm db vào bảng lich su
                LichSuDAO lichSuDAO = adminQuanLyDH.lsDAO;
                lichSuDAO.themLichSuHT(lichSuDTO);

                //Xóa db khoi bang dat lich
                DatLichDAO datLichDAO = adminQuanLyDH.dlDAO;
                datLichDAO.xoaLichDat(datLichID);

                //Hiện thông báo
                getThongBao("Hoàn thành lịch của "+ userID);
            }
        });

        //xử lý sự kiện nhấn nút hủy lịch
        holder.btnQLDHHuyLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datLichID = datLichDTO.getId();
                shopID = datLichDTO.getCuaHangID();
                userID = datLichDTO.getSdt();
                stylistID = datLichDTO.getTho();
                ngay = datLichDTO.getNgay();
                gio = datLichDTO.getGio();
                tong = datLichDTO.getTong();
                orderTime = datLichDTO.getThoiGianDat();
                LichSuDTO lichSuDTO = new LichSuDTO(datLichID, shopID, userID, stylistID, ngay, gio, tong, orderTime);

                //thêm db vào bảng lich su
                LichSuDAO lichSuDAO = adminQuanLyDH.lsDAO;
                lichSuDAO.themLichSuHUY(lichSuDTO);

                //Xóa db khoi bang dat lich
                DatLichDAO datLichDAO = adminQuanLyDH.dlDAO;
                datLichDAO.xoaLichDat(datLichID);

                //Hiện thông báo
                getThongBao("Huỷ lịch của "+ userID);
            }
        });
        return convertView;
    }
    public void getThongBao(String nd){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo")
                        .setMessage(nd);
                AlertDialog dialog = builder.create();
                dialog.show();
    }

    class ViewHolder{
        TextView txtQLDHNumberPhone, txtQLDHTong, txtQLDHShop, txtQLDHStylist, txtQLDHDay,txtQLDHHour;
        Button btnQLDHHoanThanh, btnQLDHHuyLich;
    }
}
