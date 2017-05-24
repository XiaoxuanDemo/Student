package com.lixian.model;

public class Student {
    private String id;

    private String stunam;

    private String password;

    private String classid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStunam() {
        return stunam;
    }

    public void setStunam(String stunam) {
        this.stunam = stunam == null ? null : stunam.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }
}