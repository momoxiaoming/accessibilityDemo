package com.auto.assist.accessibility.util;

import android.util.Log;

public class LogUtil
{

    private static final String TAG = "accessibility";

    private static boolean isSHow = true;


    public static  void E(String msg) {
        if (isSHow)
            Log.e(TAG, msg);
    }
    public static void D(String msg) {
        if (isSHow)
            Log.d(TAG, msg);
    }

    public static void I(String msg) {
        if (isSHow)
            Log.i(TAG, msg);
    }
}
