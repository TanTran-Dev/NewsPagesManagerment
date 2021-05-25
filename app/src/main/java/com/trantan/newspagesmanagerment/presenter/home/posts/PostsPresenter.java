package com.trantan.newspagesmanagerment.presenter.home.posts;

import com.trantan.newspagesmanagerment.base.presenter.BasePresenter;

public interface PostsPresenter extends BasePresenter {
    void refreshPosts();
    void loadMorePosts();
}
