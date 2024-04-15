package com.android.apnaott;

public class DataModel {

    private String Title;
    private String Turl;
    private String Tvid;

    // Empty Constructor
    public DataModel() {}

    public DataModel(String title, String turl, String tvid) {
        this.Title = title;
        this.Turl = turl;
        this.Tvid = tvid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getTurl() {
        return Turl;
    }

    public void setTurl(String turl) {
        this.Turl = turl;
    }

    public String getTvid() {
        return Tvid;
    }

    public void setTvid(String tvid) {
        this.Tvid = tvid;
    }
}
