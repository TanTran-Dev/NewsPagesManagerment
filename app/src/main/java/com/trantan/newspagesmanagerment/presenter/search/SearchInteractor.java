package com.trantan.newspagesmanagerment.presenter.search;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.BaseInteractor;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseListener;
import com.trantan.newspagesmanagerment.model.response.Post;

public interface SearchInteractor extends BaseInteractor {
    void searchPostByTitle(String title, int pageIndex, int pageSize,
                           OnResponseListener<ResponseBody<Page<Post>>, ResponseBody> listener);
}
