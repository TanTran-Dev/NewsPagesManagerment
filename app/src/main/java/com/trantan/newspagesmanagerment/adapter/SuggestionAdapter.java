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
import com.trantan.newspagesmanagerment.model.ItemDataNewSuggestion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.NewSuggestionViewHolder> implements Filterable {
    private List<ItemDataNewSuggestion> list;
    private List<ItemDataNewSuggestion> suggestionListFiltered;
    private ItemClickListener itemClickListener;

    public SuggestionAdapter(ItemClickListener itemClickListener) {
        this.list = new ArrayList<>();
        this.suggestionListFiltered = new ArrayList<>(list);
        this.itemClickListener = itemClickListener;
    }

    public void addItem(ItemDataNewSuggestion item) {
        this.list.add(item);
        this.suggestionListFiltered.add(item);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public NewSuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_news_suggestion, parent, false);
        return new NewSuggestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewSuggestionViewHolder holder, int position) {
        final ItemDataNewSuggestion itemDataNewSuggestion = list.get(position);
        holder.txtTitlePreview.setText(itemDataNewSuggestion.getTitle());
        Glide.with(holder.itemView.getContext())
                .load(itemDataNewSuggestion.getLinkImage())
                .error(R.drawable.placeholder)
                .centerCrop()
                .into(holder.imgNew);
    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;
        } else {
            return list.size();
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        // run on background Thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString();
            List<ItemDataNewSuggestion> filteredList = new ArrayList<>();
            if (!charString.isEmpty()){
                for (ItemDataNewSuggestion row : suggestionListFiltered){
                    if (row.getTitle().toLowerCase().contains(charString.toLowerCase())){
                        filteredList.add(row);
                    }
                }
            } else {
                filteredList.addAll(suggestionListFiltered);
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        // run on a UI thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends ItemDataNewSuggestion>) results.values);
            notifyDataSetChanged();
        }
    };


    class NewSuggestionViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_new)
        ImageView imgNew;
        @BindView(R.id.txt_title_preview)
        TextView txtTitlePreview;

        public NewSuggestionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                int i = getAdapterPosition();
                itemClickListener.onItemClick(list.get(i), imgNew, i);
            });
        }
    }

    public interface ItemClickListener {
        void onItemClick(ItemDataNewSuggestion itemDataNewSuggestion, View view, int position);
    }
}
