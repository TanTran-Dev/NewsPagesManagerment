package com.trantan.newspagesmanagerment.model.response;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "Post")
public class Post implements Serializable {
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private String id;

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("categoryDTO")
    @Expose
    @ColumnInfo(name = "category_id")
    private Category category;

    @SerializedName("websiteDTO")
    @Expose
    @ColumnInfo(name = "website_id")
    private Website website;

    @SerializedName("thumbnailUrl")
    @Expose
    @ColumnInfo(name = "thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("linkDetail")
    @Expose
    @ColumnInfo(name = "link_detail")
    private String linkDetail;

    @SerializedName("createdDate")
    @Expose
    @ColumnInfo(name = "created_date")
    private String createdDate;

    public Post() {
    }

    public Post(String id, String title, String description, Category category, Website website,
                String thumbnailUrl, String linkDetail, String createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.website = website;
        this.thumbnailUrl = thumbnailUrl;
        this.linkDetail = linkDetail;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getLinkDetail() {
        return linkDetail;
    }

    public void setLinkDetail(String linkDetail) {
        this.linkDetail = linkDetail;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }
}
