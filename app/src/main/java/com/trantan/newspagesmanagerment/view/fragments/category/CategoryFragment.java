package com.trantan.newspagesmanagerment.view.fragments.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.TabNewPageAdapter;
import com.trantan.newspagesmanagerment.base.view.BaseFragment;
import com.trantan.newspagesmanagerment.event_bus.SelectedTabEvent;
import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.presenter.category.CategoryPresenterImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends BaseFragment<CategoryPresenterImpl> implements CategoryView{
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected int getLayoutIntResource() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState, View rootView) {
        ButterKnife.bind(this, rootView);

    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String title = tab.getText().toString();
                EventBus.getDefault().post(new SelectedTabEvent(title));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getPresenter().refreshCategories();
    }

    @Override
    protected CategoryPresenterImpl initPresenter() {
        return new CategoryPresenterImpl(getContext(), this);
    }


    @Override
    public void refreshListCategories(List<Category> categories) {
        TabNewPageAdapter pageAdapter = new TabNewPageAdapter(getChildFragmentManager(), categories);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
