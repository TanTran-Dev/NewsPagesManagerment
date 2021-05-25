package com.trantan.newspagesmanagerment.presenter.home.posts;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseAdapter;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.view.fragments.home.posts.PostsView;

import java.util.Collections;


public class PostsPresenterImpl implements PostsPresenter {
    private Context context;
    private PostsView view;
    private PostsInteractor interactor;

    private int websiteID = 1;
    private int categoryID;
    private int currentPage = 0;

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void setWebsiteID(int websiteID) {
        this.websiteID = websiteID;
    }

    public PostsPresenterImpl(Context context, PostsView view) {
        this.context = context;
        this.view = view;
        this.interactor = new PostsInteractorImpl(context);
    }

    @Override
    public void refreshPosts() {
        view.enableRefreshingProgress(false);
        view.showRefreshingProgress();
        interactor.getPosts(websiteID, categoryID, 0, 15,
                new OnResponseAdapter<ResponseBody<Page<Post>>, ResponseBody>(context) {
                    @Override
                    public void complete(boolean success) {
                        view.hideRefreshingProgress();
                        view.enableLoadMore(true);
                        view.enableRefreshingProgress(true);
                        if (!success) {
                            view.refreshPosts(Collections.emptyList());
                        }
                    }

                    @Override
                    public void success(ResponseBody<Page<Post>> body) {
                        Page<Post> page = body.getData();

                        view.enableLoadMore(page.getPageIndex() != page.getMaxPageIndex());
                        currentPage = 0;

                        view.refreshPosts(page.getItems());

                    }
                });
    }

    @Override
    public void loadMorePosts() {
        view.showLoadMoreProgress();
        view.enableRefreshingProgress(false);

        interactor.getPosts(websiteID, categoryID, currentPage + 1, 15,
                new OnResponseAdapter<ResponseBody<Page<Post>>, ResponseBody>(context) {
                    @Override
                    public void complete(boolean success) {
                        view.hideLoadMoreProgress();
                        view.hideRefreshingProgress();
                        view.enableRefreshingProgress(true);
                    }

                    @Override
                    public void success(ResponseBody<Page<Post>> body) {
                        Page<Post> page = body.getData();
                        if (page.getPageIndex() == page.getMaxPageIndex()) {
                            view.enableLoadMore(false);
                        }

                        currentPage++;

                        view.addPosts(page.getItems());
                    }
                });
    }

    @Override
    public void onViewDestroy() {
        interactor.onViewDestroy();
    }
}
