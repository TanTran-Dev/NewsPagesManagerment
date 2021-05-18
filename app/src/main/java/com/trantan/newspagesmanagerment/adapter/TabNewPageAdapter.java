package com.trantan.newspagesmanagerment.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.trantan.newspagesmanagerment.Constants;
import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.view.fragments.home.posts.PostsFragment;

import java.util.ArrayList;
import java.util.List;

public class TabNewPageAdapter extends FragmentPagerAdapter {
    private List<PostsFragment> fragments;
    private List<Category> categories;

    public TabNewPageAdapter(@NonNull FragmentManager fm, List<Category> categories) {
        super(fm);
        this.fragments = new ArrayList<>();
        this.categories = categories;

        for (Category category : categories) {
            this.fragments.add(createNewFragment(category));
        }
    }

    public TabNewPageAdapter(FragmentManager childFragmentManager) {
        super(childFragmentManager);
    }

    private PostsFragment createNewFragment(Category category) {
        PostsFragment result = new PostsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.KEY_CATEGORY_ID, category.getId());
        result.setArguments(args);
        return result;
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


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getName();
    }
}
