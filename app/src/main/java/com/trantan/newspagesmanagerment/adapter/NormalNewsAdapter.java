package com.trantan.newspagesmanagerment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.model.ItemDataNew;
import com.trantan.newspagesmanagerment.model.response.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NormalNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_NORMAL = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private List<Post> posts;
    private ItemClickListener itemClickListener;
    private ILoadMore loadMore;
    boolean isLoading;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;

    public NormalNewsAdapter(RecyclerView recyclerView, ItemClickListener itemClickListener) {
        this.posts = new ArrayList<>();
        this.itemClickListener = itemClickListener;

//        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                totalItemCount = linearLayoutManager.getItemCount();
//                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                    if (loadMore != null)
//                        loadMore.onLoadMore();
//                    isLoading = true;
//                }
//
//            }
//        });
    }

    public void addListData(List<Post> posts) {
        this.posts.addAll(posts);
    }

    public List<Post> getPosts() {
        return posts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_vertical, parent, false);
            return new NormalNewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_loading_more, parent, false);
            return new LoadingViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalNewHolder) {
            final Post post = posts.get(position);
            NormalNewHolder viewHolder = (NormalNewHolder) holder;
            viewHolder.txtTitlePreview.setText(post.getTitle());
            viewHolder.txtDescription.setText(post.getDescription());
            viewHolder.txtCreatedDate.setText(post.getCreatedDate());

            Glide.with(holder.itemView.getContext())
                    .load(post.getThumbnailUrl())
                    .error(R.drawable.placeholder)
                    .centerCrop()
                    .into(viewHolder.imgNews);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        isLoading = false;
    }


    @Override
    public int getItemViewType(int position) {
        return getPosts().get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class NormalNewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_new)
        ImageView imgNews;
        @BindView(R.id.txt_title_preview)
        TextView txtTitlePreview;
        @BindView(R.id.txt_description)
        TextView txtDescription;
        @BindView(R.id.txt_created_date)
        TextView txtCreatedDate;

        public NormalNewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();
                itemClickListener.onItemClick(posts.get(i), imgNews, i);
            });
        }
    }

    public interface ItemClickListener {
        void onItemClick(Post item, View view, int position);

        void onLongClickItem(ItemDataNew itemDataNew);

    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ILoadMore {
        void onLoadMore();
    }
}
