package com.suheb.referralapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * Created by suheb on 27/11/16.
 */

public class Utility {

    public static void setFirstLaunch(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        if (!sp.contains(Constants.FIRST_LAUNCH)) {
            sp.edit().putLong(Constants.FIRST_LAUNCH, new Date().getTime()).apply();
        }
    }


    public static void setReferrerData(Context context, String data) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
//        if (!sp.contains(REFERRER_DATA)) {
        sp.edit().putString(Constants.REFERRER_DATA, data).apply();
//        }
    }

    public static String getReferrerDataRaw(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        if (!sp.contains(Constants.REFERRER_DATA)) {
            return "";
        }
        return sp.getString(Constants.REFERRER_DATA, null);
    }

    public static String getReferrerDataDecoded(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        String raw = sp.getString(Constants.REFERRER_DATA, null);

        if (raw == null) {
            return null;
        }

        try {
            String url = URLDecoder.decode(raw, "utf-8");
            try {
                String url2x = URLDecoder.decode(url, "utf-8");
                if (raw.equals(url2x)) {
                    return null;
                }
                return url2x;
            } catch (UnsupportedEncodingException uee) {
                // not URL 2x encoded but URL encoded
                if (raw.equals(url)) {
                    return null;
                }
                return url;
            }
        } catch (UnsupportedEncodingException uee) {
            // not URL encoded
        }
        return null;
    }
}
