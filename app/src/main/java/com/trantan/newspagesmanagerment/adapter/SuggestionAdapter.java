package com.trantan.newspagesmanagerment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.RecyclerViewAdapter;
import com.trantan.newspagesmanagerment.model.ItemDataNewSuggestion;
import com.trantan.newspagesmanagerment.model.response.Post;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuggestionAdapter extends RecyclerViewAdapter {
    public SuggestionAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view = getInflater().inflate(R.layout.item_post_suggestion, parent, false);
        return new PostSuggestionViewHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        Context context = getContext();
        Post post = getItem(position, Post.class);
        PostSuggestionViewHolder viewHolder = (PostSuggestionViewHolder) holder;
        viewHolder.txtTitlePreview.setText(post.getTitle());
        Glide.with(context)
                .load(post.getThumbnailUrl())
                .error(R.drawable.placeholder)
                .centerCrop()
                .into(viewHolder.imgNew);
    }


    class PostSuggestionViewHolder extends NormalViewHolder {
        @BindView(R.id.img_new)
        ImageView imgNew;
        @BindView(R.id.txt_title_preview)
        TextView txtTitlePreview;

        public PostSuggestionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
