package com.trantan.newspagesmanagerment;

import android.view.View;

import com.trantan.newspagesmanagerment.model.ItemDataNews;

public interface ItemClickListener {

        void onItemClick(ItemDataNews itemDataNews);
        void onLongClickItem(ItemDataNews itemDataNews);

}
