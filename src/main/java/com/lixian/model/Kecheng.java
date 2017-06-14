package com.lixian.model;
/**
 * ¿Î³ÌÐÅÏ¢base
 * @author Administrator
 *
 */
public class Kecheng {
    private String id;

    private String name;

    private String teahcerid;

    private String classid;

    private Integer stunum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTeahcerid() {
        return teahcerid;
    }

    public void setTeahcerid(String teahcerid) {
        this.teahcerid = teahcerid == null ? null : teahcerid.trim();
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public Integer getStunum() {
        return stunum;
    }

    public void setStunum(Integer stunum) {
        this.stunum = stunum;
    }
}