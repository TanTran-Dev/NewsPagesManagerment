package com.trantan.newspagesmanagerment.view.fragments.home.posts;

import com.trantan.newspagesmanagerment.model.response.Post;

import java.util.List;

public interface PostsView {
    void refreshPosts(List<Post> posts);

    void enableRefreshingProgress(boolean enable);
    void showRefreshingProgress();
    void hideRefreshingProgress();
}
