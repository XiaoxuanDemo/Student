package com.lixian.model;
/**
 * �༶��
 * @author Administrator
 *
 */
public class Banji {
    private String classid;

    private String classname;

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }
}