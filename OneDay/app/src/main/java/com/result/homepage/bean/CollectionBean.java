package com.result.homepage.bean;

/**
 * Created by 贾焕雪 on 2016-12-20.
 */
public class CollectionBean {

    private String date;
    private String title;
    private String image_url;
    private String e_id;

    public CollectionBean(String date, String title, String image_url, String e_id) {
        this.date = date;
        this.title = title;
        this.image_url = image_url;
        this.e_id = e_id;
    }

    @Override
    public String toString() {
        return "CollectionBean{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", image_url='" + image_url + '\'' +
                ", e_id='" + e_id + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }
}
