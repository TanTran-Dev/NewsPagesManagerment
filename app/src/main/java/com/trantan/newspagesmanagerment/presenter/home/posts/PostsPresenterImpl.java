package com.trantan.newspagesmanagerment.presenter.home.posts;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseAdapter;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.view.fragments.home.posts.PostsView;

import java.util.List;

public class PostsPresenterImpl implements PostsPresenter {
    private Context context;
    private PostsView view;
    private PostsInteractor interactor;

    public PostsPresenterImpl(Context context, PostsView view) {
        this.context = context;
        this.view = view;
        this.interactor = new PostsInteractorImpl(context);
    }

    @Override
    public void refreshPosts(Integer categoryID) {
        interactor.getPosts(categoryID, 0, 50,
                new OnResponseAdapter<ResponseBody<Page<Post>>, ResponseBody>(context) {
                    @Override
                    public void complete(boolean success) {

                    }

                    @Override
                    public void success(ResponseBody<Page<Post>> body) {
                        List<Post> posts = body.getData().getItems();
                        view.refreshPosts(posts);
                    }
                });
    }

    @Override
    public void onViewDestroy() {
        interactor.onViewDestroy();
    }
}
