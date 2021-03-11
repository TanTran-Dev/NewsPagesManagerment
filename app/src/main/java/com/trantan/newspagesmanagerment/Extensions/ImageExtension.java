package com.trantan.newspagesmanagerment.Extensions;

import android.content.Context;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

public class ImageExtension {
    public static void changeTintColor (Context context, ImageView imageView, int color){
        imageView.setColorFilter(ContextCompat.getColor(context, color));
    }
}
