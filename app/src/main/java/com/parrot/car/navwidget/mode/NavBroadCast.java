/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.parrot.car.navwidget.mode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.parrot.car.navwidget.R;

public class NavBroadCast extends BroadcastReceiver {

    int icons[] = new int[]{R.drawable.nav_direction, R.drawable.nav_preview};
    String arr[] = new String[]{"NO UNITS", "METRES", "KILOMETRES", "FEET", "YARDS", "MILES"};

    @Override
    public void onReceive(Context context, Intent intent) {

        if (null == intent) {
            return;
        }
        if (Contents.NAV_TBT.equals(intent.getAction())) {
            int dist = intent.getIntExtra(Contents.NAV_DIST, 0);
            int manoeuvre = intent.getIntExtra(Contents.NAV_MANOEUVRE, 0);
            int unit = intent.getIntExtra(Contents.NAV_UNIT, 0);
            NavWidget.updateWidget(context, icons[manoeuvre], String.valueOf(dist), arr[unit]);
        }

    }
}
