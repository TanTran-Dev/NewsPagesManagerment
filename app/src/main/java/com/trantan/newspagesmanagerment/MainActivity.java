package com.trantan.newspagesmanagerment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.trantan.newspagesmanagerment.fragments.FragmentPage;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ImageView imgMenu;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setEvents();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new FragmentPage())
                .commit();       //Quản lý các fragment
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        imgMenu = findViewById(R.id.imgMenu);
        navView = findViewById(R.id.nav_view);
    }

    private void setEvents() {
        imgMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgMenu:
                mDrawerLayout.openDrawer(navView);
                break;
        }
    }
}
