package com.trantan.newspagesmanagerment.presenter.main;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.BaseInteractor;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseListener;
import com.trantan.newspagesmanagerment.model.response.Website;

public interface MainInteractor extends BaseInteractor {
    void getWebsites(int pageIndex, int pageSize,
                     OnResponseListener<ResponseBody<Page<Website>>, ResponseBody> listener);
}
