package com.trantan.newspagesmanagerment.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.trantan.newspagesmanagerment.Constants;
import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.view.fragments.PostFragment;

import java.util.ArrayList;
import java.util.List;

public class TabNewPageAdapter extends FragmentPagerAdapter {
    private List<PostFragment> fragments;
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

    private PostFragment createNewFragment(Category category) {
        PostFragment result = new PostFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.KEY_CATEGORY_ID, category.getId());
        result.setArguments(args);
        return result;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
//        NewsFragment content = new NewsFragment();

//        switch (position) {
//            case 0:
//                content.setLink("https://www.24h.com.vn/tin-tuc-trong-ngay-c46.html");
//                break;
//            case 1:
//                content.setLink("https://www.24h.com.vn/tin-tuc-quoc-te-c415.html");
//                break;
//            case 2:
//                content.setLink("https://www.24h.com.vn/bong-da-c48.html");
//                break;
//            case 3:
//                content.setLink("https://www.24h.com.vn/kinh-doanh-c161.html");
//                break;
//            case 4:
//                content.setLink("https://www.24h.com.vn/the-thao-c101.html");
//                break;
//            case 5:
//                content.setLink("https://www.24h.com.vn/cong-nghe-thong-tin-c55.html");
//                break;
//            case 6:
//                content.setLink("https://www.24h.com.vn/giai-tri-c731.html");
//                break;
//        }

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

//        switch (position) {
//            case 0:
//                return "Tin tức trong ngày";
//            case 1:
//                return "Tin tức quốc tế";
//            case 2:
//                return "Bóng đá";
//            case 3:
//                return "Kinh doanh";
//            case 4:
//                return "Thể thao";
//            case 5:
//                return "Công nghệ";
//        }
        return categories.get(position).getName();
    }
}
