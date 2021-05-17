package com.trantan.newspagesmanagerment.view.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.model.ItemDataNewsContent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FragmentItemNewsContent extends Fragment {
    private ScrollView scrollView;
    private ItemDataNewsContent itemDataNewsContent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_item_news_content,
                container,
                false
        );
        scrollView = view.findViewById(R.id.scrlView);
        crawlDataItemContent();
        return view;
    }


    private void crawlDataItemContent(){
        new AsyncTask<String, Void , Void>(){

            @Override
            protected Void doInBackground(String... strings) {

                try {
                    Document document = Jsoup.connect(strings[0]).get();
                    Elements elements = document.select("article.nwsHt nwsUpgrade");

                    for (Element element : elements){
                        String title = element.select("h1").first().text();
                        String image = element.select("img").attr("src");
                        String desc = element.select("h2").attr("ctTp");
                        String content = element.select("p").text();

                        itemDataNewsContent = new ItemDataNewsContent(title, image, desc , content);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

            }
        }.execute();
    }
}
