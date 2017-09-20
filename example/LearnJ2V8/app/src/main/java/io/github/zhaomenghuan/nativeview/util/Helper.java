package io.github.zhaomenghuan.nativeview.util;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

public class Helper {
    public static String TAG;
    public static boolean DEBUG = false;
    private static Application mApplicationContent;
    private static Toast toast;

    public static void initialize(Application app) {
        mApplicationContent = app;
    }

    public static Application getApplication() {
        return mApplicationContent;
    }

    public static void setDebug(String TAG, boolean isDebug) {
        Helper.TAG = TAG;
        Helper.DEBUG = isDebug;
    }

    public static void Log(String TAG, String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void Log(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void Toast(String text, String type) {
        if (toast == null) {
            int duration = type.equals("S") ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
            toast = Toast.makeText(mApplicationContent, text, duration);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}
