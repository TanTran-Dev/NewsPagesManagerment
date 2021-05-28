package com.trantan.newspagesmanagerment.presenter.search;

import com.trantan.newspagesmanagerment.base.presenter.BasePresenter;

public interface SearchPresenter extends BasePresenter {
    void querySearchPost();
    void loadMoreResult();
}
