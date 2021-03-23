package com.trantan.newspagesmanagerment.adapter;

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
import com.trantan.newspagesmanagerment.model.ItemDataNew;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.NewNormalHolder> implements Filterable {
    private List<ItemDataNew> dataNews;
    private List<ItemDataNew> dataFiltered;
    private ItemClickListener itemClickListener;

    public SearchResultAdapter(ItemClickListener listener) {
        this.dataNews = new ArrayList<>();
        this.dataFiltered = new ArrayList<>(dataNews);
        this.itemClickListener = listener;
    }

    public List<ItemDataNew> getDataNews() {
        return dataNews;
    }

    public void setDataNews(List<ItemDataNew> dataNews) {
        this.dataNews = dataNews;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void addItem(ItemDataNew itemDataNew) {
        this.dataNews.add(itemDataNew);
        this.dataFiltered.add(itemDataNew);
    }

    @NonNull
    @Override
    public NewNormalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_bookmark, parent, false);
        return new NewNormalHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final NewNormalHolder holder, int position) {
        final ItemDataNew itemDataNew = dataNews.get(position);
        holder.txtTitlePreview.setText(itemDataNew.getTitle());
        holder.txtDescription.setText(itemDataNew.getDescription());
        holder.txtCreatedDate.setText(itemDataNew.getCreatedDate());

        Glide.with(holder.itemView.getContext())
                .load(itemDataNew.getLinkImage())
                .error(R.drawable.placeholder)
                .centerCrop()
                .into(holder.imgNews);

    }

    @Override
    public int getItemCount() {
        if (dataNews == null) {
            return 0;
        } else {
            return dataNews.size();
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString();
            List<ItemDataNew> filteredList = new ArrayList<>();
            if (!charString.isEmpty()) {
                for (ItemDataNew row : dataFiltered) {
                    if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                        filteredList.add(row);
                    }
                }
            } else {
                filteredList.addAll(dataFiltered);
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataNews.clear();
            dataNews.addAll((Collection<? extends ItemDataNew>) results.values);
            notifyDataSetChanged();
        }
    };
    

    class NewNormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_new)
        ImageView imgNews;
        @BindView(R.id.txt_title_preview)
        TextView txtTitlePreview;
        @BindView(R.id.txt_description)
        TextView txtDescription;
        @BindView(R.id.txt_created_date)
        TextView txtCreatedDate;

        public NewNormalHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();
                itemClickListener.onItemClick(dataNews.get(i), imgNews, i);
            });
        }
    }

    public interface ItemClickListener {
        void onItemClick(ItemDataNew itemDataNew, View view, int position);

        void onLongClickItem(ItemDataNew itemDataNew);

    }
}
