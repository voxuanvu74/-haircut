package com.example.hair_cut_application.DTO;

public class DanhGiaDTO {
    private int id;
    private int lichSuID;
    private float shopRating;
    private String shopReview;
    private float stylistRating;
    private String stylistReview;
    private LichSuDTO lichSuDTO;

    public DanhGiaDTO(int lichSuID, float shopRating, String shopReview, float stylistRating, String stylistReview) {
        this.lichSuID = lichSuID;
        this.shopRating = shopRating;
        this.shopReview = shopReview;
        this.stylistRating = stylistRating;
        this.stylistReview = stylistReview;
    }

    public DanhGiaDTO(LichSuDTO lichSuDTO, float shopRating, String shopReview,float stylistRating, String stylistReview){
        this.lichSuDTO = lichSuDTO;
        this.shopRating = shopRating;
        this.shopReview = shopReview;
        this.stylistRating = stylistRating;
        this.stylistReview = stylistReview;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLichSuID() {
        return lichSuID;
    }

    public void setLichSuID(int lichSuID) {
        this.lichSuID = lichSuID;
    }

    public float getShopRating() {
        return shopRating;
    }

    public void setShopRating(float shopRating) {
        this.shopRating = shopRating;
    }

    public String getShopReview() {
        return shopReview;
    }

    public void setShopReview(String shopReview) {
        this.shopReview = shopReview;
    }

    public float getStylistRating() {
        return stylistRating;
    }

    public void setStylistRating(float stylistRating) {
        this.stylistRating = stylistRating;
    }

    public String getStylistReview() {
        return stylistReview;
    }

    public void setStylistReview(String stylistReview) {
        this.stylistReview = stylistReview;
    }
    public LichSuDTO getLichSuDTO() {
        return lichSuDTO;
    }

    public void setLichSuDTO(LichSuDTO lichSuDTO) {
        this.lichSuDTO = lichSuDTO;
    }
}
