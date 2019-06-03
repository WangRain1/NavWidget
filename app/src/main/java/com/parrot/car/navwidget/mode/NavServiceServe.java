/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.parrot.car.navwidget.mode;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.util.Random;

public class NavServiceServe extends Service {

    /**
     * 深度思考
     * 什么年龄作什么活
     *
     */
    int i = 0;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            i ++;
            Log.i("---------","---handleMessage---");
            sendBroadcastReceiver();
            mHandler.sendEmptyMessageDelayed(1,2000);
        }
    };

    Intent mIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        mIntent = new Intent(Contents.NAV_TBT);
        mHandler.sendEmptyMessageDelayed(1,10000);
        Log.i("---------","---NavService---1onCreate---");
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("---------","---NavService---1onStartCommand---");
        flags = Service.START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    Random random = new Random();
    private void sendBroadcastReceiver() {
        Log.i("---------","---NavService---1sendBroadcastReceiver---");
        if (null == mIntent) {
            mIntent = new Intent(Contents.NAV_TBT);
        }
        mIntent.putExtra(Contents.NAV_DIST,i*10);
        mIntent.putExtra(Contents.NAV_MANOEUVRE,random.nextInt(2));
        mIntent.putExtra(Contents.NAV_UNIT,random.nextInt(6));
        sendBroadcast(mIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("---------","---NavService---onDestroy---");
        mHandler.removeCallbacksAndMessages(null);
    }
}
