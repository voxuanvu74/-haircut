package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Context;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class profile extends AppCompatActivity {
    AppCompatButton button, button2, button3, button4, btnLogout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        addControl();
        xuLySkClick();
    }

    private void xuLySkClick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profile.this, "Tính năng đang được phát triển!", Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, lichSu2.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profile.this, "Tính năng đang được phát triển!", Toast.LENGTH_SHORT).show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profile.this, "Tính năng đang được phát triển!", Toast.LENGTH_SHORT).show();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, login.class);
                startActivity(intent);
            }
        });

    }

    private void addControl() {
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        btnLogout = findViewById(R.id.btnLogout);
        textView = findViewById(R.id.textView);
        textView.setText(login.sdt);
    }

    public void getThongBao(String nd){
        AlertDialog.Builder builder = new AlertDialog.Builder(QLDHAdapter.context2);
        builder.setTitle("Thông báo")
                .setMessage(nd);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}