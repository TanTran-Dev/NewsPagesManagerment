package com.trantan.newspagesmanagerment.presenter.home;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseAdapter;
import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.view.fragments.home.HomeView;

import java.util.List;

public class HomePresenterImpl implements HomePresenter {
    private Context context;
    private HomeView view;
    private HomeInteractor interactor;


    public HomePresenterImpl(Context context, HomeView view) {
        this.context = context;
        this.view = view;
        this.interactor = new HomeInteractorImpl(context);
    }

    @Override
    public void retrieveCategories() {

    }

    @Override
    public void refreshCategories() {
        interactor.getCategories(0, 50,
                new OnResponseAdapter<ResponseBody<Page<Category>>, ResponseBody>(context) {
                    @Override
                    public void complete(boolean success) {

                    }

                    @Override
                    public void success(ResponseBody<Page<Category>> body) {
                        List<Category> categories = body.getData().getItems();
                        view.refreshListCategories(categories);
                    }
                });
    }

    @Override
    public void onViewDestroy() {
        interactor.onViewDestroy();
    }
}
