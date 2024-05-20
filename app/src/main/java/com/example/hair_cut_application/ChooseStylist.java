package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.hair_cut_application.DAO.StylistDAO;
import com.example.hair_cut_application.DTO.StylistDTO;

import java.util.ArrayList;

public class ChooseStylist extends AppCompatActivity {
    ListView lvChooseStylist;
    ArrayAdapter adapter;
    StylistDAO stylistDAO;
    Button btnOK;
    ArrayList<String> list = new ArrayList<>();
    private static String name, stylistID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_stylist);

        addControl();
    }

    private void addControl() {
        stylistDAO = new StylistDAO(this);
        list = stylistDAO.getNameSex();
        adapter = new ArrayAdapter(ChooseStylist.this, android.R.layout.simple_list_item_1,list);
        lvChooseStylist = findViewById(R.id.lvChooseStylist);
        lvChooseStylist.setAdapter(adapter);
        lvChooseStylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<StylistDTO> ds = stylistDAO.getAll();
                StylistDTO stylistDTO = ds.get(position);
                stylistID = stylistDTO.getId();
                name = stylistDTO.getTen();

                Intent intent = new Intent(ChooseStylist.this, chiTietDatLich.class);
                startActivity(intent);
            }
        });
    }

    public static String getName(){
        return name;
    }
    public static String getId(){
        return stylistID;
    }
}