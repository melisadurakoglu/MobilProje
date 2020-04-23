package com.example.mobilproje;

public class Users {
    int id;
    String UserName;
    String Password;
    int ImageSource;

    public Users(String userName, String password, int imageSource) {
        this.UserName = userName;
        this.Password = password;
        this.ImageSource = imageSource;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getImageSource() {
        return ImageSource;
    }

    public void setImageSource(int imageSource) {
        ImageSource = imageSource;
    }
}
