package com.trantan.newspagesmanagerment.presenter.main;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseAdapter;
import com.trantan.newspagesmanagerment.model.response.Website;
import com.trantan.newspagesmanagerment.view.activities.MainView;


public class MainPresenterImpl implements MainPresenter{
    private Context context;
    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(Context context, MainView view) {
        this.context = context;
        this.view = view;
        this.interactor = new MainInteractorImpl(context);
    }

    @Override
    public void refreshWebsites() {
        interactor.getWebsites(0, 10,
                new OnResponseAdapter<ResponseBody<Page<Website>>, ResponseBody>(context) {
                    @Override
                    public void complete(boolean success) {

                    }

                    @Override
                    public void success(ResponseBody<Page<Website>> body) {
                        Page<Website> page = body.getData();
                        view.refreshWebsites(page.getItems());
                    }
                });
    }

    @Override
    public void onViewDestroy() {
        interactor.onViewDestroy();
    }
}
