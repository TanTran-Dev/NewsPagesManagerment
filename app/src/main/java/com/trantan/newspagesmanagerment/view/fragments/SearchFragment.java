package com.trantan.newspagesmanagerment.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trantan.newspagesmanagerment.Constants;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.view.activities.DetailActivity;
import com.trantan.newspagesmanagerment.adapter.SearchResultAdapter;
import com.trantan.newspagesmanagerment.adapter.SuggestionAdapter;
import com.trantan.newspagesmanagerment.event_bus.DataNewsEvent;
import com.trantan.newspagesmanagerment.model.ItemDataNew;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, SearchResultAdapter.ItemClickListener {
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.rcl_item_Suggestion)
    RecyclerView rclSuggestion;

    private SuggestionAdapter suggestionAdapter;
    private SearchResultAdapter resultAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        configRecycleViewSuggestion();

        searchView.setOnQueryTextListener(this);
        return view;
    }

    private void configRecycleViewSuggestion() {
        resultAdapter = new SearchResultAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rclSuggestion.setLayoutManager(linearLayoutManager);
////        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
////                linearLayoutManager.getOrientation());
//        rclSuggestion.addItemDecoration(dividerItemDecoration);
        rclSuggestion.setAdapter(resultAdapter);

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
        ItemDataNew dataNew = new ItemDataNew(title, linkImage, description, linkDetail);
        resultAdapter.addItem(dataNew);
        resultAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        resultAdapter.getFilter().filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        resultAdapter.getFilter().filter(query);

        return true;
    }

    @Override
    public void onItemClick(ItemDataNew itemDataNew, View view, int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Constants.KEY_ITEM_NEW, itemDataNew);

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(),view.findViewById(R.id.img_new), "imgNews");
        startActivity(intent, optionsCompat.toBundle());
    }

    @Override
    public void onLongClickItem(ItemDataNew itemDataNew) {

    }
}