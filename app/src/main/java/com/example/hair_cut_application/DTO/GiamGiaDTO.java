package com.example.hair_cut_application.DTO;

public class GiamGiaDTO {
    private int id;
    private String ma;
    private int noidung;

    public GiamGiaDTO(int id, String ma, int noidung) {
        this.id = id;
        this.ma = ma;
        this.noidung = noidung;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public int getNoidung() {
        return noidung;
    }

    public void setNoidung(int noidung) {
        this.noidung = noidung;
    }
}


