package com.trantan.newspagesmanagerment.fragments;

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
import com.trantan.newspagesmanagerment.activities.DetailActivity;
import com.trantan.newspagesmanagerment.adapter.BookmarkAdapter;
import com.trantan.newspagesmanagerment.adapter.SearchResultAdapter;
import com.trantan.newspagesmanagerment.database.DatabaseHelper;
import com.trantan.newspagesmanagerment.event_bus.SaveNewEvent;
import com.trantan.newspagesmanagerment.model.ItemDataNew;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarkFragment extends Fragment implements BookmarkAdapter.ItemClickListener {
    @BindView(R.id.rcl_normal)
    RecyclerView rclView;

    private BookmarkAdapter bookmarkAdapter;
    private List<ItemDataNew> itemDataNews;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        ButterKnife.bind(this,view);
        configRecyclerview();
        return view;
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
            bookmarkAdapter.addItem(saveNewEvent.getItemDataNew());
            bookmarkAdapter.notifyDataSetChanged();

           Toast.makeText(getContext(), "Added", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(ItemDataNew itemDataNew, View view, int position) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Constants.KEY_ITEM_NEW, itemDataNew);

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(),view.findViewById(R.id.img_new), "imgNews");
        startActivity(intent, optionsCompat.toBundle());
    }

    @Override
    public void onLongClickItem(ItemDataNew itemDataNew) {

    }
}
