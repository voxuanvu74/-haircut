package com.example.hair_cut_application.DTO;

public class ChiTietDatLichDTO {
    private int ID;
    private int datLichID;
    private int dichVuID;
    private String tongTien;
    private int giamGia;

    public ChiTietDatLichDTO(int datLichID, int dichVuID, String tongTien, int giamGia) {
        this.datLichID = datLichID;
        this.dichVuID = dichVuID;
        this.tongTien = tongTien;
        this.giamGia = giamGia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDatLichID() {
        return datLichID;
    }

    public void setDatLichID(int datLichID) {
        this.datLichID = datLichID;
    }

    public int getDichVuID() {
        return dichVuID;
    }

    public void setDichVuID(int dichVuID) {
        this.dichVuID = dichVuID;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }
}
