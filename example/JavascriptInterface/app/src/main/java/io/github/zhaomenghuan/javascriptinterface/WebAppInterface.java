package io.github.zhaomenghuan.javascriptinterface;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by zhaomenghuan on 2017/8/26.
 */

public class WebAppInterface {
    private static String TAG = "App";
    Context context;
    WebView webView;

    public WebAppInterface(Context context, WebView webView) {
        this.context = context;
        this.webView = webView;
    }

    @JavascriptInterface
    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
