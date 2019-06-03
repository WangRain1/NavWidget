/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.parrot.car.navwidget.mode;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class NavService extends Service {

    NavBroadCast mCast = new NavBroadCast();
    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver();
        Intent intent = new Intent(this,NavServiceServe.class);
        startService(intent);
        Log.i("---------","---NavService---onCreate---");
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("---------","---NavService---onStartCommand---");
        flags = Service.START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contents.NAV_TBT);
        registerReceiver(mCast,intentFilter);
    }

    public void unRegisterReceiver() {
        unregisterReceiver(mCast);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegisterReceiver();
    }
}
