package com.trantan.newspagesmanagerment.model;

public class ItemDataNews {
    private String tittle;
    private String linkImage;
    private String content;
    private String linkDetail;

    public ItemDataNews(String tittle, String linkImage, String content, String linkDetail) {
        this.tittle = tittle;
        this.linkImage = linkImage;
        this.content = content;
        this.linkDetail = linkDetail;
    }

    public ItemDataNews() {
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLinkDetail() {
        return linkDetail;
    }

    public void setLinkDetail(String linkDetail) {
        this.linkDetail = linkDetail;
    }
}
