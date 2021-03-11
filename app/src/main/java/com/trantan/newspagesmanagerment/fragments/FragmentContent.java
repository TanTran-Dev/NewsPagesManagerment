package com.trantan.newspagesmanagerment.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.trantan.newspagesmanagerment.ContentDetailActivity;
import com.trantan.newspagesmanagerment.ItemClickListener;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.adapter.NewsAdapter;
import com.trantan.newspagesmanagerment.model.ItemDataNews;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FragmentContent extends Fragment implements ItemClickListener{
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<ItemDataNews> listDataNews;
    private String link;

    private ItemClickListener itemClickListener;
    private FragmentItemNewsContent fragmentItemNewsContent;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public FragmentContent() {
    }

    public static FragmentContent newInstance(){
        Bundle args = new Bundle();
        FragmentContent content = new FragmentContent();
        content.setArguments(args);

        return content;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_content,
                container,
                false
        );



        recyclerView = view.findViewById(R.id.rclView);
        adapter = new NewsAdapter(listDataNews, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        if (link.equals("https://www.24h.com.vn")) {
            crawlData();
        } else {
            crawlSinglePage();
        }

        return view;
    }



    private void crawlData() {
        listDataNews = new ArrayList<>();
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... values) {

                try {
                    Document doc = Jsoup.connect(values[0]).get();
                    Elements elements = doc.select("div.bxDoC");

                    for (Element element : elements) {
                        String title = element.select("a").first().text();
                        String linkImage = element.select("img").attr("data-original");
                        String content = element.select("span.nwsSpSpc").text();
                        String linkDetail = element.select("a").attr("href");


                        Log.d("Content", "title: " + title);
                        Log.d("Content", "linkImage: " + linkImage);
                        Log.d("Content", "content: " + content);
                        Log.d("Content", "linkDetail: " + linkDetail);

                        listDataNews.add(new ItemDataNews(title, linkImage, content, linkDetail));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.setDataNews(listDataNews);
                adapter.notifyDataSetChanged();
            }
        }.execute(link);

    }

    private void crawlSinglePage() {
        listDataNews = new ArrayList<>();
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... values) {
                try {
                    Document doc = Jsoup.connect(values[0]).get();
                    Elements elements = doc.select("article.bxDoiSbIt");

                    for (Element element : elements) {
                        String title = element.select("a").attr("title");
                        String linkImage = element.select("img").attr("data-original");
                        String content = element.select("span.nwsSp").text();
                        String linkDetail = element.select("a").attr("href");

                        Log.d("Content", "title: " + title);
                        Log.d("Content", "linkImage: " + linkImage);
                        Log.d("Content", "content: " + content);
                        Log.d("Content", "linkDetail: " + linkDetail);

                        listDataNews.add(new ItemDataNews(title, linkImage, content, linkDetail));

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.setDataNews(listDataNews);
                adapter.notifyDataSetChanged();
            }
        }.execute(link);
    }


    @Override
    public void onItemClick(ItemDataNews itemDataNews) {
        Intent intent = new Intent(getContext(), ContentDetailActivity.class);
        intent.putExtra("detail", itemDataNews.getLinkDetail());
        startActivity(intent);
    }

    @Override
    public void onLongClickItem(ItemDataNews itemDataNews) {

    }
}
