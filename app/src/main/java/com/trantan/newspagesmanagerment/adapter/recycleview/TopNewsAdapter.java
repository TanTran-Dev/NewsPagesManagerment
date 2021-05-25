package com.trantan.newspagesmanagerment.adapter.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.database.DatabaseHelper;
import com.trantan.newspagesmanagerment.event_bus.SaveNewEvent;
import com.trantan.newspagesmanagerment.model.ItemDataNew;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.TopNewsViewHolder> {
    private List<ItemDataNew> topDataNews;
    private ItemClickListener itemClickListener;

    public TopNewsAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.topDataNews = new ArrayList<>();
    }

    public void addListData(List<ItemDataNew> topDataNews) {
        this.topDataNews.addAll(topDataNews);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public TopNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post_horizontal, parent, false);
        return new TopNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopNewsViewHolder holder, int position) {
        final ItemDataNew itemDataNew = topDataNews.get(position);
        holder.txtTitlePreview.setText(itemDataNew.getTitle());
        holder.txtDescription.setText(itemDataNew.getDescription());
        holder.txtCreatesDate.setText(itemDataNew.getCreatedDate());

        Glide.with(holder.itemView.getContext())
                .load(itemDataNew.getLinkImage())
                .error(R.drawable.placeholder)
                .centerCrop()
                .into(holder.imgNew);
    }

    @Override
    public int getItemCount() {
        return topDataNews.size();
    }

    class TopNewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_new)
        ImageView imgNew;
        @BindView(R.id.txt_title_preview)
        TextView txtTitlePreview;
        @BindView(R.id.txt_description)
        TextView txtDescription;
        @BindView(R.id.txt_created_date)
        TextView txtCreatesDate;
        @BindView(R.id.btn_add_to_bookmark)
        ImageButton btnAddToBookmark;

        public TopNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                    itemClickListener.onItemClick(topDataNews.get(i), imgNew, i);
                }
            });

            btnAddToBookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                    ItemDataNew itemDataNew = topDataNews.get(i);
                    DatabaseHelper.getInMemoryDatabase(itemView.getContext()).getItemDataNewsDAO().addNew(itemDataNew);
                    EventBus.getDefault().post(new SaveNewEvent(itemDataNew));
                }
            });
        }
    }

    public interface ItemClickListener {
        void onItemClick(ItemDataNew itemDataNew, View view, int position);

        void onLongClickItem(ItemDataNew itemDataNew);

    }
}
