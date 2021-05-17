package com.trantan.newspagesmanagerment.networks._24h;

import android.os.AsyncTask;

import com.trantan.newspagesmanagerment.adapter.ListPostAdapter;
import com.trantan.newspagesmanagerment.event_bus.DataNewsEvent;
import com.trantan.newspagesmanagerment.model.ItemDataNew;
import com.trantan.newspagesmanagerment.view.PostView;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class News24HTopCrawler<T extends ListPostAdapter> extends AsyncTask<String, Void, List<ItemDataNew>> {
    private T adapter;
    private PostView view;

    public News24HTopCrawler(T adapter, PostView view) {
        this.adapter = adapter;
        this.view = view;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        view.showRefreshingProgress();
        view.enableRefreshing(false);
    }

    @Override
    protected List<ItemDataNew> doInBackground(String... values) {
        List<ItemDataNew> topNews = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(values[0]).get();
            Elements elements = doc.select("div.item");

            for (Element element : elements) {
                String title = element.select("a").attr("title");
                String linkImage = element.select("img").attr("data-original");
                String linkDetail = element.select("a").attr("href");
//                        String desc = doc.select("div.desc").first().text();


                ItemDataNew dataNew = new ItemDataNew(title, linkImage, null, null, linkDetail);
                topNews.add(dataNew);

                EventBus.getDefault().post(new DataNewsEvent(dataNew));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topNews;
    }

    @Override
    protected void onPostExecute(List<ItemDataNew> itemDataNews) {
        view.hideRefreshingProgress();
        view.enableRefreshing(true);
        adapter.addListTop(itemDataNews);
        adapter.notifyItemChanged(ListPostAdapter.VIEW_TYPE_TOP_NEWS);
    }
}
