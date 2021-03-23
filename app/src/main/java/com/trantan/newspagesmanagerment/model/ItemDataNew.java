package com.trantan.newspagesmanagerment.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "New")
public class ItemDataNew implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "link_image")
    private String linkImage;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "created_date")
    private String createdDate;
    @ColumnInfo(name = "link_detail")
    private String linkDetail;

    public ItemDataNew(String title, String linkImage, String description, String createdDate, String linkDetail) {
        this.title = title;
        this.linkImage = linkImage;
        this.description = description;
        this.createdDate = createdDate;
        this.linkDetail = linkDetail;
    }

    public ItemDataNew(String title, String linkImage, String description, String linkDetail) {
        this.title = title;
        this.linkImage = linkImage;
        this.description = description;
        this.linkDetail = linkDetail;
    }

    public ItemDataNew() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkDetail() {
        return linkDetail;
    }

    public void setLinkDetail(String linkDetail) {
        this.linkDetail = linkDetail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
