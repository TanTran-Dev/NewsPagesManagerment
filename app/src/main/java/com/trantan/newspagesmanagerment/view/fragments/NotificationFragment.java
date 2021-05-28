package com.trantan.newspagesmanagerment.view.fragments;

import android.os.Bundle;
import android.view.View;

import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.BottomPagerAdapter;
import com.trantan.newspagesmanagerment.base.presenter.BasePresenter;
import com.trantan.newspagesmanagerment.base.view.fragment.BaseFragment;

public
class NotificationFragment extends BaseFragment {
    @Override
    protected int getLayoutIntResource() {
        return R.layout.fragment_notification;
    }

    @Override
    public String getName() {
        return BottomPagerAdapter.NOTIFICATION_FRAGMENT;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState, View rootView) {

    }

    @Override
    protected void initData(Bundle saveInstanceState) {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
