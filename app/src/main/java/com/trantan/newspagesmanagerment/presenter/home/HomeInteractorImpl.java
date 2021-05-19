package com.trantan.newspagesmanagerment.presenter.home;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.network.ApiClient;
import com.trantan.newspagesmanagerment.base.network.ResponseObserver;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseListener;
import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.service.category.CategoryService;
import com.trantan.newspagesmanagerment.service.post.PostService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeInteractorImpl implements HomeInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public HomeInteractorImpl(Context context) {
        this.context = context;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void retrieveCategories(OnResponseListener<ResponseBody<Page<Category>>, ResponseBody> listener) {
        Disposable disposable = ApiClient.getInstance()
                .create(CategoryService.class)
                .retrieveCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new ResponseObserver<>(listener));
        compositeDisposable.add(disposable);
    }

    @Override
    public void getCategories(int pageIndex, int pageSize,
                              OnResponseListener<ResponseBody<Page<Category>>, ResponseBody> listener) {
        Disposable disposable = ApiClient.getInstance()
                .create(CategoryService.class)
                .getCategories(null, null, pageIndex, pageSize)
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
