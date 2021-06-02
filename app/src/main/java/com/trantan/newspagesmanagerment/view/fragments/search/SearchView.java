package com.trantan.newspagesmanagerment.view.fragments.search;

import com.trantan.newspagesmanagerment.model.response.Post;

import java.util.List;

public interface SearchView {
    void refreshResults(List<Post> posts, int totalItems);
    void addResults(List<Post> posts);

    void showRefreshingProgress();
    void hideRefreshingProgress();
    void enableRefreshing(boolean enable);

    void enableLoadingMore(boolean enable);
    void showLoadMoreProgress();
    void hideLoadMoreProgress();
}
