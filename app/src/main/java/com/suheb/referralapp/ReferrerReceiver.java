package com.suheb.referralapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by suheb on 27/11/16.
 */

public class ReferrerReceiver extends BroadcastReceiver {
    public static final String ACTION_UPDATE_DATA = "ACTION_UPDATE_DATA";
    private static final String ACTION_INSTALL_REFERRER = "com.android.vending.INSTALL_REFERRER";
    private static final String KEY_REFERRER = "referrer";

    public ReferrerReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        Utility.setReferrerData(context.getApplicationContext(), (String) extras.get(KEY_REFERRER));
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_UPDATE_DATA));
    }
}
