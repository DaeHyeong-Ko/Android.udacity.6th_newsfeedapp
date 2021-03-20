package com.example.newsfeedapp;

public class newsfeedapp {
    private String mTitle;
    private String mDate;
    private String mUrl;
    private String mAuthorName;
    private String mSectionName;

    public newsfeedapp(String SectionName, String AuthorName, String title, String date, String url) {
        mTitle = title;
        mDate = date;
        mUrl = url;
        mAuthorName = AuthorName;
        mSectionName = SectionName;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }


}