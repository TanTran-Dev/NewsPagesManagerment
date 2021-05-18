package com.trantan.newspagesmanagerment.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trantan.newspagesmanagerment.Constants;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.database.DatabaseHelper;
import com.trantan.newspagesmanagerment.event_bus.SaveNewEvent;
import com.trantan.newspagesmanagerment.model.ItemDataNew;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.imgNews)
    ImageView imgNews;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title_preview)
    TextView txtNewTitle;
    @BindView(R.id.ln_content)
    LinearLayout lnContent;
    @BindView(R.id.btn_add_to_bookmark)
    FloatingActionButton btnAddToBookmark;
    @BindView(R.id.btn_share)
    FloatingActionButton btnShare;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;

    private String html;
    private ItemDataNew itemDataNew;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("");
        }

        itemDataNew = (ItemDataNew) getIntent().getSerializableExtra(Constants.KEY_ITEM_NEW);
        Glide.with(this).load(itemDataNew.getLinkImage())
                .error(R.drawable.placeholder)
                .into(imgNews);
        txtNewTitle.setText(itemDataNew.getTitle());
        html = itemDataNew.getLinkDetail();
        imgBack.setOnClickListener(this);
        btnAddToBookmark.setOnClickListener(this);
        btnShare.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_move_to_top: {
                scrollView.fullScroll(NestedScrollView.FOCUS_UP);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back: {
                supportFinishAfterTransition();
            }
            break;

            case R.id.btn_add_to_bookmark: {
                DatabaseHelper.getInMemoryDatabase(this).getItemDataNewsDAO().addNew(itemDataNew);
                EventBus.getDefault().post(new SaveNewEvent(itemDataNew));
            }
            break;

            case R.id.btn_share: {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, html);
                intent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(intent, null);
                startActivity(shareIntent);
            }
            break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }
}
