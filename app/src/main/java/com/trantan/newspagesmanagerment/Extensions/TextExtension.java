package com.trantan.newspagesmanagerment.Extensions;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.trantan.newspagesmanagerment.R;

public class TextExtension {
    public static void changeColor(Context context, TextView textView, int Color) {
        textView.setTextColor(ContextCompat.getColor(context, Color));
    }

    public static void showTitle(TextView textView, boolean enableTextView) {
        if (enableTextView) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }
}
