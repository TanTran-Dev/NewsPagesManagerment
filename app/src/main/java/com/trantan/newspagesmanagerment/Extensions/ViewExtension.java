package com.trantan.newspagesmanagerment.Extensions;

import android.content.Context;
import android.view.View;

import androidx.core.content.ContextCompat;

public class ViewExtension {
    public static void changeColor(Context context, View view, int color) {
        view.setBackgroundColor(ContextCompat.getColor(context, color));
    }
}
