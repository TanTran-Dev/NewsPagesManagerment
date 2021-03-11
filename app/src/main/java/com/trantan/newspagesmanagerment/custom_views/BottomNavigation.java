package com.trantan.newspagesmanagerment.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.trantan.newspagesmanagerment.Extensions.ImageExtension;
import com.trantan.newspagesmanagerment.Extensions.TextExtension;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.databinding.BottomNavigationTemplateBinding;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigation extends LinearLayout implements View.OnClickListener {
    private Context context;

    private List<NavItem> navItemList = new ArrayList<>();
    private static final int selectedColor = R.color.nav_selected;
    private static final int normalColor = R.color.nav_normal;

    private NavItemSelectedListener navItemSelectedListener;

    int currentSelectedPosition = 0;

    public BottomNavigation(Context context) {
        super(context);
        init(context);
    }

    public BottomNavigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setNavItemSelectedListener(NavItemSelectedListener navItemSelectedListener) {
        this.navItemSelectedListener = navItemSelectedListener;
    }

    private void init(Context context) {
        this.context = context;

        final BottomNavigationTemplateBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.bottom_navigation_template, this, true
        );

        onViewCreated(binding);
        setItemListener(binding);
    }

    private void onViewCreated(BottomNavigationTemplateBinding binding) {
        final NavItem item1 = new NavItem(binding.btnNav1, binding.img1, binding.tvTitle1);
        final NavItem item2 = new NavItem(binding.btnNav2, binding.img2, binding.tvTitle2);
        final NavItem item3 = new NavItem(binding.btnNav3, binding.img3, binding.tvTitle3);
        final NavItem item4 = new NavItem(binding.btnNav4, binding.img4, binding.tvTitle4);

        navItemList.add(item1);
        navItemList.add(item2);
        navItemList.add(item3);
        navItemList.add(item4);
    }

    private void setItemListener(BottomNavigationTemplateBinding binding) {
        binding.btnNav1.setOnClickListener(this);
        binding.btnNav2.setOnClickListener(this);
        binding.btnNav3.setOnClickListener(this);
        binding.btnNav4.setOnClickListener(this);
    }

    public void handleItemSelected(int position) {
        for (int i = 0; i < navItemList.size(); i++) {
            boolean isSelected;

            if (i == position){
                isSelected = true;
            }else {
                isSelected = false;
            }

            final int color = isSelected ? selectedColor : normalColor;
            ImageExtension.changeTintColor(context, navItemList.get(i).getImage(), color);
            TextExtension.changeColor(context, navItemList.get(i).getTextView(), color);
            TextExtension.showTitle(navItemList.get(i).getTextView(), isSelected);
        }
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        handleItemSelected(id);
        if (navItemSelectedListener != null) {
            switch (id) {
                case R.id.btnNav1:
                    navItemSelectedListener.setSelectedListener(NAV_ITEM.item1);
                    break;
                case R.id.btnNav2:
                    navItemSelectedListener.setSelectedListener(NAV_ITEM.item2);
                    break;
                case R.id.btnNav3:
                    navItemSelectedListener.setSelectedListener(NAV_ITEM.item3);
                    break;
                case R.id.btnNav4:
                    navItemSelectedListener.setSelectedListener(NAV_ITEM.item4);
                    break;
            }
        }
    }


    private class NavItem {
        private LinearLayout linearLayout;
        private ImageView image;
        private TextView textView;

        public NavItem(LinearLayout linearLayout, ImageView image, TextView textView) {
            this.linearLayout = linearLayout;
            this.image = image;
            this.textView = textView;
        }

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public interface NavItemSelectedListener {
        void setSelectedListener(NAV_ITEM nav_item);
    }

    public enum NAV_ITEM {
        item1("Home", 0),
        item2("Search", 1),
        item3("Bookmark", 2),
        item4("Notification", 3);

        private String label;
        private int position;

        NAV_ITEM(String label, int position) {
            this.label = label;
            this.position = position;
        }

        public int getPosition() {
            return position;
        }

        public String getLabel() {
            return label;
        }
    }
}
