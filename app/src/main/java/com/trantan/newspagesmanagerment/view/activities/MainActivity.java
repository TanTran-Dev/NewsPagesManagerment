package com.trantan.newspagesmanagerment.view.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.BottomPagerAdapter;
import com.trantan.newspagesmanagerment.adapter.recycleview.DrawerItemCustomAdapter;
import com.trantan.newspagesmanagerment.base.view.activity.BaseActivity;
import com.trantan.newspagesmanagerment.model.response.Website;
import com.trantan.newspagesmanagerment.presenter.main.MainPresenter;
import com.trantan.newspagesmanagerment.presenter.main.MainPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
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

        FragmentManager fragmentManager = getSupportFragmentManager();
        BottomPagerAdapter pagerAdapter = new BottomPagerAdapter(fragmentManager, 4);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(4);

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        drawerItemCustomAdapter = new DrawerItemCustomAdapter(this);
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
            switch (item.getItemId()) {
                case R.id.nav_home: {
                    toolbar.setTitle("Home");
                    appBarLayout.setVisibility(View.VISIBLE);
                    viewPager.setCurrentItem(BottomPagerAdapter.HOME_FRAGMENT_POSITION);
                }
                return true;

                case R.id.nav_search: {
                    toolbar.setTitle("Search");
                    appBarLayout.setVisibility(View.GONE);
                    viewPager.setCurrentItem(BottomPagerAdapter.SEARCH_FRAGMENT_POSITION);
                }
                return true;

                case R.id.nav_bookmark: {
                    toolbar.setTitle("Bookmark");
                    appBarLayout.setVisibility(View.VISIBLE);
                    viewPager.setCurrentItem(BottomPagerAdapter.BOOKMARK_FRAGMENT_POSITION);
                }
                return true;

                case R.id.nav_notification: {
                    toolbar.setTitle("Notification");
                    appBarLayout.setVisibility(View.VISIBLE);
                    viewPager.setCurrentItem(BottomPagerAdapter.NOTIFICATION_FRAGMENT_POSITION);
                }
                return true;
            }
            return false;
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
}
