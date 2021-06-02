package com.trantan.newspagesmanagerment.event_bus;


import com.trantan.newspagesmanagerment.model.response.Post;

public class SaveNewEvent {
    private Post post;

    public SaveNewEvent() {
    }

    public SaveNewEvent(Post post) {
        this.post = post;
    }

    public Post getItemDataNew() {
        return post;
    }

    public void setItemDataNew(Post post) {
        this.post = post;
    }
}
