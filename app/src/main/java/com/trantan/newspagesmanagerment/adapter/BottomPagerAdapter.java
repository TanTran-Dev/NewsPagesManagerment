package com.trantan.newspagesmanagerment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.recycleview.base.HomeFragmentPagerAdapter;
import com.trantan.newspagesmanagerment.base.view.fragment.BaseFragment;
import com.trantan.newspagesmanagerment.view.fragments.NotificationFragment;
import com.trantan.newspagesmanagerment.view.fragments.bookmark.BookmarkFragment;
import com.trantan.newspagesmanagerment.view.fragments.home.HomeFragment;
import com.trantan.newspagesmanagerment.view.fragments.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomPagerAdapter extends HomeFragmentPagerAdapter {
    public static final String HOME_FRAGMENT = "HOME_FRAGMENT";
    public static final String SEARCH_FRAGMENT = "SEARCH_FRAGMENT";
    public static final String BOOKMARK_FRAGMENT = "BOOKMARK_FRAGMENT";
    public static final String NOTIFICATION_FRAGMENT = "NOTIFICATION_FRAGMENT";

    public BottomPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public List<BaseFragment> initFragments() {
        List<BaseFragment> fragments = new ArrayList<>(4);
        fragments.add(new HomeFragment());
        fragments.add(new SearchFragment());
        fragments.add(new BookmarkFragment());
        fragments.add(new NotificationFragment());
        return fragments;
    }

    @Override
    public  int getItemID(int position) {
        String fragmentName = getFragmentName(position);
        if (fragmentName == null) {
            return -1;
        }

        switch (fragmentName) {
            case HOME_FRAGMENT: {
                return R.id.btnNav1;
            }
            case SEARCH_FRAGMENT: {
                return R.id.btnNav2;
            }
            case BOOKMARK_FRAGMENT: {
                return R.id.btnNav3;
            }

            case NOTIFICATION_FRAGMENT: {
                return R.id.btnNav4;
            }
            default: {
                return -1;
            }
        }
    }
}
