package com.trantan.newspagesmanagerment.presenter.home;

import com.trantan.newspagesmanagerment.base.presenter.BasePresenter;

public interface HomePresenter extends BasePresenter {
    void retrieveCategories();
    void refreshCategories();
}
