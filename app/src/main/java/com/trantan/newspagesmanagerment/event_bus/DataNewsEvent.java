package com.trantan.newspagesmanagerment.event_bus;


import com.trantan.newspagesmanagerment.model.ItemDataNew;

public class DataNewsEvent {
   private ItemDataNew itemDataNew;

    public DataNewsEvent() {
    }

    public DataNewsEvent(ItemDataNew itemDataNew) {
        this.itemDataNew = itemDataNew;
    }

    public ItemDataNew getItemDataNew() {
        return itemDataNew;
    }

    public void setItemDataNew(ItemDataNew itemDataNew) {
        this.itemDataNew = itemDataNew;
    }
}
