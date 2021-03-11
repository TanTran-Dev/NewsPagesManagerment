package com.trantan.newspagesmanagerment.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trantan.newspagesmanagerment.Extensions.TextExtension;
import com.trantan.newspagesmanagerment.Extensions.ViewExtension;
import com.trantan.newspagesmanagerment.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutCustom extends FrameLayout {
    private Context context;
    private RecyclerView recyclerView;

    public TabLayoutCustom(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public TabLayoutCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabLayoutCustom(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TabLayoutCustomAdapter adapter = new TabLayoutCustomAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        addView(recyclerView);
    }


     class TabLayoutCustomAdapter extends RecyclerView.Adapter<TabLayoutCustomAdapter.TabViewHolder> {
        private static final int lineNormal = R.color.line_tab_normal;
        private static final int lineSelected = R.color.line_tab_selected;

        private static final int textSelected = R.color.text_tab_selected;
        private static final int textNormal = R.color.text_tab_normal;

        private Context context;
        private List<Tab> tabs;

        public TabLayoutCustomAdapter(Context context) {
            this.context = context;
            this.tabs = new ArrayList<>();
            tabs.add(new Tab("Tin tức trong ngày", false));
            tabs.add(new Tab("Tin tức quốc tế", false));
            tabs.add(new Tab("Bóng đá", false));
            tabs.add(new Tab("Kinh doanh", false));
            tabs.add(new Tab("Thể thao", false));
            tabs.add(new Tab("Công nghệ", false));
        }

        @NonNull
        @Override
        public TabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_tab_layout, parent, false);
            return new TabViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TabViewHolder holder, int position) {
            Tab tab = tabs.get(position);
            holder.txtTabTitle.setText(tab.getTitle());

            int colorIndicator = tab.isSelected ? lineSelected : lineNormal;
            int colorTitle = tab.isSelected ? textSelected: textNormal;

            ViewExtension.changeColor(context, holder.tabIndicator, colorIndicator);
            TextExtension.changeColor(context, holder.txtTabTitle, colorTitle);
        }

        @Override
        public int getItemCount() {
            return tabs.size();
        }

        class TabViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.txt_title)
            TextView txtTabTitle;
            @BindView(R.id.tab_indicator)
            View tabIndicator;

            public TabViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        private class Tab{
            private String title;
            private boolean isSelected;

            public Tab() {
            }

            public Tab(String title, boolean isSelected) {
                this.title = title;
                this.isSelected = isSelected;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }
        }
    }

}
