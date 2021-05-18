package com.trantan.newspagesmanagerment.view.fragments.home;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.TabNewPageAdapter;
import com.trantan.newspagesmanagerment.base.view.BaseFragment;
import com.trantan.newspagesmanagerment.event_bus.SelectedTabEvent;
import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.presenter.home.HomePresenterImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment<HomePresenterImpl> implements HomeView {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<Category> categories;
    @Override
    protected int getLayoutIntResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState, View rootView) {
        ButterKnife.bind(this, rootView);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Category category = categories.get(tab.getPosition());
                EventBus.getDefault().post(new SelectedTabEvent(category));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        getPresenter().refreshCategories();
    }

    @Override
    protected HomePresenterImpl initPresenter() {
        return new HomePresenterImpl(getContext(), this);
    }


    @Override
    public void refreshListCategories(List<Category> categories) {
        this.categories = categories;

        TabNewPageAdapter pageAdapter = new TabNewPageAdapter(getChildFragmentManager(), categories);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
