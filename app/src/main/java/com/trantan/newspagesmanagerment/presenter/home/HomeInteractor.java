package com.trantan.newspagesmanagerment.presenter.home;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.BaseInteractor;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseListener;
import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.model.response.Post;

public interface HomeInteractor extends BaseInteractor {
    void retrieveCategories(OnResponseListener<ResponseBody<Page<Category>>, ResponseBody> listener);

    void getCategories(int pageIndex, int pageSize,
                       OnResponseListener<ResponseBody<Page<Category>>, ResponseBody> listener);

}
