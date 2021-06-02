package com.trantan.newspagesmanagerment.presenter.search;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.network.ApiClient;
import com.trantan.newspagesmanagerment.base.network.ResponseObserver;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseListener;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.service.PostService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchInteractorImpl implements SearchInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public SearchInteractorImpl(Context context) {
        this.context = context;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void searchPostByTitle(String title, int pageIndex, int pageSize,
                                  OnResponseListener<ResponseBody<Page<Post>>, ResponseBody> listener) {
        Disposable disposable = ApiClient.getInstance()
                .create(PostService.class)
                .searchPost(title, null, null, pageIndex, pageSize)
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