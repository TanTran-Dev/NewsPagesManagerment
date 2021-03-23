package com.trantan.newspagesmanagerment.event_bus;

public class SelectedTabEvent {
    private String title;

    public SelectedTabEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
