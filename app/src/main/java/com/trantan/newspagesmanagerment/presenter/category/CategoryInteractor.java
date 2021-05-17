package com.trantan.newspagesmanagerment.presenter.category;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.BaseInteractor;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseListener;
import com.trantan.newspagesmanagerment.model.response.Category;

public interface CategoryInteractor extends BaseInteractor {
    void retrieveCategories(OnResponseListener<ResponseBody<Page<Category>>, ResponseBody> listener);

    void refreshCategories(int pageIndex, int pageSize,
                           OnResponseListener<ResponseBody<Page<Category>>, ResponseBody> listener);
}
