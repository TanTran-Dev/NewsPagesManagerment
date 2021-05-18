package com.trantan.newspagesmanagerment.presenter.home.posts;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.BaseInteractor;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseListener;
import com.trantan.newspagesmanagerment.model.response.Post;

public interface PostsInteractor extends BaseInteractor {
    void getPosts(Integer categoryID, int pageIndex, int pageSize,
                  OnResponseListener<ResponseBody<Page<Post>>, ResponseBody> listener);
}
