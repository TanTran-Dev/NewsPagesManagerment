package com.trantan.newspagesmanagerment.adapter.recycleview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.RecyclerViewAdapter;
import com.trantan.newspagesmanagerment.model.response.Website;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerItemCustomAdapter extends RecyclerViewAdapter {
    public DrawerItemCustomAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view = getInflater().inflate(R.layout.item_menu_drawer, parent, false);
        return new DrawerItemViewHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        Context context = getContext();
        Website website = getItem(position, Website.class);
        DrawerItemViewHolder viewHolder = (DrawerItemViewHolder) holder;

        Glide.with(context)
                .load(website.getLogoURL())
                .into(viewHolder.imgLogo);

        viewHolder.txtLabel.setText(website.getName());
    }

    class DrawerItemViewHolder extends NormalViewHolder{
        @BindView(R.id.imgLogoWeb)
        ImageView imgLogo;

        @BindView(R.id.txtLabel)
        TextView txtLabel;
        public DrawerItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
