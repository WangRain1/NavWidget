/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.parrot.car.navwidget.mode;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.parrot.car.navwidget.MainActivity;
import com.parrot.car.navwidget.R;

import static android.provider.Settings.ACTION_SETTINGS;

public class NavWidget extends AppWidgetProvider {

    private static final String TAG = NavWidget.class.getSimpleName();
    /**
     * 小窗口点击时候发出的广播
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e(TAG,"nav onReceive.");
    }

    public static void updateWidget(Context context,int icon,String dist, String unit){
        // 获取 example_appwidget.xml 对应的RemoteViews
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.nav_widget_item);
        Log.i("---------","---NavService---updateWidget---");
        remoteView.setImageViewResource(R.id.widget_icon,icon);
        // 设置显示数字
        remoteView.setTextViewText(R.id.nav_text_left, dist);
        remoteView.setTextViewText(R.id.nav_text_right, unit);
        // 设置点击按钮对应的PendingIntent：即点击按钮时，发送广播。
        remoteView.setOnClickPendingIntent(R.id.widget_layout, getOpenPendingIntent(context));
        ComponentName componentName = new ComponentName(context, NavWidget.class);
        // 更新 widget
        AppWidgetManager.getInstance(context).updateAppWidget(componentName, remoteView);
    }

    /**
     * 获取 打开 MainActivity 的 PendingIntent
     */
    private static PendingIntent getOpenPendingIntent(Context context) {
        Intent intent = new Intent(ACTION_SETTINGS);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
        return pi;
    }

    /**
     * 当该窗口小部件第一次添加到桌面时调用该方法，可添加多次但只第一次调用
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.i("---------","---NavService---onEnabled---");
        Intent intent = new Intent(context,NavService.class);
        context.startService(intent);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Intent intent = new Intent(context,NavService.class);
        context.stopService(intent);
    }

    /**
     * 每次窗口小部件被点击更新都调用一次该方法
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}
