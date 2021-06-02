package com.trantan.newspagesmanagerment.presenter.post_detail;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.network.ApiClient;
import com.trantan.newspagesmanagerment.base.network.ResponseObserver;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseListener;
import com.trantan.newspagesmanagerment.model.response.PostDetail;
import com.trantan.newspagesmanagerment.service.PostDetailService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostDetailInteractorImpl implements PostDetailInteractor{
    private Context context;
    private CompositeDisposable compositeDisposable;

    public PostDetailInteractorImpl(Context context) {
        this.context = context;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getPostDetails(String postID, int pageIndex, int pageSize,
                               OnResponseListener<ResponseBody<Page<PostDetail>>, ResponseBody> listener) {
        Disposable disposable = ApiClient.getInstance()
                .create(PostDetailService.class)
                .getPostDetails(postID, null, null, pageIndex, pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new ResponseObserver<>(listener));
        compositeDisposable.add(disposable);
    }

    @Override
    public void onViewDestroy() {
        this.context = null;
        this.compositeDisposable.clear();
    }
}
