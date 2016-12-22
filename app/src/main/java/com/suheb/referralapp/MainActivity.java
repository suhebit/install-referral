package com.suheb.referralapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by suheb on 27/11/16.
 */
public class MainActivity extends AppCompatActivity {

    private TextView content;

    private final BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateData();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (TextView) findViewById(R.id.content);
        updateData();
    }
    /*

     1.For testing navigate to your sdk/platform-tools folder in terminal
     2. Type ./adb shell >> adb shell will be open
     3. enter in adb shell >> am broadcast -a com.android.vending.INSTALL_REFERRER -n com.suheb.referralapp/com.suheb.referralapp.ReferrerReceiver --es referrer "YOUR_REFERRAL_CODE_HERE"
     4.You will get your referal code successfully.
     5.For any kind of help contact suheb.it7@gmail.com

    */

    private void updateData() {
        String referrerDataRaw = Utility.getReferrerDataRaw(getApplicationContext());
        String referrerDataDecoded = Utility.getReferrerDataDecoded(getApplicationContext());

        if (!TextUtils.isEmpty(Utility.getReferrerDataRaw(this))) {
            if (referrerDataDecoded != null) {
                referrerDataRaw = referrerDataRaw + " " + referrerDataDecoded;
            }
        }
        content.setText(referrerDataRaw);
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mUpdateReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(mUpdateReceiver, new IntentFilter(ReferrerReceiver.ACTION_UPDATE_DATA));
        super.onResume();
    }

}
