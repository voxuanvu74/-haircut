package com.example.hair_cut_application;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hair_cut_application.DTO.DichVuDTO;

import java.util.ArrayList;

public class DichVuAdapter extends BaseAdapter {
    Context context;
    ArrayList<DichVuDTO> ds;
    private static int tong;
    private static int dem;
    private static int gia;
    private static ArrayList<DichVuDTO> listDVDTO;


    public DichVuAdapter(Context context,ArrayList<DichVuDTO> ds){
        this.context = context;
        this.ds = ds;
        dem = 0;
        tong = 0;
        listDVDTO = new ArrayList<>();
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

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_item, null);

            holder.name = convertView.findViewById(R.id.eTxtTenDichVu);
            holder.price = convertView.findViewById(R.id.eTxtGiaDichVu);
            holder.img = convertView.findViewById(R.id.imgDichVu);
            holder.btnChonDichVu = convertView.findViewById(R.id.btnChonDichVu);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        DichVuDTO d = ds.get(position);
        holder.name.setText(d.getTen());
        holder.price.setText(d.getGia());;
        String imgName = d.getImgName();

        int resId = ((Activity) context).getResources()
                .getIdentifier(imgName, "drawable", ((Activity) context).getPackageName());
        holder.img.setImageResource(resId);

        holder.btnChonDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btnChonDichVu.getText().toString().equals("Chọn")){
                    gia = Integer.parseInt(holder.price.getText().toString());
                    tong += gia;
                    dem++;
                    holder.btnChonDichVu.setText("Đã chọn");

                    listDVDTO.add(d);
                    shopDetail.setTenButton(dem);
                    shopDetail.xuLySKAnHienLayout(dem);
                }
            }
        });

        return convertView;
    }

    public static int getDem(){return dem;};
    public static int getTong(){return tong;};
    public static ArrayList<DichVuDTO> getListDVDTO(){
        return listDVDTO;
    }

    class ViewHolder{
        ImageView img;
        TextView price;
        TextView name;
        Button btnChonDichVu;
    }

}
