package com.trantan.newspagesmanagerment.model;

public
class ItemDataNewSuggestion {
    private String title;
    private String linkImage;

    public ItemDataNewSuggestion() {
    }

    public ItemDataNewSuggestion(String title, String linkImage) {
        this.title = title;
        this.linkImage = linkImage;
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
}
