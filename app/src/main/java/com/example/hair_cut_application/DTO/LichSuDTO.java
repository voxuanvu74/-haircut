package com.example.hair_cut_application.DTO;

public class LichSuDTO {
    private int id;
    private String cuaHangID;
    private String sdt;
    private String tho;
    private String ngay;
    private String gio;
    private String tong;
    private String thoiGianDat;


    public LichSuDTO(int id){
        this.id = id;
    }
    public LichSuDTO(String sdt){
        this.sdt = sdt;
    }

    public LichSuDTO(int id, String cuaHangID, String sdt, String tho, String ngay, String gio, String tong, String thoiGianDat) {
        this.id = id;
        this.cuaHangID = cuaHangID;
        this.sdt = sdt;
        this.ngay = ngay;
        this.gio = gio;
        this.tho = tho;
        this.tong = tong;
        this.thoiGianDat = thoiGianDat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuaHangID() {
        return cuaHangID;
    }

    public void setCuaHangID(String cuaHangID) {
        this.cuaHangID = cuaHangID;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getTho() {
        return tho;
    }

    public void setTho(String tho) {
        this.tho = tho;
    }

    public String getTong() {
        return tong;
    }

    public void setTong(String tong) {
        this.tong = tong;
    }
    public String getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(String thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }
}
