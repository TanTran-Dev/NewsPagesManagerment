package com.trantan.newspagesmanagerment.view.fragments.home.posts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.PostsAdapter;
import com.trantan.newspagesmanagerment.base.view.BaseFragment;
import com.trantan.newspagesmanagerment.event_bus.SelectedTabEvent;
import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.presenter.home.posts.PostsPresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsFragment extends BaseFragment<PostsPresenterImpl> implements PostsView,
        SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rcl_view)
    RecyclerView recyclerView;

    private PostsAdapter postAdapter;
    @Override
    protected int getLayoutIntResource() {
        return R.layout.fragment_posts;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState, View rootView) {
        ButterKnife.bind(this, rootView);

        postAdapter = new PostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(postAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);

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

    }

    @Override
    protected PostsPresenterImpl initPresenter() {
        return new PostsPresenterImpl(getContext(), this);
    }

    @Override
    public void refreshPosts(List<Post> posts) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectedTabEvent(SelectedTabEvent selectedTabEvent) {
        getPresenter().refreshPosts(selectedTabEvent.getCategory().getId());
    }

    @Override
    public void onRefresh() {
//        getPresenter().refreshPosts();
    }
}
