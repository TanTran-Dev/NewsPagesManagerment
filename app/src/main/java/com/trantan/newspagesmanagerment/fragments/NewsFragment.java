package com.trantan.newspagesmanagerment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.trantan.newspagesmanagerment.Constants;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.activities.DetailActivity;
import com.trantan.newspagesmanagerment.adapter.ListNewsAdapter;
import com.trantan.newspagesmanagerment.adapter.SearchResultAdapter;
import com.trantan.newspagesmanagerment.model.ItemDataNew;
import com.trantan.newspagesmanagerment.model.TopicModel;
import com.trantan.newspagesmanagerment.networks._24h.News24HCrawler;
import com.trantan.newspagesmanagerment.networks._24h.News24HTopCrawler;
import com.trantan.newspagesmanagerment.view.NewsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment implements SearchResultAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener , NewsView {
    @BindView(R.id.rcl_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private ListNewsAdapter listNewsAdapter;
    private TopicModel topicModel;
    private String link;

    public void setLink(String link) {
        this.link = link;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_news,
                container,
                false
        );

        ButterKnife.bind(this, view);
        listNewsAdapter = new ListNewsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listNewsAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
        return view;
    }

    @Override
    public void onItemClick(ItemDataNew itemDataNew, View view, int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Constants.KEY_ITEM_NEW, itemDataNew);

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(), view.findViewById(R.id.img_new), "imgNews");
        startActivity(intent, optionsCompat.toBundle());
    }

    @Override
    public void onLongClickItem(ItemDataNew itemDataNew) {

    }

    @Override
    public void onRefresh() {
        new News24HTopCrawler<>(listNewsAdapter, this).execute(link);
        new News24HCrawler<>(listNewsAdapter, this).execute(link);
    }

    @Override
    public void showRefreshingProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshingProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void enableRefreshing(boolean enable) {
        swipeRefreshLayout.setEnabled(enable);
    }
}
