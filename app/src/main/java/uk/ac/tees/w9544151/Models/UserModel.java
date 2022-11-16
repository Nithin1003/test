package uk.ac.tees.w9544151.Models;

public class UserModel {
    String userId,type, name, mobile, email, password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel(String userId, String type, String name, String mobile, String email, String password) {
        this.userId = userId;
        this.type = type;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }
}