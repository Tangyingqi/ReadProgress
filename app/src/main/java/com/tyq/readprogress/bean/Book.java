package com.tyq.readprogress.bean;

import java.net.URL;

/**
 * Created by tyq on 2015/9/7.
 */
public class Book {
    private String Title;
    private String Author;
    private String Bitmap;

    private String Url;


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getBitmap() {
        return Bitmap;
    }

    public void setBitmap(String bitmap) {
        Bitmap = bitmap;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
