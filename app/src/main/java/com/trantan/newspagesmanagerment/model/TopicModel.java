package com.trantan.newspagesmanagerment.model;

import java.util.List;

public class TopicModel {
    private String title;
    private List<ItemDataNew> dataNewList;

    public TopicModel() {
    }

    public TopicModel(String title, List<ItemDataNew> dataNewList) {
        this.title = title;
        this.dataNewList = dataNewList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemDataNew> getDataNewList() {
        return dataNewList;
    }

    public void setDataNewList(List<ItemDataNew> dataNewList) {
        this.dataNewList = dataNewList;
    }
}
