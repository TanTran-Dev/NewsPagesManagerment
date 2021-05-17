package com.trantan.newspagesmanagerment.presenter.category;

import com.trantan.newspagesmanagerment.base.presenter.BasePresenter;

public interface CategoryPresenter extends BasePresenter {
    void retrieveCategories();
    void refreshCategories();
}
