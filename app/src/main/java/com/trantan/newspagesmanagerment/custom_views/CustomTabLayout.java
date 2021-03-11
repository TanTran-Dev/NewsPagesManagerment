package com.trantan.newspagesmanagerment.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public
class CustomTabLayout extends TabLayout {

    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void setupWithViewPager(ViewPager viewPager)
    {
        try {
            super.setupWithViewPager(viewPager);

            //App.showLog("====cm here==");
            Context context = getContext();
            Typeface mTypeface = Typeface.createFromAsset(context
                    .getAssets(),  "font/poppins_medium.ttf" );
            if (mTypeface != null) {
                this.removeAllTabs();

                ViewGroup slidingTabStrip = (ViewGroup) getChildAt(0);

                PagerAdapter adapter = viewPager.getAdapter();

                for (int i = 0, count = adapter.getCount(); i < count; i++) {
                    Tab tab = this.newTab();
                    this.addTab(tab.setText(adapter.getPageTitle(i)));
                    //  AppCompatTextView view = (AppCompatTextView) ((ViewGroup) slidingTabStrip.getChildAt(i)).getChildAt(1);
                    TextView view = (TextView) ((ViewGroup) slidingTabStrip.getChildAt(i)).getChildAt(1);
                    view.setTypeface(mTypeface);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.d("TAB", "setupWithViewPager: " + e.getMessage());
        }
    }
}
