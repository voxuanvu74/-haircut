package com.example.hair_cut_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.hair_cut_application.DAO.DanhGiaDAO;
import com.example.hair_cut_application.DTO.DanhGiaDTO;

public class danhGia extends AppCompatActivity {
    RatingBar ratingBarShop, ratingBarStylist;
    EditText etxtReviewShop, etxtReviewStylist;
    Button btnSubmit;
    int rateValue;
    DanhGiaDAO danhGiaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);

        addControl();
        xuLySKClick();
    }

    private void xuLySKClick() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lsID = lichSu2.getLichSuID();
                float shopRating = ratingBarShop.getRating();
                String shopReview = etxtReviewShop.getText().toString();
                float stylistRating = ratingBarStylist.getRating();
                String stylistReview = etxtReviewStylist.getText().toString();

                DanhGiaDTO danhGiaDTO = new DanhGiaDTO(lsID, shopRating, shopReview, stylistRating, stylistReview);
                danhGiaDAO.themDanhGia(danhGiaDTO);

                Intent intent = new Intent(danhGia.this, home.class);
                startActivity(intent);
            }
        });
    }

    private void addControl() {
        ratingBarShop = findViewById(R.id.ratingBarShop);
        ratingBarStylist = findViewById(R.id.ratingBarStylist);
        etxtReviewShop = findViewById(R.id.etxtReviewShop);
        etxtReviewStylist = findViewById(R.id.etxtReviewStylist);
        btnSubmit = findViewById(R.id.btnSubmit);
        danhGiaDAO = new DanhGiaDAO(this);
    }
}