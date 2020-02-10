package com.trantan.newspagesmanagerment.model;

public class ItemDataNewsContent {
    private String tittle;
    private String image;
    private String desc;
    private String content;

    public ItemDataNewsContent(String tittle, String image, String desc, String content) {
        this.tittle = tittle;
        this.image = image;
        this.desc = desc;
        this.content = content;
    }

    public ItemDataNewsContent() {
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
