package com.trantan.newspagesmanagerment.presenter.main;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.network.ApiClient;
import com.trantan.newspagesmanagerment.base.network.ResponseObserver;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseListener;
import com.trantan.newspagesmanagerment.model.response.Website;
import com.trantan.newspagesmanagerment.service.WebsiteService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainInteractorImpl implements MainInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public MainInteractorImpl(Context context) {
        this.context = context;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getWebsites(int pageIndex, int pageSize,
                            OnResponseListener<ResponseBody<Page<Website>>, ResponseBody> listener) {
        Disposable disposable = ApiClient.getInstance()
                .create(WebsiteService.class)
                .getPageWebsites(null, null, pageIndex, pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new ResponseObserver<>(listener));
        compositeDisposable.add(disposable);
    }

    @Override
    public void onViewDestroy() {
        context = null;
        compositeDisposable.clear();
    }
}
