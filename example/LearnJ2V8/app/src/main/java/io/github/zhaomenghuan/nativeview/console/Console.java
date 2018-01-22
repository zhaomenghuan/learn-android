package io.github.zhaomenghuan.nativeview.console;

import android.util.Log;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;

import io.github.zhaomenghuan.nativeview.core.NativeObject;

public class Console extends NativeObject {
    private static String TAG = "AgreeSDK";
    private V8Object mObject;

    public Console(V8 v8Runtime) {
        super(v8Runtime);
    }

    public V8Object getV8Object() {
        return mObject;
    }

    public void log(final String message) {
        Log.v(TAG, message);
    }

    public void error(final String message) {
        Log.e(TAG, message);
    }

    public void warm(final String message) {
        Log.w(TAG, message);
    }

    @Override
    protected void initV8Object() {
        mObject = new V8Object(mRuntime);
        mObject.registerJavaMethod(this, "error", "error", new Class<?>[]{String.class});
        mObject.registerJavaMethod(this, "log", "log", new Class<?>[]{String.class});
        mObject.registerJavaMethod(this, "warm", "warm", new Class<?>[]{String.class});
    }

    @Override
    public void clean() {
        mObject.release();
    }
}
