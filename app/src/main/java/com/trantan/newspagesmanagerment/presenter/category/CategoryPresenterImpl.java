package com.trantan.newspagesmanagerment.presenter.category;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseAdapter;
import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.view.fragments.category.CategoryView;

import java.util.List;

public class CategoryPresenterImpl implements CategoryPresenter{
    private Context context;
    private CategoryView view;
    private CategoryInteractor interactor;


    public CategoryPresenterImpl(Context context, CategoryView view) {
        this.context = context;
        this.view = view;
        this.interactor = new CategoryInteractorImpl(context);
    }

    @Override
    public void retrieveCategories() {

    }

    @Override
    public void refreshCategories() {
        interactor.refreshCategories(0, 50,
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
