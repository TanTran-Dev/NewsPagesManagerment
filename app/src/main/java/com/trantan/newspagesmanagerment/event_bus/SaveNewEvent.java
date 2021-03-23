package com.trantan.newspagesmanagerment.event_bus;


import com.trantan.newspagesmanagerment.model.ItemDataNew;

public class SaveNewEvent {
    private ItemDataNew itemDataNew;

    public SaveNewEvent() {
    }

    public SaveNewEvent(ItemDataNew itemDataNew) {
        this.itemDataNew = itemDataNew;
    }

    public ItemDataNew getItemDataNew() {
        return itemDataNew;
    }

    public void setItemDataNew(ItemDataNew itemDataNew) {
        this.itemDataNew = itemDataNew;
    }
}
