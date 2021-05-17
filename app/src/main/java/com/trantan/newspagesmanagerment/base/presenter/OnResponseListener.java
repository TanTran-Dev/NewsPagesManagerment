package com.trantan.newspagesmanagerment.base.presenter;

import com.trantan.newspagesmanagerment.base.model.ResponseBody;

public interface OnResponseListener<T extends ResponseBody, U extends ResponseBody> {
    void onSuccess(T body);
    void onError(U body);
    void onError();
    void onNetworkConnectionError();
    void onTimeOut();
}