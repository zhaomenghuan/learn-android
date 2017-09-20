package io.github.zhaomenghuan.javascriptinterface;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final WebView webView = new WebView(this);
        webView.loadUrl("file:///android_asset/www/index.html");
        // 开启debug
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }

        WebSettings webSettings = webView.getSettings();
        // 开启JavaScript代码
        webSettings.setJavaScriptEnabled(true);
        // 开启JavaScriptInterface
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.addJavascriptInterface(new WebAppInterface(this, webView), "JSBridge");
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
                view.loadUrl(url);
                // 消耗掉这个事件, Android中返回True的即到此为止吧,事件就会不会冒泡传递了，我们称之为消耗掉。
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    view.evaluateJavascript("fireDocumentEvent('titleUpdate', '" + view.getTitle() + "')", null);
                } else {
                    view.loadUrl("javascript:fireDocumentEvent('titleUpdate', '" + view.getTitle() + "')");
                }
            }
        });

        webView.setWebChromeClient(new WebChromeClient());

        setContentView(webView);
    }
}
