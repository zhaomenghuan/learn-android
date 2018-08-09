package cn.com.agree.android.launcher;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cn.com.agree.android.utils.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        sharedPreferencesHelper = new SharedPreferencesHelper(mContext, "HomeKeyListener");

        HomeKeyEventBroadCastReceiver  receiver = new HomeKeyEventBroadCastReceiver();
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

        findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLauncher();
            }
        });
    }

    public class HomeKeyEventBroadCastReceiver extends BroadcastReceiver {
        private static final String SYSTEM_REASON = "reason";
        private static final String SYSTEM_HOME_KEY = "homekey";
        private static final String SYSTEM_RECENT_APPS = "recentapps";
        private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";
        private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (reason != null) {
                    if (reason.equals(SYSTEM_HOME_KEY)) {
                        showToast("收到HomeKey点击");
                    } else if (reason.equals(SYSTEM_RECENT_APPS)) {
                        showToast("收到长按Home键或任务键点击");
                    } else if (reason.equals(SYSTEM_DIALOG_REASON_LOCK)) {
                        showToast("锁屏");
                    } else if (reason.equals(SYSTEM_DIALOG_REASON_ASSIST)) {
                        showToast("samsung 长按Home键");
                    }
                    clearDefaultLauncher();
                    if (sharedPreferencesHelper.getSharedPreference("setDefaultLauncher", false).toString() == "false") {
                        openLauncher();
                        sharedPreferencesHelper.put("setDefaultLauncher", true);
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        showToast("屏蔽默认返回键逻辑");
    }

    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 清除默认桌面（采用先设置一个空的桌面为默认然后在将该空桌面禁用的方式来实现）
     */
    public void clearDefaultLauncher() {
        PackageManager pm = mContext.getPackageManager();
        String pn = mContext.getPackageName();
        String hn = MainActivity.class.getName();
        ComponentName mhCN = new ComponentName(pn, hn);
        Intent homeIntent = new Intent("android.intent.action.MAIN");
        homeIntent.addCategory("android.intent.category.HOME");
        homeIntent.addCategory("android.intent.category.DEFAULT");
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pm.setComponentEnabledSetting(mhCN, 1, 1);
        mContext.startActivity(homeIntent);
        pm.setComponentEnabledSetting(mhCN, 0, 1);
    }

    /**
     * 打开自定义桌面
     */
    public void openLauncher() {
        Intent paramIntent = new Intent("android.intent.action.MAIN");
        paramIntent.setComponent(new ComponentName("com.huawei.android.internal.app", "com.huawei.android.internal.app.HwResolverActivity"));
        paramIntent.addCategory("android.intent.category.DEFAULT");
        paramIntent.addCategory("android.intent.category.HOME");
        mContext.startActivity(paramIntent);
    }
}
