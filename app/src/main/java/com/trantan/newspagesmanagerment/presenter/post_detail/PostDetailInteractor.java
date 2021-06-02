package com.trantan.newspagesmanagerment.presenter.post_detail;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.BaseInteractor;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseListener;
import com.trantan.newspagesmanagerment.model.response.PostDetail;

public interface PostDetailInteractor extends BaseInteractor {
    void getPostDetails(String postID, int pageIndex, int pageSize,
                        OnResponseListener<ResponseBody<Page<PostDetail>>, ResponseBody> listener);
}
