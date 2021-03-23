package com.trantan.newspagesmanagerment.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.trantan.newspagesmanagerment.fragments.NewsFragment;

public class TabNewPageAdapter extends FragmentPagerAdapter {
    public TabNewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public TabNewPageAdapter(FragmentManager childFragmentManager) {
        super(childFragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        NewsFragment content = new NewsFragment();

        switch (position){
            case 0:
                content.setLink("https://www.24h.com.vn/tin-tuc-trong-ngay-c46.html");
                break;
            case 1:
                content.setLink("https://www.24h.com.vn/tin-tuc-quoc-te-c415.html");
                break;
            case 2:
                content.setLink("https://www.24h.com.vn/bong-da-c48.html");
                break;
            case 3:
                content.setLink("https://www.24h.com.vn/kinh-doanh-c161.html");
                break;
            case 4:
                content.setLink("https://www.24h.com.vn/the-thao-c101.html");
                break;
            case 5:
                content.setLink("https://www.24h.com.vn/cong-nghe-thong-tin-c55.html");
                break;
            case 6:
                content.setLink("https://www.24h.com.vn/giai-tri-c731.html");
                break;
        }

        return content;
    }

    @Override
    public int getCount() {
        return 7;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Tin tức trong ngày";
            case 1:
                return "Tin tức quốc tế";
            case 2:
                return "Bóng đá";
            case 3:
                return "Kinh doanh";
            case 4:
                return "Thể thao";
            case 5:
                return "Công nghệ";
        }
        return "Giải trí";
    }
}
