package com.trantan.newspagesmanagerment.networks._24h;

import android.os.AsyncTask;

import com.trantan.newspagesmanagerment.adapter.ListNewsAdapter;
import com.trantan.newspagesmanagerment.event_bus.DataNewsEvent;
import com.trantan.newspagesmanagerment.model.ItemDataNew;
import com.trantan.newspagesmanagerment.model.TopicModel;
import com.trantan.newspagesmanagerment.view.NewsView;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class News24HCrawler<T extends ListNewsAdapter> extends AsyncTask<String, String, TopicModel> {
    private T adapter;
    private NewsView view;

    public News24HCrawler(T adapter, NewsView view) {
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
    protected TopicModel doInBackground(String... values) {
        List<ItemDataNew> normalNews = new ArrayList<>();
        TopicModel topicModel = null;
        try {
            Document doc = Jsoup.connect(values[0]).get();
            Elements elements = doc.select("article.bxDoiSbIt");

            Element element1 = doc.getElementsByClass("cateBdm brmCmTb brmCmTbx").first();
            String topicTitle = element1.select("a").attr("title");

            for (Element element : elements) {
                String title = element.select("a").attr("title");
                String linkImage = element.select("img").attr("data-original");
                String content = element.select("span.nwsSp").text();
                String createdDate = element.select("span.updTm").text();
                String linkDetail = element.select("a").attr("href");

                ItemDataNew dataNew = new ItemDataNew(title, linkImage, content, createdDate, linkDetail);

                normalNews.add(dataNew);
                EventBus.getDefault().post(new DataNewsEvent(dataNew));
            }

            topicModel = new TopicModel(topicTitle, normalNews);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topicModel;
    }

    @Override
    protected void onPostExecute(TopicModel topicModel) {
        view.hideRefreshingProgress();
        view.enableRefreshing(true);
        adapter.setTopicModel(topicModel);
        adapter.notifyItemChanged(ListNewsAdapter.VIEW_TYPE_NORMAL_NEWS);
    }
}
