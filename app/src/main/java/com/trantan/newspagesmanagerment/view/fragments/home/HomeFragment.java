package com.trantan.newspagesmanagerment.view.fragments.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.BottomPagerAdapter;
import com.trantan.newspagesmanagerment.adapter.TabNewPageAdapter;
import com.trantan.newspagesmanagerment.base.view.fragment.BaseFragment;
import com.trantan.newspagesmanagerment.event_bus.EventChangeWebsite;
import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.presenter.home.HomePresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment<HomePresenterImpl> implements HomeView {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    protected int getLayoutIntResource() {
        return R.layout.fragment_home;
    }

    @Override
    public String getName() {
        return BottomPagerAdapter.HOME_FRAGMENT;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState, View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        getPresenter().refreshCategories();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected HomePresenterImpl initPresenter() {
        return new HomePresenterImpl(getContext(), this);
    }

    @Override
    public void refreshListCategories(List<Category> categories) {
        TabNewPageAdapter pageAdapter = new TabNewPageAdapter(getChildFragmentManager(), categories);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeWebsiteEvent(EventChangeWebsite eventChangeWebsite){
        int websiteID = eventChangeWebsite.getWebsite().getId();

        switch (websiteID){
            case 1: {
                getPresenter().refreshCategories();
            }
            break;

            case 2: {

            }
            break;
        }
    }
}
