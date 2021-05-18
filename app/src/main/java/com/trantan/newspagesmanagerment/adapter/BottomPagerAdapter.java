package com.trantan.newspagesmanagerment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.view.fragments.bookmark.BookmarkFragment;
import com.trantan.newspagesmanagerment.view.fragments.home.HomeFragment;
import com.trantan.newspagesmanagerment.view.fragments.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomPagerAdapter extends FragmentPagerAdapter {
    public static final int HOME_FRAGMENT_POSITION = 0;
    public static final int SEARCH_FRAGMENT_POSITION = 1;
    public static final int BOOKMARK_FRAGMENT_POSITION = 2;
    public static final int NOTIFICATION_FRAGMENT_POSITION = 3;
    private List<Fragment> fragments;

    public BottomPagerAdapter(@NonNull FragmentManager fm, int size) {
        super(fm);
        fragments = new ArrayList<>(size);
        fragments.add(new HomeFragment());
        fragments.add(new SearchFragment());
        fragments.add(new BookmarkFragment());
        fragments.add(new Fragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public static int getItemID(int id) {
        switch (id) {
            case HOME_FRAGMENT_POSITION:{
                return R.id.btnNav1;
            }
            case SEARCH_FRAGMENT_POSITION: {
                return R.id.btnNav2;
            }
            case BOOKMARK_FRAGMENT_POSITION: {
                return R.id.btnNav3;
            }

            case NOTIFICATION_FRAGMENT_POSITION: {
                return R.id.btnNav4;
            }
            default: {
                return -1;
            }
        }
    }
}
