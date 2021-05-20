package com.trantan.newspagesmanagerment.presenter.home.posts;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseAdapter;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.view.fragments.home.posts.PostsView;


public class PostsPresenterImpl implements PostsPresenter {
    private Context context;
    private PostsView view;
    private PostsInteractor interactor;

    private int categoryID;

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public PostsPresenterImpl(Context context, PostsView view) {
        this.context = context;
        this.view = view;
        this.interactor = new PostsInteractorImpl(context);
    }

    @Override
    public void refreshPosts() {
        view.showRefreshingProgress();
        view.enableRefreshingProgress(false);
        interactor.getPosts(categoryID, 0, 50,
                new OnResponseAdapter<ResponseBody<Page<Post>>, ResponseBody>(context) {
                    @Override
                    public void complete(boolean success) {
                        view.hideRefreshingProgress();
                        view.enableRefreshingProgress(true);
                    }

                    @Override
                    public void success(ResponseBody<Page<Post>> body) {
                        Page<Post> posts = body.getData();
                        view.refreshPosts(posts.getItems());
                    }
                });
    }

    @Override
    public void onViewDestroy() {
        interactor.onViewDestroy();
    }
}
