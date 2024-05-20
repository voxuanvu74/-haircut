package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class chooseStyle extends AppCompatActivity {
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_style);

        addControl();
    }

    private void addControl() {
        btn1 = findViewById(R.id.btnKieu1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(activity_choose_style.this, Shops_layout.class);
//                startActivity(intent);
            }
        });
    }
}