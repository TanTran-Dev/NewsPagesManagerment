package com.trantan.newspagesmanagerment.event_bus;

import com.trantan.newspagesmanagerment.model.response.Website;

public class EventChangeWebsite {
    private Website website;

    public EventChangeWebsite(Website website) {
        this.website = website;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }
}
