package com.example.modelclass;

public class Home_ModelClass {

    String cate_id,cate_name,cate_image;

    public Home_ModelClass(String cate_id, String cate_name, String cate_image) {
        this.cate_id = cate_id;
        this.cate_name = cate_name;
        this.cate_image = cate_image;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getCate_image() {
        return cate_image;
    }

    public void setCate_image(String cate_image) {
        this.cate_image = cate_image;
    }
}
