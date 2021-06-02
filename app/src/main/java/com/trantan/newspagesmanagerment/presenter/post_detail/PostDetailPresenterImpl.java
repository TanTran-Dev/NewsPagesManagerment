package com.trantan.newspagesmanagerment.presenter.post_detail;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseAdapter;
import com.trantan.newspagesmanagerment.model.response.PostDetail;
import com.trantan.newspagesmanagerment.view.activities.PostDetailView;

import java.util.List;

public class PostDetailPresenterImpl implements PostDetailPresenter{
    private Context context;
    private PostDetailView view;
    private PostDetailInteractor interactor;

    private String postID;

    public PostDetailPresenterImpl(Context context, PostDetailView view) {
        this.context = context;
        this.view = view;
        this.interactor = new PostDetailInteractorImpl(context);
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    @Override
    public void refreshDataDetail() {
        interactor.getPostDetails(postID, 0, 50,
                new OnResponseAdapter<ResponseBody<Page<PostDetail>>, ResponseBody>(context) {
                    @Override
                    public void complete(boolean success) {

                    }

                    @Override
                    public void success(ResponseBody<Page<PostDetail>> body) {
                        Page<PostDetail> page = body.getData();
                        view.refreshPostDetails(page.getItems());
                    }
                });
    }

    @Override
    public void onViewDestroy() {
        interactor.onViewDestroy();
    }
}
