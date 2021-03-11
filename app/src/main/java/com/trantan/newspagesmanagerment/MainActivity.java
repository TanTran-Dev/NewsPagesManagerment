package com.trantan.newspagesmanagerment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.trantan.newspagesmanagerment.adapter.BottomPagerAdapter;
import com.trantan.newspagesmanagerment.custom_views.BottomNavigation;
import com.trantan.newspagesmanagerment.fragments.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        BottomNavigation.NavItemSelectedListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.btn_menu)
    ImageView btnMenu;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rootBottomNav)
    BottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setEvents();

        FragmentManager fragmentManager = getSupportFragmentManager();
        BottomPagerAdapter pagerAdapter = new BottomPagerAdapter(fragmentManager, 4);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(4);

        bottomNavigation.setNavItemSelectedListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    private void setEvents() {
        btnMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_menu:
                mDrawerLayout.openDrawer(navView);
                break;
        }
    }

    @Override
    public void setSelectedListener(BottomNavigation.NAV_ITEM nav_item) {
        viewPager.setCurrentItem(nav_item.getPosition());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigation.handleItemSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
