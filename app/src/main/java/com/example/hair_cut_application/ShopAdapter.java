package com.example.hair_cut_application;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hair_cut_application.DTO.DanhGiaDTO;
import com.example.hair_cut_application.DTO.ShopDTO;

import java.util.ArrayList;

public class ShopAdapter extends BaseAdapter {
    Context context;
    ArrayList<ShopDTO> ds;

    public ShopAdapter(Context context,ArrayList<ShopDTO> ds){
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
        ShopAdapter.ViewHolder holder;
        if(convertView == null) {
            holder = new ShopAdapter.ViewHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_shops, null);

            holder.linearShops= convertView.findViewById(R.id.linearShops);
            holder.txtShopName = convertView.findViewById(R.id.txtShopName);
            holder.txtShopHotline = convertView.findViewById(R.id.txtShopHotline);
            holder.txtShopAddress = convertView.findViewById(R.id.txtShopAddress);
            convertView.setTag(holder);
        }else{
            holder = (ShopAdapter.ViewHolder) convertView.getTag();
        }
            ShopDTO shopDTO = ds.get(position);
            holder.txtShopName.setText(shopDTO.getName());
            holder.txtShopHotline.setText("Hotline: "+shopDTO.getHotline());
            holder.txtShopAddress.setText("Đc: "+shopDTO.getDiaChi());
            holder.linearShops.setBackgroundResource(R.drawable.border_menu);

        return convertView;
    }

    class ViewHolder{
        LinearLayout linearShops;
        TextView txtShopHotline, txtShopName, txtShopAddress;
    }
}
