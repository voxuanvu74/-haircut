package com.example.hair_cut_application.DTO;

public class DichVuDTO {
    private int id;
    private String ten;
    private String moTa;
    private String gia;
    private String imgName;

    public DichVuDTO(int id, String ten, String moTa, String gia, String imgName) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.gia = gia;
        this.imgName = imgName;
    }

    public DichVuDTO(String ten, String moTa, String gia) {
        this.ten = ten;
        this.moTa = moTa;
        this.gia = gia;
    }
    public DichVuDTO(String ten, String gia) {
        this.ten = ten;
        this.moTa ="";
        this.gia = gia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
