package com.trantan.newspagesmanagerment.view.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trantan.newspagesmanagerment.Constants;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.base.view.activity.BaseActivity;
import com.trantan.newspagesmanagerment.constants.ContentType;
import com.trantan.newspagesmanagerment.database.DatabaseHelper;
import com.trantan.newspagesmanagerment.event_bus.SaveNewEvent;
import com.trantan.newspagesmanagerment.model.ItemDataNew;
import com.trantan.newspagesmanagerment.model.response.Post;
import com.trantan.newspagesmanagerment.model.response.PostDetail;
import com.trantan.newspagesmanagerment.presenter.post_detail.PostDetailPresenterImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity<PostDetailPresenterImpl> implements PostDetailView, View.OnClickListener {
    @BindView(R.id.imgNews)
    ImageView imgNews;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title_preview)
    TextView txtNewTitle;
    @BindView(R.id.txt_description)
    TextView txtDesc;
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
    private String postID;
    private String titlePost;
    private String description;
    private String thumbnailUrl;

    @Override
    protected int getLayoutIntResource() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState) {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        postID = getIntent().getStringExtra(Constants.KEY_POST_ID);
        titlePost = getIntent().getStringExtra(Constants.KEY_TITLE_POST);
        description = getIntent().getStringExtra(Constants.KEY_DESCRIPTION);
        thumbnailUrl = getIntent().getStringExtra(Constants.KEY_THUMBNAIL);
//        html = post.getLinkDetail();

        imgBack.setOnClickListener(this);
        btnAddToBookmark.setOnClickListener(this);
        btnShare.setOnClickListener(this);

        getPresenter().setPostID(postID);
        getPresenter().refreshDataDetail();
    }

    @Override
    protected PostDetailPresenterImpl initPresenter() {
        return new PostDetailPresenterImpl(this, this);
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

//            case R.id.btn_add_to_bookmark: {
//                DatabaseHelper.getInMemoryDatabase(this).getItemDataNewsDAO().addNew(post);
//                EventBus.getDefault().post(new SaveNewEvent(post));
//            }
//            break;

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

    @Override
    public void refreshPostDetails(List<PostDetail> postDetails) {
        Glide.with(this).load(this.thumbnailUrl)
                .error(R.drawable.placeholder)
                .into(imgNews);
        txtNewTitle.setText(this.titlePost);
        txtDesc.setText(this.description);

        for (PostDetail postDetail : postDetails) {
            if (postDetail.getContentType().equals(ContentType.TEXT)) {
                TextView textView = new TextView(this);
                textView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                textView.setTextSize(16);
                Typeface typeface = ResourcesCompat.getFont(this, R.font.lora_medium);

                textView.setTypeface(typeface);
                lnContent.addView(textView);
                textView.setText(postDetail.getContent());
            } else if (postDetail.getContentType().equals(ContentType.IMAGE)) {
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                lnContent.addView(imageView);
                Glide.with(this).load(postDetail.getContent())
                        .into(imageView);

            }
        }
    }
}
