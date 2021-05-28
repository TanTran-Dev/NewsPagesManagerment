package com.trantan.newspagesmanagerment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.EndlessLoadingRecyclerViewAdapter;
import com.trantan.newspagesmanagerment.model.ItemDataNew;
import com.trantan.newspagesmanagerment.model.response.Post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultAdapter extends EndlessLoadingRecyclerViewAdapter {

    public SearchResultAdapter(Context context) {
        super(context, false);
    }


    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_bookmark, parent, false);
        return new PostNormalHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        Context context = getContext();
        final Post post = getItem(position, Post.class);
        PostNormalHolder viewHolder = (PostNormalHolder) holder;
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


    class PostNormalHolder extends NormalViewHolder {
        @BindView(R.id.img_new)
        ImageView imgNews;
        @BindView(R.id.txt_title_preview)
        TextView txtTitlePreview;
        @BindView(R.id.txt_description)
        TextView txtDescription;
        @BindView(R.id.txt_created_date)
        TextView txtCreatedDate;

        public PostNormalHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
