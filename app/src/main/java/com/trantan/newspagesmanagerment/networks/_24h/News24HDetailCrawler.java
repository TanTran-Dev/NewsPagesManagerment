package com.trantan.newspagesmanagerment.networks._24h;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.trantan.newspagesmanagerment.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class News24HDetailCrawler<T extends ViewGroup> extends AsyncTask<String, String, Void> {
    private Context context;
    private T viewGroup;

    public News24HDetailCrawler(Context context, T viewGroup) {
        this.context = context;
        this.viewGroup = viewGroup;
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            Document document = Jsoup.connect(strings[0]).get();
            Elements elements = document.select("p");

            for (Element element : elements) {
                String detail = element.text();
                String linkImg = element.getElementsByTag("img").select("img").attr("data-original");

                publishProgress(linkImg, String.valueOf(Type.IMAGE));
                publishProgress(detail, String.valueOf(Type.TEXT));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        switch (Type.valueOf(values[1])){
            case IMAGE:{
                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(5,5,5,5);

                Log.d("IMAGE", "linkImg: "  + values[0] );
                if (values[0] != null && !values[0].isEmpty()){
                    viewGroup.addView(imageView);
                    Glide.with(context).load(values[0])
                            .into(imageView);
                }
            }
            break;

            case TEXT:{
                TextView textView = new TextView(context);
                textView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                textView.setTextSize(16);
                Typeface typeface = ResourcesCompat.getFont(context,R.font.lora_medium);

                textView.setTypeface(typeface);
                Log.d("TEXT", "onProgressUpdate: " + "&&" + values[0] + "&&");
                viewGroup.addView(textView);
                textView.setText(values[0]);
            }
            break;
        }
    }

    private enum Type{
        TEXT, IMAGE
    }
}
