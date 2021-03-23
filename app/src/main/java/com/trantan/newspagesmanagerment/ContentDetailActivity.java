package com.trantan.newspagesmanagerment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ContentDetailActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);

        webView = findViewById(R.id.webview);
        Intent intent = getIntent();
        String linkDetail = intent.getStringExtra(Constants.KEY_URL_DETAIL);
        webView.loadUrl(linkDetail);
        webView.setWebViewClient(new WebViewClient());
    }
}
