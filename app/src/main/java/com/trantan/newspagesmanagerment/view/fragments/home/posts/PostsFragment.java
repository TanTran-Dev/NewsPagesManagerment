package com.trantan.newspagesmanagerment.view.fragments.home.posts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.trantan.newspagesmanagerment.Constants;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.recycleview.PostsAdapter;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.EndlessLoadingRecyclerViewAdapter;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.RecyclerViewAdapter;
import com.trantan.newspagesmanagerment.base.view.fragment.BaseFragment;
import com.trantan.newspagesmanagerment.event_bus.SelectedTabEvent;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.presenter.home.posts.PostsPresenterImpl;
import com.trantan.newspagesmanagerment.view.activities.DetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsFragment extends BaseFragment<PostsPresenterImpl> implements PostsView,
        SwipeRefreshLayout.OnRefreshListener, EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener,
        RecyclerViewAdapter.OnItemClickListener {
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rcl_view)
    RecyclerView rclPosts;
    @BindView(R.id.ln_no_data)
    LinearLayout lnNoData;

    private PostsAdapter postAdapter;

    @Override
    protected int getLayoutIntResource() {
        return R.layout.fragment_posts;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState, View rootView) {
        ButterKnife.bind(this, rootView);

        postAdapter = new PostsAdapter(getContext());
        postAdapter.addOnItemClickListener(this);
        postAdapter.setLoadingMoreListener(this);
        rclPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        rclPosts.setAdapter(postAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);

    }

    private void getDataFromArgs(Bundle args) {
        int categoryId = args.getInt(Constants.KEY_CATEGORY_ID, -1);
        if (categoryId > 0) {
            getPresenter().setCategoryID(categoryId);
        }
        getPresenter().refreshPosts();
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

    @Override
    protected void initData(Bundle saveInstanceState) {
        Bundle args = getArguments();
        getDataFromArgs(args);
    }

    @Override
    protected PostsPresenterImpl initPresenter() {
        return new PostsPresenterImpl(getContext(), this);
    }

    @Override
    public void refreshPosts(List<Post> posts) {
        postAdapter.refresh(posts);
        if (posts.size() == 0) {
            lnNoData.setVisibility(View.VISIBLE);
            rclPosts.setVisibility(View.GONE);
        } else {
            lnNoData.setVisibility(View.GONE);
            rclPosts.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addPosts(List<Post> posts) {
        postAdapter.addModels(posts, false);
    }

    @Override
    public void enableRefreshingProgress(boolean enable) {
        swipeRefreshLayout.setEnabled(enable);
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
    public void showLoadMoreProgress() {
        postAdapter.showLoadingItem(true);
    }

    @Override
    public void hideLoadMoreProgress() {
        postAdapter.hideLoadingItem();
    }

    @Override
    public void enableLoadMore(boolean enable) {
        postAdapter.enableLoadingMore(enable);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectedTabEvent(SelectedTabEvent selectedTabEvent) {
        //     getPresenter().setCategoryID(selectedTabEvent.getCategory().getId());
    }

    @Override
    public void onRefresh() {
        getPresenter().refreshPosts();
    }

    @Override
    public void onLoadMore() {
        getPresenter().loadMorePosts();
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        Context context = getActivity();
        Post post = postAdapter.getItem(position, Post.class);
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Constants.KEY_POST_ID,  post.getId());
        intent.putExtra(Constants.KEY_TITLE_POST, post.getTitle());
        intent.putExtra(Constants.KEY_DESCRIPTION, post.getDescription());
        intent.putExtra(Constants.KEY_THUMBNAIL, post.getThumbnailUrl());

        context.startActivity(intent);
    }
}
