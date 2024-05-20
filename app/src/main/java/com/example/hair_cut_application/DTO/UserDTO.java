package com.example.hair_cut_application.DTO;

public class UserDTO {
    private String username, phoneNumber, password;

    public UserDTO(String username,String phoneNumber,String password){
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    public UserDTO(String phoneNumber){
        this.username ="";
        this.password ="";
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
