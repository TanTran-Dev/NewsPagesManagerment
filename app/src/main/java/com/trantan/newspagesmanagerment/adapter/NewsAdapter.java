package com.trantan.newspagesmanagerment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trantan.newspagesmanagerment.ItemClickListener;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.model.ItemDataNews;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private List<ItemDataNews> dataNews;

    private ItemClickListener itemClickListener;
    public NewsAdapter(List<ItemDataNews> dataNews, ItemClickListener listener) {
        this.dataNews = dataNews;
        this.itemClickListener = listener;
    }

    public List<ItemDataNews> getDataNews() {
        return dataNews;
    }

    public void setDataNews(List<ItemDataNews> dataNews) {
        this.dataNews = dataNews;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_vertical, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsHolder holder, int position) {
        final ItemDataNews itemDataNews = dataNews.get(position);
        holder.txtTitle.setText(itemDataNews.getTittle());
        holder.txtContent.setText(itemDataNews.getContent());

        Glide.with(holder.itemView.getContext())
                .load(itemDataNews.getLinkImage())
//                .error(R.drawable.ic_launcher_background)
                .into(holder.imgNews);



    }

    @Override
    public int getItemCount() {
        if (dataNews == null) {
            return 0;
        }
        return dataNews.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder  {
        ImageView imgNews;
        TextView txtTitle;
        TextView txtContent;



        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            imgNews = itemView.findViewById(R.id.imgNews);
            txtTitle = itemView.findViewById(R.id.txtNewsTitle);
            txtContent = itemView.findViewById(R.id.txtContent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                    itemClickListener.onItemClick(dataNews.get(i));
                }
            });
        }
    }
}
