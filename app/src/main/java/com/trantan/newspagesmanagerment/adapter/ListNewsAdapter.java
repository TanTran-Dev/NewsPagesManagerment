package com.trantan.newspagesmanagerment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trantan.newspagesmanagerment.Constants;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.activities.DetailActivity;
import com.trantan.newspagesmanagerment.event_bus.SelectedTabEvent;
import com.trantan.newspagesmanagerment.model.ItemDataNew;
import com.trantan.newspagesmanagerment.model.TopicModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_TOP_NEWS = 0;
    public static final int VIEW_TYPE_NORMAL_NEWS = 1;
    private List<ItemDataNew> topNews;
    private TopicModel topicModel;


    public ListNewsAdapter() {
        this.topNews = new ArrayList<>();
        this.topicModel = new TopicModel();
    }

    public void addListTop(List<ItemDataNew> topNews) {
        this.topNews.addAll(topNews);
    }

    public void setTopicModel(TopicModel topicModel) {
        this.topicModel = topicModel;
    }

    public List<ItemDataNew> getTopNews() {
        return topNews;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_TOP_NEWS: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_top_news, parent, false);
                return new ListTopNewsViewHolder(view);
            }

            case VIEW_TYPE_NORMAL_NEWS: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_normal_news, parent, false);
                return new ListNormalNewViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListTopNewsViewHolder) {
            ListTopNewsViewHolder viewHolder = (ListTopNewsViewHolder) holder;
            viewHolder.topNewsAdapter.addListData(getTopNews());
            if (getTopNews() != null) {
                viewHolder.txtTopicTitle.setVisibility(View.VISIBLE);
            } else {
                viewHolder.txtTopicTitle.setVisibility(View.GONE);
            }
        } else if (holder instanceof ListNormalNewViewHolder) {
            ListNormalNewViewHolder viewHolder = (ListNormalNewViewHolder) holder;
            if (topicModel.getDataNewList() != null && !topicModel.getDataNewList().isEmpty()) {
                viewHolder.txtHeaderTitle.setText(topicModel.getTitle());
                viewHolder.normalNewAdapter.addListData(topicModel.getDataNewList());
            }
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_TOP_NEWS;
        } else {
            return VIEW_TYPE_NORMAL_NEWS;
        }
    }

    class ListTopNewsViewHolder extends RecyclerView.ViewHolder implements TopNewsAdapter.ItemClickListener {
        @BindView(R.id.rcl_top_news)
        RecyclerView rclTopNews;
        @BindView(R.id.txt_topic_title)
        TextView txtTopicTitle;
        private TopNewsAdapter topNewsAdapter;

        public ListTopNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            topNewsAdapter = new TopNewsAdapter(this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false);
            rclTopNews.setLayoutManager(layoutManager);
            rclTopNews.setAdapter(topNewsAdapter);
        }

        @Override
        public void onItemClick(ItemDataNew itemDataNew, View view, int position) {
            Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
            intent.putExtra(Constants.KEY_ITEM_NEW, itemDataNew);

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation((Activity) itemView.getContext(), view.findViewById(R.id.img_new), "imgNews");
            itemView.getContext().startActivity(intent, optionsCompat.toBundle());
        }

        @Override
        public void onLongClickItem(ItemDataNew itemDataNew) {

        }
    }

    class ListNormalNewViewHolder extends RecyclerView.ViewHolder implements NormalNewsAdapter.ItemClickListener {
        @BindView(R.id.txt_header_title)
        TextView txtHeaderTitle;
        @BindView(R.id.rcl_normal)
        RecyclerView rclView;
        private NormalNewsAdapter normalNewAdapter;

        public ListNormalNewViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            EventBus.getDefault().register(this);

            normalNewAdapter = new NormalNewsAdapter(rclView, this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            rclView.setLayoutManager(layoutManager);
            rclView.setAdapter(normalNewAdapter);

            RecyclerView.OnItemTouchListener itemTouchListener = new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                    int action = e.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_MOVE: {
                            rv.getParent().requestDisallowInterceptTouchEvent(true);
                        }
                        break;
                    }
                    return false;
                }

                @Override
                public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            };

            rclView.addOnItemTouchListener(itemTouchListener);

            normalNewAdapter.setLoadMore(new NormalNewsAdapter.ILoadMore() {
                @Override
                public void onLoadMore() {
                    if (normalNewAdapter.getDataNews().size() <= 10) {

                    }
                }
            });
        }

        @Subscribe(threadMode = ThreadMode.MAIN)
        public void onSelectedTabEvent(SelectedTabEvent event) {
            txtHeaderTitle.setText(event.getTitle());
        }

        @Override
        public void onItemClick(ItemDataNew itemDataNew, View view, int position) {
            Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
            intent.putExtra(Constants.KEY_ITEM_NEW, itemDataNew);

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation((Activity) itemView.getContext(), view.findViewById(R.id.img_new), "imgNews");
            itemView.getContext().startActivity(intent, optionsCompat.toBundle());
        }

        @Override
        public void onLongClickItem(ItemDataNew itemDataNew) {

        }
    }
}
