package com.trantan.newspagesmanagerment.presenter.search;

import android.content.Context;

import com.trantan.newspagesmanagerment.base.model.Page;
import com.trantan.newspagesmanagerment.base.model.ResponseBody;
import com.trantan.newspagesmanagerment.base.presenter.OnResponseAdapter;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.view.fragments.search.SearchView;

import java.util.Collections;

public class SearchPresenterImpl implements SearchPresenter{
    private Context context;
    private SearchView view;
    private SearchInteractor interactor;

    private String query;

    private int currentPage = 0;

    public SearchPresenterImpl(Context context, SearchView searchView) {
        this.context = context;
        this.view = searchView;
        this.interactor = new SearchInteractorImpl(context);
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public void querySearchPost() {
        view.showRefreshingProgress();
        interactor.searchPostByTitle(query, 0, 20,
                new OnResponseAdapter<ResponseBody<Page<Post>>, ResponseBody>(context) {
                    @Override
                    public void complete(boolean success) {
                        view.hideRefreshingProgress();
                        view.enableLoadingMore(true);

                        if (!success){
                            view.refreshResults(Collections.emptyList(), 0);
                        }
                    }

                    @Override
                    public void success(ResponseBody<Page<Post>> body) {
                        Page<Post> page = body.getData();

                        view.enableLoadingMore(page.getPageIndex() != page.getMaxPageIndex());
                        currentPage = 0;
                        view.refreshResults(page.getItems(), page.getTotalItems());

                    }
                });
    }

    @Override
    public void loadMoreResult() {
        view.showLoadMoreProgress();
        interactor.searchPostByTitle(query, currentPage + 1, 20,
                new OnResponseAdapter<ResponseBody<Page<Post>>, ResponseBody>(context) {
                    @Override
                    public void complete(boolean success) {
                        view.hideLoadMoreProgress();
                    }

                    @Override
                    public void success(ResponseBody<Page<Post>> body) {
                        Page<Post> page = body.getData();

                       if (page.getPageIndex() == page.getMaxPageIndex()){
                           view.enableLoadingMore(false);
                       }

                       currentPage ++;
                       view.addResults(page.getItems());

                    }
                });
    }

    @Override
    public void onViewDestroy() {
        interactor.onViewDestroy();
    }
}
