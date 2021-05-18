package com.trantan.newspagesmanagerment.event_bus;

import com.trantan.newspagesmanagerment.model.response.Category;

public class SelectedTabEvent {
    private Category category;

    public SelectedTabEvent() {
    }

    public SelectedTabEvent(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
