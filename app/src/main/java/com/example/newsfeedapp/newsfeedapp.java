package com.example.newsfeedapp;

public class newsfeedapp {
    private String mtitle;
    private String mdate;
    String mUrl;

    public newsfeedapp(String title, String date, String url) {
        mtitle = title;
        mdate = date;
        //mPicture = picture;
        mUrl = url;
    }

    public String getTitle(){
        return mtitle;
    }

    public String getdate(){
        return mdate;
    }


    public String getUrl(){
        return mUrl;
    }
}