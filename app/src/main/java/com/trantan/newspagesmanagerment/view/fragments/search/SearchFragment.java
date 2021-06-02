package com.trantan.newspagesmanagerment.view.fragments.search;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.BottomPagerAdapter;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.EndlessLoadingRecyclerViewAdapter;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.RecyclerViewAdapter;
import com.trantan.newspagesmanagerment.base.view.fragment.BaseFragment;
import com.trantan.newspagesmanagerment.custom_views.LoadingDialog;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.presenter.search.SearchPresenterImpl;
import com.trantan.newspagesmanagerment.adapter.SearchResultAdapter;
import com.trantan.newspagesmanagerment.adapter.SuggestionAdapter;
import com.trantan.newspagesmanagerment.event_bus.DataNewsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends BaseFragment<SearchPresenterImpl> implements
        SearchView.OnQueryTextListener,
        com.trantan.newspagesmanagerment.view.fragments.search.SearchView, RecyclerViewAdapter.OnItemClickListener,
        EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener {
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.rclSearchResult)
    RecyclerView rclSearchResult;
    @BindView(R.id.rclSuggestion)
    RecyclerView rclSearchSuggestion;
    @BindView(R.id.layoutSuggestion)
    CardView layoutSuggestion;
    @BindView(R.id.txtTotalResult)
    TextView txtTotalResult;
    @BindView(R.id.ln_no_data)
    LinearLayout lnNoData;

    private SuggestionAdapter suggestionAdapter;
    private SearchResultAdapter resultAdapter;

    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutIntResource() {
        return R.layout.fragment_search;
    }

    @Override
    public String getName() {
        return BottomPagerAdapter.SEARCH_FRAGMENT;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState, View rootView) {
        ButterKnife.bind(this, rootView);
        loadingDialog = new LoadingDialog(getContext());
        configRecycleViewSuggestion();

        searchView.setOnQueryTextListener(this);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {

    }

    @Override
    protected SearchPresenterImpl initPresenter() {
        return new SearchPresenterImpl(getContext(), this);
    }


    private void configRecycleViewSuggestion() {
//        suggestionAdapter = new SuggestionAdapter(getContext());
//        suggestionAdapter.addOnItemClickListener(this);
//        rclSearchSuggestion.setLayoutManager(new LinearLayoutManager(getContext()));
//////        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
//////                linearLayoutManager.getOrientation());
////        rclSuggestion.addItemDecoration(dividerItemDecoration);
//        rclSearchSuggestion.setAdapter(suggestionAdapter);


        resultAdapter = new SearchResultAdapter(getContext());
        resultAdapter.addOnItemClickListener(this);
        resultAdapter.setLoadingMoreListener(this);
        rclSearchResult.setLayoutManager(new LinearLayoutManager(getContext()));
        rclSearchResult.setAdapter(resultAdapter);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCrawlEvent(DataNewsEvent dataNewsEvent) {
        String title = dataNewsEvent.getItemDataNew().getTitle();
        String linkImage = dataNewsEvent.getItemDataNew().getLinkImage();
        String linkDetail = dataNewsEvent.getItemDataNew().getLinkDetail();
        String description = dataNewsEvent.getItemDataNew().getDescription();
        Post dataNew = new Post(title, linkImage, description, linkDetail);
        suggestionAdapter.addModel(dataNew, false);
        suggestionAdapter.notifyDataSetChanged();
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        getPresenter().setQuery(query);
        getPresenter().querySearchPost();

        searchView.setFocusable(false);

        hideKeyboard(getActivity());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {

        return true;
    }


    @Override
    public void refreshResults(List<Post> posts, int totalItems) {
//        if (posts.size() > 0){
//            layoutSuggestion.setVisibility(View.VISIBLE);
//        } else {
//            layoutSuggestion.setVisibility(View.GONE);
//        }
//        suggestionAdapter.refresh(posts);
//        suggestionAdapter.notifyDataSetChanged();
        if (totalItems > 0) {
            txtTotalResult.setText("Tìm thấy: " + totalItems + " kết quả");
            resultAdapter.refresh(posts);
            resultAdapter.notifyDataSetChanged();

            lnNoData.setVisibility(View.GONE);
            txtTotalResult.setVisibility(View.VISIBLE);
            rclSearchResult.setVisibility(View.VISIBLE);
        } else {
            lnNoData.setVisibility(View.VISIBLE);
            txtTotalResult.setVisibility(View.GONE);
            rclSearchResult.setVisibility(View.GONE);
        }
    }

    @Override
    public void addResults(List<Post> posts) {
        resultAdapter.addModels(posts, false);
    }

    @Override
    public void showRefreshingProgress() {
        loadingDialog.show();
    }

    @Override
    public void hideRefreshingProgress() {
        loadingDialog.hide();
    }

    @Override
    public void enableRefreshing(boolean enable) {

    }

    @Override
    public void enableLoadingMore(boolean enable) {
        resultAdapter.enableLoadingMore(enable);
    }

    @Override
    public void showLoadMoreProgress() {
        resultAdapter.showLoadingItem(true);
    }

    @Override
    public void hideLoadMoreProgress() {
        resultAdapter.hideLoadingItem();
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
//
//        Intent intent = new Intent(getContext(), DetailActivity.class);
//        intent.putExtra(Constants.KEY_ITEM_NEW, itemDataNew);
//
//        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
//                .makeSceneTransitionAnimation(getActivity(),view.findViewById(R.id.img_new), "imgNews");
//        startActivity(intent, optionsCompat.toBundle());
    }

    @Override
    public void onLoadMore() {
        getPresenter().loadMoreResult();
    }
}