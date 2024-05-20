package com.example.hair_cut_application.DTO;

public class ShopDTO {
    private String id;
    private String maShop;
    private String diaChi;
    private String name;
    private String hotline;

    public ShopDTO(String id,  String diaChi, String name) {
        this.id = id;
        this.diaChi = diaChi;
        this.name = name;
        this.hotline = "012 345 678";
    }

    public ShopDTO(String maShop, String diaChi, String name, String hotline) {
        this.maShop = maShop;
        this.diaChi = diaChi;
        this.name = name;
        this.hotline = hotline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getMaShop() {
        return maShop;
    }

    public void setMaShop(String maShop) {
        this.maShop = maShop;
    }
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }
}
