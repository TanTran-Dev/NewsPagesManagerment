package com.trantan.newspagesmanagerment.adapter.recycleview.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.trantan.newspagesmanagerment.base.view.fragment.BaseFragment;

import java.util.List;

public abstract class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = initFragments();
    }

    public abstract List<BaseFragment> initFragments();

    public abstract int getItemID(int position);

    public void addFragment(int position, BaseFragment fragment) {
        fragments.add(position, fragment);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        int pos = POSITION_NONE;
        if (object instanceof BaseFragment) {
            pos = fragments.indexOf(object);
        }
        return pos;
    }

    @Override
    public long getItemId(int position) {
        return System.identityHashCode(fragments.get(position));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public String getFragmentName(int position) {
        if (position >= 0 && position < fragments.size()) {
            return fragments.get(position).getName();
        }
        return null;
    }

    public int getFragmentPosition(String name) {
        for (int i = 0; i < fragments.size(); i++) {
            if (name.equals(fragments.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }
}
