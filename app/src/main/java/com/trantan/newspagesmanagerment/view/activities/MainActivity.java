package com.trantan.newspagesmanagerment.view.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.BottomPagerAdapter;
import com.trantan.newspagesmanagerment.adapter.recycleview.DrawerItemCustomAdapter;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.RecyclerViewAdapter;
import com.trantan.newspagesmanagerment.base.view.activity.BaseActivity;
import com.trantan.newspagesmanagerment.event_bus.EventChangeWebsite;
import com.trantan.newspagesmanagerment.model.response.Website;
import com.trantan.newspagesmanagerment.presenter.main.MainPresenter;
import com.trantan.newspagesmanagerment.presenter.main.MainPresenterImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity<MainPresenter> implements MainView, RecyclerViewAdapter.OnItemClickListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.rootBottomNav)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.rclMenu)
    RecyclerView rclMenu;

    private DrawerItemCustomAdapter drawerItemCustomAdapter;
    private BottomPagerAdapter pagerAdapter;

    @Override
    protected int getLayoutIntResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState) {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("News");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

       initViewPager();

    }

    private void initViewPager(){
        pagerAdapter = new BottomPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(4);

        initBottomNavigation();
    }

    private void initBottomNavigation(){
        bottomNavigation.getMenu().clear();
        bottomNavigation.inflateMenu(R.menu.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        drawerItemCustomAdapter = new DrawerItemCustomAdapter(this);
        drawerItemCustomAdapter.addOnItemClickListener(this);
        rclMenu.setLayoutManager(new LinearLayoutManager(this));
        rclMenu.setAdapter(drawerItemCustomAdapter);

        getPresenter().refreshWebsites();
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenterImpl(this, this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String fragmentName;
            String toolbarTitle;
            switch (item.getItemId()) {
                case R.id.nav_home: {
                    appBarLayout.setVisibility(View.VISIBLE);
                    toolbarTitle = "Home";
                    fragmentName = BottomPagerAdapter.HOME_FRAGMENT;
                }
                break;

                case R.id.nav_search: {
                    appBarLayout.setVisibility(View.GONE);
                    toolbarTitle = "Search";
                    fragmentName = BottomPagerAdapter.SEARCH_FRAGMENT;
                }
                break;

                case R.id.nav_bookmark: {
                    appBarLayout.setVisibility(View.VISIBLE);
                    toolbarTitle = "Bookmark";
                    fragmentName = BottomPagerAdapter.BOOKMARK_FRAGMENT;
                }
                break;

                case R.id.nav_notification: {
                    appBarLayout.setVisibility(View.VISIBLE);
                    toolbarTitle = "Notification";
                    fragmentName = BottomPagerAdapter.NOTIFICATION_FRAGMENT;
                }
                break;

                default: {
                    return false;
                }
            }

            toolbar.setTitle(toolbarTitle);
            int fragmentPosition = pagerAdapter.getFragmentPosition(fragmentName);
            if (fragmentPosition >= 0) {
                viewPager.setCurrentItem(fragmentPosition);
            }

            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                mDrawerLayout.openDrawer(navView);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshWebsites(List<Website> websites) {
        drawerItemCustomAdapter.refresh(websites);
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder,
                            int viewType, int position) {
        Website website = drawerItemCustomAdapter.getItem(position, Website.class);
        EventBus.getDefault().post(new EventChangeWebsite(website));
    }
}
