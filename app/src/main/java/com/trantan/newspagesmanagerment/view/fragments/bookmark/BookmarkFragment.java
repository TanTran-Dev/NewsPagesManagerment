package com.trantan.newspagesmanagerment.view.fragments.bookmark;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trantan.newspagesmanagerment.Constants;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.BottomPagerAdapter;
import com.trantan.newspagesmanagerment.base.presenter.BasePresenter;
import com.trantan.newspagesmanagerment.base.view.fragment.BaseFragment;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.view.activities.DetailActivity;
import com.trantan.newspagesmanagerment.adapter.recycleview.BookmarkAdapter;
import com.trantan.newspagesmanagerment.database.DatabaseHelper;
import com.trantan.newspagesmanagerment.event_bus.SaveNewEvent;
import com.trantan.newspagesmanagerment.model.ItemDataNew;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarkFragment extends BaseFragment implements BookmarkAdapter.ItemClickListener {
    @BindView(R.id.rcl_normal)
    RecyclerView rclView;

    private BookmarkAdapter bookmarkAdapter;
    private List<Post> itemDataNews;

    @Override
    protected int getLayoutIntResource() {
        return R.layout.fragment_bookmark;
    }

    @Override
    public String getName() {
        return BottomPagerAdapter.BOOKMARK_FRAGMENT;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState, View rootView) {
        ButterKnife.bind(this,rootView);
        configRecyclerview();
    }

    @Override
    protected void initData(Bundle saveInstanceState) {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    private void configRecyclerview(){
        bookmarkAdapter = new BookmarkAdapter(this);

        bookmarkAdapter.setDataNews(DatabaseHelper.getInMemoryDatabase(getContext()).getItemDataNewsDAO().findAllItemDataNews());
        rclView.setLayoutManager(new LinearLayoutManager(getContext()));
        rclView.setAdapter(bookmarkAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSaveNewEvent(SaveNewEvent saveNewEvent){
//            bookmarkAdapter.addItem(saveNewEvent.getItemDataNew());
//            bookmarkAdapter.notifyDataSetChanged();
//
//           Toast.makeText(getContext(), "Added", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(ItemDataNew itemDataNew, View view, int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Constants.KEY_ITEM_POST, itemDataNew);

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(),view.findViewById(R.id.img_new), "imgNews");
        startActivity(intent, optionsCompat.toBundle());
    }

    @Override
    public void onLongClickItem(ItemDataNew itemDataNew) {

    }
}
