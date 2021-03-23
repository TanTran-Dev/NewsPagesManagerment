package com.trantan.newspagesmanagerment.view;

import com.trantan.newspagesmanagerment.model.ItemDataNew;

public interface NewsView {
    void showRefreshingProgress();
    void hideRefreshingProgress();
    void enableRefreshing(boolean enable);
}
