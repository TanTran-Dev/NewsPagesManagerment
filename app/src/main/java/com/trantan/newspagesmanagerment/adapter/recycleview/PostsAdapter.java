package com.trantan.newspagesmanagerment.adapter.recycleview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.EndlessLoadingRecyclerViewAdapter;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.RecyclerViewAdapter;
import com.trantan.newspagesmanagerment.model.response.Post;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PostsAdapter extends EndlessLoadingRecyclerViewAdapter {

    public PostsAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view = getInflater().inflate(R.layout.item_post_vertical, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        Context context = getContext();
        PostViewHolder viewHolder = (PostViewHolder) holder;

        Post post = getItem(position, Post.class);
        viewHolder.txtTitlePreview.setText(post.getTitle());
        viewHolder.txtDescription.setText(post.getDescription());
        viewHolder.txtCreatedDate.setText(post.getCreatedDate());

        Glide.with(context)
                .load(post.getThumbnailUrl())
                .error(R.drawable.placeholder)
                .centerCrop()
                .into(viewHolder.imgNews);
    }

    @Override
    protected RecyclerView.ViewHolder initLoadingViewHolder(ViewGroup parent) {
        View view = getInflater().inflate(R.layout.item_loading, parent, false);
        return new LoadingViewHolder(view);
    }

    @Override
    protected void bindLoadingViewHolder(LoadingViewHolder loadingViewHolder, int position) {

    }

    class PostViewHolder extends NormalViewHolder {
        @BindView(R.id.img_new)
        ImageView imgNews;
        @BindView(R.id.txt_title_preview)
        TextView txtTitlePreview;
        @BindView(R.id.txt_description)
        TextView txtDescription;
        @BindView(R.id.txt_created_date)
        TextView txtCreatedDate;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
