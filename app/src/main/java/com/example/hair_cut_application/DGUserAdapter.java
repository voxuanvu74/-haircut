package com.example.hair_cut_application;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hair_cut_application.DTO.DanhGiaDTO;

import java.util.ArrayList;

public class DGUserAdapter extends BaseAdapter {
    Context context;
    ArrayList<DanhGiaDTO> ds;

    public DGUserAdapter(Context context,ArrayList<DanhGiaDTO> ds){
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
            convertView = inflater.inflate(R.layout.layout_danhgia, null);

            holder.txtUserNameDG = convertView.findViewById(R.id.txtUserNameDG);
            holder.ratingBarDG = convertView.findViewById(R.id.ratingBarDG);
            holder.etxtReviewDG = convertView.findViewById(R.id.etxtReviewDG);
            holder.layoutDG = convertView.findViewById(R.id.layoutDG);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        DanhGiaDTO danhGiaDTO = ds.get(position);
        holder.txtUserNameDG.setText(danhGiaDTO.getLichSuDTO().getSdt());
        holder.ratingBarDG.setRating(danhGiaDTO.getStylistRating());
        holder.etxtReviewDG.setText(danhGiaDTO.getStylistReview());
        holder.layoutDG.setBackgroundResource(R.drawable.border);

        return convertView;
    }

    class ViewHolder{
        TextView txtUserNameDG;
        RatingBar ratingBarDG;
        TextView etxtReviewDG;
        LinearLayout layoutDG;
    }
}
