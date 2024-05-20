package com.example.hair_cut_application.DTO;

public class StylistDTO {
    private String id, ten, sdt, gioiTinh, email, moTa;

    public StylistDTO(String id, String ten, String gioiTinh, String sdt, String email, String moTa) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.moTa = moTa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
