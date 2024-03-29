package com.trantan.newspagesmanagerment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.TabNewPageAdapter;
import com.trantan.newspagesmanagerment.event_bus.SelectedTabEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        viewPager.setAdapter(new TabNewPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setListener(new ICTabs.ICTabSelectedListener() {
//            @Override
//            public void ICTabListener(ICTabs.TAB_IC tab_ic, int position) {
//                viewPager.setCurrentItem(position);
//            }
//        });
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

        return view;
    }
}
