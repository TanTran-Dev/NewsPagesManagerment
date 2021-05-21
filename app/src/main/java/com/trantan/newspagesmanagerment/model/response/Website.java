package com.trantan.newspagesmanagerment.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Website {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("logoURL")
    @Expose
    private String logoURL;

    public Website() {
    }

    public Website(int id, String name, String url, String logoURL) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.logoURL = logoURL;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
