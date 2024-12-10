package com.aurionpro.entity;

public class Admin {
    private int adminId;
    private String name;
    private String password;

   
    public Admin() {}

    public Admin(int adminId, String name, String password) {
        this.adminId = adminId;
        this.name = name;
        this.password = password;
    }

   
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", name=" + name + "]";
    }
}

